import com.opencsv.exceptions.CsvException;
import org.example.CsvWorkerParser;
import org.example.Worker;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CsvWorkerParserTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final String TEST_CSV = "test_workers.csv";

    @Test
    void testParseWorkersFromCSV() throws IOException, CsvException, ParseException {
        CsvWorkerParser parser = new CsvWorkerParser(';');
        List<Worker> workers = parser.parseWorkersFromCSV(TEST_CSV);

        assertNotNull(workers);
        assertFalse(workers.isEmpty());

        // Проверяем первого работника
        Worker firstWorker = workers.get(0);
        assertEquals(28281, firstWorker.getId());
        assertEquals("Aahan", firstWorker.getName());
        assertEquals("Male", firstWorker.getGender());
        assertEquals(DATE_FORMAT.parse("15.05.1970"), firstWorker.getBirthDate());
        assertEquals("I", firstWorker.getDepartment().getName());
        assertEquals(4800.0, firstWorker.getSalary());
    }

    @Test
    void testParseWorker() throws ParseException {
        CsvWorkerParser parser = new CsvWorkerParser(';');
        String[] testData = {
                "101", "TestName", "TestGender", "01.01.2000", "TestDept", "1234.56"
        };

        Worker worker = parser.parseWorker(testData, new java.util.HashMap<>());

        assertEquals(101, worker.getId());
        assertEquals("TestName", worker.getName());
        assertEquals("TestGender", worker.getGender());
        assertEquals(DATE_FORMAT.parse("01.01.2000"), worker.getBirthDate());
        assertEquals("TestDept", worker.getDepartment().getName());
        assertEquals(1234.56, worker.getSalary());
    }
}
