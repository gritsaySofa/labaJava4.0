package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvWorkerParser {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final char delimiter;

    public CsvWorkerParser(char delimiter) {
        this.delimiter = delimiter;
    }

    public List<Worker> parseWorkersFromCSV(String csvFile) throws IOException, CsvException, ParseException {
        List<Worker> workers = new ArrayList<>();
        Map<String, DepartmentID> departments = new HashMap<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile);
             InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
             CSVReader reader = new CSVReaderBuilder(inputStreamReader)
                     .withSkipLines(1)  // Пропускаем заголовок
                     .withCSVParser(new com.opencsv.CSVParserBuilder()
                             .withSeparator(delimiter)
                             .build())
                     .build()) {

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    Worker worker = parseWorker(nextLine, departments);
                    workers.add(worker);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + Arrays.toString(nextLine));
                    e.printStackTrace();
                }
            }
        }
        return workers;
    }

    private Worker parseWorker(String[] csvLine, Map<String, DepartmentID> departments) throws ParseException {
        if (csvLine.length < 6) {
            throw new IllegalArgumentException("Invalid CSV line format. Expected 6 columns, got " + csvLine.length);
        }

        int id = Integer.parseInt(csvLine[0].trim());
        String name = csvLine[1].trim();
        String gender = csvLine[2].trim();
        Date birthDate = DATE_FORMAT.parse(csvLine[3].trim());
        String departmentName = csvLine[4].trim();
        double salary = Double.parseDouble(csvLine[5].trim());

        DepartmentID department = departments.computeIfAbsent(
                departmentName,
                n -> new DepartmentID(departmentName)
        );

        return new Worker(id, name, gender, department, salary, birthDate);
    }
}