import org.example.DepartmentID;
import org.example.Worker;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    private DepartmentID createTestDepartment() {
        return new DepartmentID("TestDept");
    }

    @Test
    void testWorkerCreation() {
        DepartmentID department = createTestDepartment();
        Date birthDate = new Date();
        Worker worker = new Worker(1, "John", "Male", department, 5000.0, birthDate);

        assertEquals(1, worker.getId());
        assertEquals("John", worker.getName());
        assertEquals("Male", worker.getGender());
        assertEquals(department, worker.getDepartment());
        assertEquals(5000.0, worker.getSalary());
        assertEquals(birthDate, worker.getBirthDate());
    }

    @Test
    void testEqualsAndHashCode() {
        DepartmentID dept = createTestDepartment();
        Date date = new Date();

        Worker worker1 = new Worker(1, "Alice", "Female", dept, 4000.0, date);
        Worker worker2 = new Worker(1, "Alice", "Female", dept, 4000.0, date);
        Worker worker3 = new Worker(2, "Bob", "Male", dept, 4500.0, date);

        assertEquals(worker1, worker2);
        assertNotEquals(worker1, worker3);
        assertEquals(worker1.hashCode(), worker2.hashCode());
        assertNotEquals(worker1.hashCode(), worker3.hashCode());
    }

    @Test
    void testToString() {
        DepartmentID dept = createTestDepartment();
        Worker worker = new Worker(3, "Eve", "Female", dept, 3500.0, new Date());

        String str = worker.toString();
        assertTrue(str.contains("Eve"));
        assertTrue(str.contains("Female"));
        assertTrue(str.contains("TestDept"));
        assertTrue(str.contains("3500.0"));
    }
}