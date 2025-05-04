package org.example;
import java.util.Objects;


/**
 * Класс, представляющий ID отдела.
 * Генерирует уникальные ID для каждого отдела.
 */
public class DepartmentID {
    private static int nextId = 1;
    private final int id;
    private final String name;

    /**
     * Создает новый идентификатор отдела.
     *
     * @param name название отдела
     */
    public DepartmentID(String name) {
        this.id = nextId++;
        this.name = name;
    }
    /**
     * Сбрасывает счётчик ID
     */
    public static void resetCounter() {
        nextId = 1;
    }

    /**
     * @return числовой идентификатор отдела
     */
    public int getId() {
        return id;
    }
    /**
     * @return название отдела
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentID that = (DepartmentID) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}