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


/**
 * Для преобразования данных из CSV-файла в список объектов Worker.
 */
public class CsvWorkerParser {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final char delimiter;

    /**
     * Создает парсер CSV с указанным разделителем.
     *
     * @param delimiter разделитель в CSV-файле
     */
    public CsvWorkerParser(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Парсит CSV-файл и возвращает список работников.
     * @param csvFile имя CSV-файла в ресурсах
     * @return список объектов Worker
     * @throws IOException если возникла ошибка чтения файла
     * @throws CsvException если возникла ошибка парсинга CSV
     * @throws ParseException если возникла ошибка парсинга даты
     */
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

    /**
     * Преобразует строку CSV в объект Worker.
     * @param csvLine массив значений из строки CSV
     * @param departments словарь отделов для избежания дублирования
     * @return объект Worker
     * @throws ParseException если возникла ошибка парсинга даты
     * @throws IllegalArgumentException если строка имеет неверный формат
     */
    public Worker parseWorker(String[] csvLine, Map<String, DepartmentID> departments) throws ParseException {
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