package com.project.PriceComparatorMarket.Services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DataImportDb {

    @Bean
    public CommandLineRunner importCvs(CsvService csvService) {
        return args -> {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:data/*.csv");

            DateTimeFormatter filenameFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                try (InputStream file = resource.getInputStream()) {
                    if (fileName.contains("_discounts_")) {
                        String[] line = fileName.split("_discounts_");
                        String store = line[0].trim();
                        String dateStr = line[1].replace(".csv", "");
                        LocalDate date = LocalDate.parse(dateStr, filenameFormatter);
                        csvService.importCsvDiscount(file, store,date);
                    } else {
                        String[] line = fileName.split("_");
                        String store = line[0].trim();
                        String dateStr = line[1].replace(".csv", "");
                        LocalDate date = LocalDate.parse(dateStr, filenameFormatter);
                        csvService.importCsv(file, store, date);
                    }
                } catch (Exception e) {
                    System.err.println(" Error importing " + fileName + ": " + e.getMessage());
                }
            }
        };
    }
}
