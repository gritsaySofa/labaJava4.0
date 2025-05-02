package org.example;
import java.util.Date;
import java.util.Objects;

public class Worker {
    private final int id;
    private final String name;
    private final String gender;
    private final DepartmentID department;
    private final double salary;
    private final Date birthDate;

    public Worker(int id, String name, String gender, DepartmentID department, double salary, Date birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public DepartmentID getDepartment() { return department; }
    public double getSalary() { return salary; }
    public Date getBirthDate() { return birthDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                Double.compare(worker.salary, salary) == 0 &&
                Objects.equals(name, worker.name) &&
                Objects.equals(gender, worker.gender) &&
                Objects.equals(department, worker.department) &&
                Objects.equals(birthDate, worker.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, department, salary, birthDate);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                '}';
    }
}