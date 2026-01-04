package com.evaluate.analysis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

@SpringBootApplication
@MapperScan("com.evaluate.analysis.mapper")
public class DataAnalysisApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DataAnalysisApplication.class)
            .web(WebApplicationType.SERVLET)
            .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Database Connection Successful!");
                System.out.println("Existing Tables:");
                try (ResultSet rs = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"})) {
                    while (rs.next()) {
                        System.out.println(" - " + rs.getString("TABLE_NAME"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
