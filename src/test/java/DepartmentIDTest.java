import org.example.DepartmentID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentIDTest {

    @BeforeEach
    void resetCounter() {

        DepartmentID.resetCounter();
    }
    @Test
    void testDepartmentCreation() {
        DepartmentID department = new DepartmentID("IT");
        assertEquals("IT", department.getName());
        assertEquals(1, department.getId());;
    }

    @Test
    void testEqualsAndHashCode() {
        DepartmentID.resetCounter();
        DepartmentID dept1 = new DepartmentID("HR");

        DepartmentID.resetCounter();
        DepartmentID dept2 = new DepartmentID("HR");

        DepartmentID dept3 = new DepartmentID("Finance");

        // Теперь оба объекта будут иметь одинаковые ID
        assertEquals(dept1, dept2);
        assertNotEquals(dept1, dept3);
        assertEquals(dept1.hashCode(), dept2.hashCode());
    }

    @Test
    void testIdIncrement() {
        DepartmentID.resetCounter();

        DepartmentID first = new DepartmentID("First");
        DepartmentID second = new DepartmentID("Second");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
    }
}