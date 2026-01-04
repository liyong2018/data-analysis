import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;

public class CodeGenerator {

    static class Formula {
        String target;
        List<String> sources = new ArrayList<>();
        String externalTable; // null if internal, tableId if external
    }

    static class Field {
        String name;
        String unit;
        String code;

        public Field(String name, String unit, String code) {
            this.name = name.trim();
            this.unit = unit.trim();
            this.code = code.trim();
        }
    }

    static class Table {
        String id;
        String name;
        List<Field> fields = new ArrayList<>();
        List<Formula> formulas = new ArrayList<>();

        public Table(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        String inputFile = "extracted.txt";
        String entityPackage = "com.evaluate.analysis.entity";
        String mapperPackage = "com.evaluate.analysis.mapper";
        String entityPath = "src/main/java/com/evaluate/analysis/entity/";
        String mapperPath = "src/main/java/com/evaluate/analysis/mapper/";

        List<Table> tables = new ArrayList<>();
        Table currentTable = null;
        boolean inLogic = false;
        StringBuilder logicBuffer = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
            String line;
            List<String> previousLines = new ArrayList<>();
            Pattern tableIdPattern = Pattern.compile("表\\s*号\\s*[：:]\\s*([A-Z][0-9]+)");
            Pattern fieldPattern = Pattern.compile("^(.*?)\\s+([^\\s]+)\\s+([A-Z][0-9]{5,})$");

            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                Matcher idMatcher = tableIdPattern.matcher(line);
                if (idMatcher.find()) {
                    // Process previous table's logic
                    if (currentTable != null && logicBuffer.length() > 0) {
                        parseLogic(currentTable, logicBuffer.toString());
                        logicBuffer.setLength(0);
                    }
                    inLogic = false;

                    String id = idMatcher.group(1);
                    currentTable = new Table(id);
                    tables.add(currentTable);
                    
                    for (int i = previousLines.size() - 1; i >= 0 && i >= previousLines.size() - 20; i--) {
                        String prev = previousLines.get(i);
                        if (prev.contains("表") && (prev.startsWith("（") || prev.startsWith("("))) {
                            currentTable.name = prev;
                            break;
                        }
                    }
                    continue;
                }

                if (line.contains("逻辑关系")) {
                    inLogic = true;
                    logicBuffer.setLength(0);
                    continue;
                }

                if (inLogic) {
                    // Filter out noise to avoid parsing page numbers or irrelevant text as logic
                    if (!line.matches("^\\d+$") && !line.startsWith("（") && !line.contains("表 号") && !line.contains("填报单位")) {
                         logicBuffer.append(line);
                    }
                } else if (currentTable != null) {
                    Matcher fieldMatcher = fieldPattern.matcher(line);
                    if (fieldMatcher.find()) {
                        String name = fieldMatcher.group(1);
                        String unit = fieldMatcher.group(2);
                        String code = fieldMatcher.group(3);
                        if (code.startsWith(currentTable.id)) {
                             currentTable.fields.add(new Field(name, unit, code));
                        }
                    }
                }

                previousLines.add(line);
                if (previousLines.size() > 20) {
                    previousLines.remove(0);
                }
            }
            // Process last table logic
            if (currentTable != null && logicBuffer.length() > 0) {
                parseLogic(currentTable, logicBuffer.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Table table : tables) {
            generateEntity(table, entityPath, entityPackage);
            generateMapper(table, mapperPath, mapperPackage, entityPackage);
            generateService(table, "src/main/java/com/evaluate/analysis/service/", "com.evaluate.analysis.service", entityPackage);
            generateServiceImpl(table, "src/main/java/com/evaluate/analysis/service/impl/", "com.evaluate.analysis.service.impl", "com.evaluate.analysis.service", mapperPackage, entityPackage);
            generateController(table, "src/main/java/com/evaluate/analysis/controller/", "com.evaluate.analysis.controller", "com.evaluate.analysis.service", entityPackage);
            generateVueView(table, "frontend/src/views/");
        }
        
        generateVueRouter(tables, "frontend/src/router/");
        generateHomeView(tables, "frontend/src/views/");
        
        System.out.println("Generated code for " + tables.size() + " tables.");
    }

    private static void parseLogic(Table table, String text) {
        // Split by separators (Chinese/English semicolon, period)
        String[] parts = text.split("[;；。]");
        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;

            // Check for equality and ensure it's a formula (not inequality)
            if (part.contains("=") && !part.contains("≥") && !part.contains(">") && !part.contains("<")) {
                String[] eq = part.split("=");
                if (eq.length != 2) continue;
                
                String leftSide = eq[0];
                String target = "";
                Matcher m = Pattern.compile(table.id + "[0-9]+").matcher(leftSide);
                if (m.find()) {
                    target = m.group();
                } else {
                    continue;
                }

                String expr = eq[1].trim();
                
                // Case 1: Summation (A = B + C + ...)
                if (expr.contains("+")) {
                    Formula f = new Formula();
                    f.target = target;
                    String[] terms = expr.split("\\+");
                    for (String term : terms) {
                        String cleanTerm = term.replaceAll("[^A-Z0-9]", "");
                        if (!cleanTerm.isEmpty()) {
                            f.sources.add(cleanTerm);
                        }
                    }
                    
                    // Filter: Only allow if target and all sources belong to this table
                    if (f.target.startsWith(table.id)) {
                        boolean allSourcesInTable = true;
                        for (String s : f.sources) {
                            if (!s.startsWith(table.id)) {
                                allSourcesInTable = false;
                                break;
                            }
                        }
                        if (allSourcesInTable && !f.sources.isEmpty()) {
                            table.formulas.add(f);
                        }
                    }
                }
                // Case 2: Simple Assignment (A = B)
                else {
                     String cleanExpr = expr.replaceAll("[^A-Z0-9]", "");
                     if (cleanExpr.matches("[A-Z][0-9]+")) {
                         Formula f = new Formula();
                         f.target = target;
                         f.sources.add(cleanExpr);
                         
                         // Check if external
                         if (!cleanExpr.startsWith(table.id)) {
                             // Extract Table ID from field code (e.g., B01055 -> B01)
                             // Assuming standard format: ID + digits. ID is usually 3 chars (A01, Z01)
                             // But B01055 starts with B01.
                             // Let's guess the table ID length. Usually 3.
                             if (cleanExpr.length() >= 3) {
                                 f.externalTable = cleanExpr.substring(0, 3);
                             }
                         }
                         
                         table.formulas.add(f);
                     }
                }
            }
        }
    }

    private static void generateHomeView(List<Table> tables, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + "HomeView.vue", StandardCharsets.UTF_8))) {
            pw.println("<template>");
            pw.println("  <div class=\"home\">");
            pw.println("    <h1>自然灾害损失统计调查制度 - 数据录入系统</h1>");
            pw.println("    <div class=\"table-list\">");
            
            for (Table table : tables) {
                String displayName = table.name;
                if (displayName == null) displayName = table.id;
                
                // Clean up name
                displayName = displayName.replaceAll("^[（(].*?[）)]", "").trim();
                if (displayName.endsWith("统计表")) {
                    displayName = displayName.substring(0, displayName.length() - 3);
                } else if (displayName.endsWith("汇总表")) {
                    displayName = displayName.substring(0, displayName.length() - 3);
                } else if (displayName.endsWith("表")) {
                    displayName = displayName.substring(0, displayName.length() - 1);
                }
                
                String fullLabel = table.id + " " + displayName;
                
                pw.println("      <router-link to=\"/table" + table.id + "\" class=\"table-link\">");
                pw.println("        <el-card shadow=\"hover\" class=\"table-card\">");
                pw.println("          " + fullLabel);
                pw.println("        </el-card>");
                pw.println("      </router-link>");
            }
            
            pw.println("    </div>");
            pw.println("  </div>");
            pw.println("</template>");
            pw.println();
            pw.println("<style scoped>");
            pw.println(".home {");
            pw.println("  padding: 20px;");
            pw.println("  max-width: 1200px;");
            pw.println("  margin: 0 auto;");
            pw.println("}");
            pw.println("h1 {");
            pw.println("  text-align: center;");
            pw.println("  margin-bottom: 40px;");
            pw.println("  color: #303133;");
            pw.println("}");
            pw.println(".table-list {");
            pw.println("  display: grid;");
            pw.println("  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));");
            pw.println("  gap: 20px;");
            pw.println("}");
            pw.println(".table-link {");
            pw.println("  text-decoration: none;");
            pw.println("}");
            pw.println(".table-card {");
            pw.println("  height: 100px;");
            pw.println("  display: flex;");
            pw.println("  align-items: center;");
            pw.println("  justify-content: center;");
            pw.println("  text-align: center;");
            pw.println("  font-size: 16px;");
            pw.println("  font-weight: bold;");
            pw.println("  color: #409EFF;");
            pw.println("  transition: all 0.3s;");
            pw.println("}");
            pw.println(".table-card:hover {");
            pw.println("  transform: translateY(-5px);");
            pw.println("}");
            pw.println("</style>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateVueView(Table table, String path) {
        String fileName = "Table" + table.id + ".vue";
        
        Set<String> targetFields = new HashSet<>();
        boolean hasExternalFormulas = false;
        
        for (Formula f : table.formulas) {
            targetFields.add(f.target);
            if (f.externalTable != null) {
                hasExternalFormulas = true;
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + fileName, StandardCharsets.UTF_8))) {
            pw.println("<template>");
            pw.println("  <div class=\"table-container\">");
            pw.println("    <h2>" + (table.name != null ? table.name : table.id) + "</h2>");
            pw.println("    <el-form :model=\"form\" label-position=\"top\">");
            pw.println("      <el-form-item label=\"报告ID\">");
            pw.println("        <el-input v-model=\"form.reportId\" size=\"large\" />");
            pw.println("      </el-form-item>");
            pw.println("      <el-form-item label=\"行政区划代码\">");
            pw.println("        <el-tree-select");
            if (hasExternalFormulas) {
                pw.println("          @change=\"fetchExternalData\"");
            }
            pw.println("          v-model=\"form.regionCode\"");
            pw.println("          :data=\"regionOptions\"");
            pw.println("          :props=\"{ label: 'label', value: 'code', children: 'children' }\"");
            pw.println("          placeholder=\"请选择行政区划\"");
            pw.println("          size=\"large\"");
            pw.println("          style=\"width: 100%\"");
            pw.println("          filterable");
            pw.println("          check-strictly");
            pw.println("        />");
            pw.println("      </el-form-item>");
            pw.println("      <el-form-item label=\"报告日期\">");
            pw.println("        <el-date-picker v-model=\"form.reportDate\" type=\"datetime\" value-format=\"YYYY-MM-DD HH:mm:ss\" size=\"large\" style=\"width: 100%\" />");
            pw.println("      </el-form-item>");
            
            for (Field field : table.fields) {
                // Determine if this field is a target of an EXTERNAL formula
                boolean isExternalTarget = false;
                String sourceDesc = "";
                for(Formula f : table.formulas) {
                    if(f.target.equals(field.code) && f.externalTable != null) {
                        isExternalTarget = true;
                        sourceDesc = f.sources.get(0);
                        break;
                    }
                }

                // Intra-table targets are disabled (read-only).
                // External targets: We allow manual edit but show placeholder/hint, OR disable if we are confident.
                // Since we are implementing fetch, we can disable them OR leave them enabled for override.
                // User said "Parent items don't need manual entry".
                // We'll leave them enabled but with a placeholder showing the source.
                // Actually, for Z01024 (Sum), it IS disabled.
                // For Z01001 (External), let's keep it enabled so user can correct it if fetch fails.
                
                boolean isInternalTarget = targetFields.contains(field.code) && !isExternalTarget;
                
                pw.println("      <el-form-item label=\"" + field.name + " (" + field.unit + ")\">");
                pw.print("        <el-input-number v-model=\"form.col" + field.code + "\" :precision=\"2\" :step=\"0.1\" size=\"large\" style=\"width: 100%\"");
                if (isInternalTarget) {
                    pw.print(" disabled");
                }
                if (isExternalTarget) {
                     pw.print(" placeholder=\"自动关联: " + sourceDesc + "\"");
                }
                pw.println(" />");
                if (isExternalTarget) {
                    pw.println("        <div class=\"form-tip\">关联数据源: " + sourceDesc + "</div>");
                }
                pw.println("      </el-form-item>");
            }
            
            pw.println("      <el-form-item>");
            pw.println("        <el-button type=\"primary\" @click=\"onSubmit\" size=\"large\" style=\"width: 100%\">提交</el-button>");
            pw.println("      </el-form-item>");
            pw.println("    </el-form>");
            pw.println("  </div>");
            pw.println("</template>");
            pw.println();
            pw.println("<style scoped>");
            pw.println(".table-container {");
            pw.println("  padding: 20px;");
            pw.println("  max-width: 800px;");
            pw.println("  margin: 0 auto;");
            pw.println("}");
            pw.println(".form-tip {");
            pw.println("  font-size: 12px;");
            pw.println("  color: #909399;");
            pw.println("  line-height: 1.5;");
            pw.println("}");
            pw.println("</style>");
            pw.println();
            pw.println("<script setup lang=\"ts\">");
            pw.println("import { reactive, watchEffect } from 'vue'");
            pw.println("import axios from 'axios'");
            pw.println("import { ElMessage } from 'element-plus'");
            pw.println("import regionData from '@/assets/regions.json'");
            pw.println();
            
            // ... (formatRegions, generateReportId, getCurrentDateTime same as before)
            pw.println("// Recursively format region data to include code in label for display");
            pw.println("const formatRegions = (data: any[]): any[] => {");
            pw.println("  return data.map(item => {");
            pw.println("    const newItem = { ...item }");
            pw.println("    if (!item.children || item.children.length === 0) {");
            pw.println("      newItem.label = `${item.name} - ${item.code}`");
            pw.println("    } else {");
            pw.println("      newItem.label = item.name");
            pw.println("      newItem.children = formatRegions(item.children)");
            pw.println("    }");
            pw.println("    return newItem");
            pw.println("  })");
            pw.println("}");
            pw.println();
            pw.println("const regionOptions = formatRegions(regionData)");
            pw.println();
            pw.println("const generateReportId = () => {");
            pw.println("  const now = new Date()");
            pw.println("  const year = now.getFullYear()");
            pw.println("  const month = String(now.getMonth() + 1).padStart(2, '0')");
            pw.println("  const day = String(now.getDate()).padStart(2, '0')");
            pw.println("  const random = String(Math.floor(Math.random() * 1000)).padStart(3, '0')");
            pw.println("  return `${year}${month}${day}${random}`");
            pw.println("}");
            pw.println();
            pw.println("const getCurrentDateTime = () => {");
            pw.println("  const now = new Date()");
            pw.println("  const year = now.getFullYear()");
            pw.println("  const month = String(now.getMonth() + 1).padStart(2, '0')");
            pw.println("  const day = String(now.getDate()).padStart(2, '0')");
            pw.println("  const hour = String(now.getHours()).padStart(2, '0')");
            pw.println("  const minute = String(now.getMinutes()).padStart(2, '0')");
            pw.println("  const second = String(now.getSeconds()).padStart(2, '0')");
            pw.println("  return `${year}-${month}-${day} ${hour}:${minute}:${second}`");
            pw.println("}");
            pw.println();

            pw.println("const form = reactive({");
            pw.println("  reportId: generateReportId(),");
            pw.println("  regionCode: '',");
            pw.println("  reportDate: getCurrentDateTime(),");
            for (Field field : table.fields) {
                pw.println("  col" + field.code + ": 0,");
            }
            pw.println("})");
            pw.println();
            
            if (hasExternalFormulas) {
                pw.println("const fetchExternalData = async () => {");
                pw.println("  if (!form.regionCode) return;");
                pw.println("  try {");
                pw.println("    const res = await axios.get(`/api/table" + table.id + "/summary?regionCode=${form.regionCode}`);");
                pw.println("    const data = res.data;");
                pw.println("    if (data) {");
                for (Formula f : table.formulas) {
                    if (f.externalTable != null) {
                        pw.println("      if (data.col" + f.target + " !== undefined) form.col" + f.target + " = data.col" + f.target + ";");
                    }
                }
                pw.println("      ElMessage.success('已自动获取关联数据');");
                pw.println("    }");
                pw.println("  } catch (e) {");
                pw.println("    console.error('Failed to fetch external data', e);");
                pw.println("  }");
                pw.println("}");
                pw.println();
            }

            if (!table.formulas.isEmpty()) {
                pw.println("// Auto-calculation for parent fields");
                pw.println("watchEffect(() => {");
                for (Formula f : table.formulas) {
                    // Only generate watchEffect for INTERNAL formulas (summations within table)
                    if (f.externalTable == null) {
                        pw.print("  form.col" + f.target + " = ");
                        for (int i = 0; i < f.sources.size(); i++) {
                            String src = f.sources.get(i);
                            pw.print("(Number(form.col" + src + ") || 0)");
                            if (i < f.sources.size() - 1) {
                                pw.print(" + ");
                            }
                        }
                        pw.println();
                    }
                }
                pw.println("})");
                pw.println();
            }

            pw.println("const onSubmit = async () => {");
            pw.println("  try {");
            pw.println("    await axios.post('/api/table" + table.id + "', form)");
            pw.println("    ElMessage.success('提交成功')");
            pw.println("  } catch (error) {");
            pw.println("    ElMessage.error('提交失败')");
            pw.println("  }");
            pw.println("}");
            pw.println("</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateVueRouter(List<Table> tables, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + "index.ts", StandardCharsets.UTF_8))) {
            pw.println("import { createRouter, createWebHistory } from 'vue-router'");
            pw.println();
            pw.println("const router = createRouter({");
            pw.println("  history: createWebHistory(import.meta.env.BASE_URL),");
            pw.println("  routes: [");
            pw.println("    {");
            pw.println("      path: '/',");
            pw.println("      name: 'home',");
            pw.println("      component: () => import('../views/HomeView.vue')");
            pw.println("    },");
            
            for (Table table : tables) {
                pw.println("    {");
                pw.println("      path: '/table" + table.id + "',");
                pw.println("      name: 'table" + table.id + "',");
                pw.println("      component: () => import('../views/Table" + table.id + ".vue')");
                pw.println("    },");
            }
            
            pw.println("  ]");
            pw.println("})");
            pw.println();
            pw.println("export default router");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateEntity(Table table, String path, String pkg) {
        String className = "Table" + table.id;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".java", StandardCharsets.UTF_8))) {
            pw.println("package " + pkg + ";");
            pw.println();
            pw.println("import com.baomidou.mybatisplus.annotation.TableName;");
            pw.println("import com.baomidou.mybatisplus.annotation.IdType;");
            pw.println("import com.baomidou.mybatisplus.annotation.TableId;");
            pw.println("import java.math.BigDecimal;");
            pw.println("import java.time.LocalDate;");
            pw.println("import java.time.LocalDateTime;");
            pw.println("import lombok.Data;");
            pw.println();
            pw.println("/**");
            pw.println(" * " + (table.name != null ? table.name : table.id));
            pw.println(" */");
            pw.println("@Data");
            pw.println("@TableName(\"table_" + table.id + "\")");
            pw.println("public class " + className + " {");
            pw.println();
            pw.println("    @TableId(type = IdType.AUTO)");
            pw.println("    private Long id;");
            pw.println();
            pw.println("    /** 报告ID */");
            pw.println("    private String reportId;");
            pw.println();
            pw.println("    /** 行政区划代码 */");
            pw.println("    private String regionCode;");
            pw.println();
            pw.println("    /** 报告日期 */");
            pw.println("    private LocalDateTime reportDate;");
            pw.println();
            
            for (Field field : table.fields) {
                pw.println("    /** " + field.name + " (" + field.unit + ") */");
                pw.println("    private BigDecimal col" + field.code + ";");
                pw.println();
            }
            
            pw.println("    private LocalDateTime createdAt;");
            pw.println("    private LocalDateTime updatedAt;");
            pw.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateMapper(Table table, String path, String pkg, String entityPkg) {
        String className = "Table" + table.id + "Mapper";
        String entityName = "Table" + table.id;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".java", StandardCharsets.UTF_8))) {
            pw.println("package " + pkg + ";");
            pw.println();
            pw.println("import " + entityPkg + "." + entityName + ";");
            pw.println("import com.baomidou.mybatisplus.core.mapper.BaseMapper;");
            pw.println("import org.apache.ibatis.annotations.Mapper;");
            pw.println();
            pw.println("@Mapper");
            pw.println("public interface " + className + " extends BaseMapper<" + entityName + "> {");
            pw.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateService(Table table, String path, String pkg, String entityPkg) {
        String className = "Table" + table.id + "Service";
        String entityName = "Table" + table.id;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".java", StandardCharsets.UTF_8))) {
            pw.println("package " + pkg + ";");
            pw.println();
            pw.println("import " + entityPkg + "." + entityName + ";");
            pw.println("import com.baomidou.mybatisplus.extension.service.IService;");
            pw.println();
            pw.println("public interface " + className + " extends IService<" + entityName + "> {");
            pw.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateServiceImpl(Table table, String path, String pkg, String servicePkg, String mapperPkg, String entityPkg) {
        String className = "Table" + table.id + "ServiceImpl";
        String serviceName = "Table" + table.id + "Service";
        String mapperName = "Table" + table.id + "Mapper";
        String entityName = "Table" + table.id;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".java", StandardCharsets.UTF_8))) {
            pw.println("package " + pkg + ";");
            pw.println();
            pw.println("import " + entityPkg + "." + entityName + ";");
            pw.println("import " + mapperPkg + "." + mapperName + ";");
            pw.println("import " + servicePkg + "." + serviceName + ";");
            pw.println("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;");
            pw.println("import org.springframework.stereotype.Service;");
            pw.println();
            pw.println("@Service");
            pw.println("public class " + className + " extends ServiceImpl<" + mapperName + ", " + entityName + "> implements " + serviceName + " {");
            pw.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateController(Table table, String path, String pkg, String servicePkg, String entityPkg) {
        String className = "Table" + table.id + "Controller";
        
        // Identify external dependencies
        Set<String> externalTables = new HashSet<>();
        for (Formula f : table.formulas) {
            if (f.externalTable != null) {
                externalTables.add(f.externalTable);
            }
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".java", StandardCharsets.UTF_8))) {
            pw.println("package " + pkg + ";");
            pw.println();
            pw.println("import " + entityPkg + ".*;");
            pw.println("import " + servicePkg + ".*;");
            pw.println("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;");
            pw.println("import org.springframework.beans.factory.annotation.Autowired;");
            pw.println("import org.springframework.web.bind.annotation.*;");
            pw.println("import java.util.List;");
            pw.println("import java.util.Map;");
            pw.println("import java.util.HashMap;");
            pw.println();
            pw.println("@RestController");
            pw.println("@RequestMapping(\"/api/table" + table.id + "\")");
            pw.println("public class " + className + " {");
            pw.println();
            pw.println("    @Autowired");
            pw.println("    private Table" + table.id + "Service service;");
            pw.println();
            
            for (String extId : externalTables) {
                pw.println("    @Autowired");
                pw.println("    private Table" + extId + "Service service" + extId + ";");
            }
            if (!externalTables.isEmpty()) {
                pw.println();
            }
            
            pw.println("    @PostMapping");
            pw.println("    public boolean save(@RequestBody Table" + table.id + " entity) {");
            pw.println("        return service.saveOrUpdate(entity);");
            pw.println("    }");
            pw.println();
            pw.println("    @GetMapping");
            pw.println("    public List<Table" + table.id + "> list() {");
            pw.println("        return service.list();");
            pw.println("    }");
            
            if (!externalTables.isEmpty()) {
                pw.println();
                pw.println("    @GetMapping(\"/summary\")");
                pw.println("    public Map<String, Object> getSummary(@RequestParam String regionCode) {");
                pw.println("        Map<String, Object> result = new HashMap<>();");
                pw.println("        if (regionCode == null || regionCode.isEmpty()) return result;");
                pw.println();
                
                for (String extId : externalTables) {
                    pw.println("        Table" + extId + " entity" + extId + " = service" + extId + ".getOne(new QueryWrapper<Table" + extId + ">().eq(\"region_code\", regionCode).orderByDesc(\"report_date\").last(\"LIMIT 1\"));");
                    pw.println("        if (entity" + extId + " != null) {");
                    
                    // Populate fields
                    for (Formula f : table.formulas) {
                        if (extId.equals(f.externalTable)) {
                            String sourceCode = f.sources.get(0);
                            // Assuming getter name matches field code.
                            // Field code: B01055 -> getColB01055()
                            pw.println("            result.put(\"col" + f.target + "\", entity" + extId + ".getCol" + sourceCode + "());");
                        }
                    }
                    pw.println("        }");
                }
                
                pw.println("        return result;");
                pw.println("    }");
            }
            
            pw.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
