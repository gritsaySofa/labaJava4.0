package org.example;

import java.util.List;

public class Main {
    private static final String CSV_FILE = "aaa.csv";
    private static final char DELIMITER = ';';

    public static void main(String[] args) {
        try {
            CsvWorkerParser parser = new CsvWorkerParser(DELIMITER);
            List<Worker> workers;
            workers = parser.parseWorkersFromCSV(CSV_FILE);
            workers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}