
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.charset.StandardCharsets;

public class SchemaParser {

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

        public Table(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        String inputFile = "extracted.txt";
        String outputFile = "init.sql";

        List<Table> tables = new ArrayList<>();
        Table currentTable = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
            String line;
            List<String> previousLines = new ArrayList<>();
            // Relaxed regex
            Pattern tableIdPattern = Pattern.compile("表\\s*号\\s*[：:]\\s*([A-Z][0-9]+)");
            Pattern fieldPattern = Pattern.compile("^(.*?)\\s+([^\\s]+)\\s+([A-Z][0-9]{5,})$");

            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                lineCount++;
                if (lineCount < 10) System.out.println("Debug Line: " + line);
                
                // Debug if line contains "表 号"
                if (line.contains("表") && line.contains("号")) {
                     System.out.println("Potential Table ID Line: " + line);
                }

                // Check for Table ID
                Matcher idMatcher = tableIdPattern.matcher(line);
                if (idMatcher.find()) {
                    String id = idMatcher.group(1);
                    System.out.println("Found Table: " + id);
                    currentTable = new Table(id);
                    tables.add(currentTable);
                    
                    // Try to find table name
                    for (int i = previousLines.size() - 1; i >= 0 && i >= previousLines.size() - 20; i--) {
                        String prev = previousLines.get(i);
                        // Relaxed name pattern
                        if (prev.contains("表") && (prev.startsWith("（") || prev.startsWith("("))) {
                            currentTable.name = prev;
                            break;
                        }
                    }
                }

                // Check for Field
                if (currentTable != null) {
                    Matcher fieldMatcher = fieldPattern.matcher(line);
                    if (fieldMatcher.find()) {
                        String name = fieldMatcher.group(1);
                        String unit = fieldMatcher.group(2);
                        String code = fieldMatcher.group(3);
                        // Ensure the code belongs to the current table (starts with same prefix usually)
                        // Z01 -> Z01001. G06 -> G06001.
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate SQL
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
            pw.println("-- Database Schema for Natural Disaster Loss Statistics");
            pw.println("CREATE DATABASE IF NOT EXISTS evaluate_db;");
            pw.println("USE evaluate_db;");
            pw.println();

            for (Table table : tables) {
                pw.println("-- Table: " + (table.name != null ? table.name : table.id));
                pw.println("CREATE TABLE IF NOT EXISTS table_" + table.id + " (");
                pw.println("    id BIGINT AUTO_INCREMENT PRIMARY KEY,");
                pw.println("    report_id VARCHAR(50) NOT NULL COMMENT '报告ID',");
                pw.println("    region_code VARCHAR(20) NOT NULL COMMENT '行政区划代码',");
                pw.println("    report_date DATETIME NOT NULL COMMENT '报告日期',");
                
                for (Field field : table.fields) {
                    // Use code as column name
                    // Escape name for comment
                    String comment = field.name + " (" + field.unit + ")";
                    comment = comment.replace("'", "\\'");
                    pw.println("    col_" + field.code + " DECIMAL(18,2) DEFAULT 0 COMMENT '" + comment + "',");
                }
                
                pw.println("    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
                pw.println("    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,");
                pw.println("    UNIQUE KEY uk_report_region (report_id, region_code)");
                pw.println(");");
                pw.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Generated " + tables.size() + " tables.");
    }
}
