package advanced1;

import java.util.*;

/**
 * Использование интерфейса Comparable с реализацией его метода compareTo
 * для того, чтобы работал стандартный метод Collections.sort
 */
public class ComparableExample {
    public static void main(String[] args) {
        example1();
    }

    public static void example1() {
        List<Person1> listUsers = new ArrayList<>();
        Set<Person1> setUsers = new TreeSet<>();

        addElements(listUsers);
        addElements(setUsers);

        Collections.sort(listUsers);
        System.out.println(listUsers);

        System.out.println(setUsers);
    }

    public static void addElements(Collection<Person1> c) {
        c.add(new Person1(3, "Vasya"));
        c.add(new Person1(2, "Petya"));
        c.add(new Person1(1, "Kolya"));
    }
}

class Person1 implements Comparable<Person1> {
    private int id;
    private String name;

    public Person1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person = (Person1) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Person1 o) {
        if (this.id > o.getId()) {
            return 1;
        } else if (this.id < o.getId()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + "'";
    }
}
