package com.ssafy.starry.config;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@Component
public class CsvParseBean {

    public static List<Set<String>> foods = new ArrayList<>();

    public List<Set<String>> readCSV() {
        try {
            System.out.println("readCSV readCSV");

            for (int i = 0; i <= 7; i++) {
                foods.add(new HashSet<>());
            }

            ClassPathResource resource = new ClassPathResource("static/csv/food_name_csv.csv");

            CSVReader reader = new CSVReader(
                new InputStreamReader(new FileInputStream(resource.getFile()),
                    StandardCharsets.UTF_8));
            String[] nextLine;
            nextLine = reader.readNext(); // 목차 버리기
            while ((nextLine = reader.readNext()) != null) {   // 2
                String food = nextLine[1];
                foods.get(food.length()).add(food);
            }
            reader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return foods;
    }

    @PostConstruct
    private void initialize() {
        foods = readCSV();
    }

}
