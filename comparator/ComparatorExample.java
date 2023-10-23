package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
    }

    public static void example1() {
        List<String> animals = new ArrayList<>();
        animals.add("dog");
        animals.add("cat");
        animals.add("d");
        animals.add("bird");
        animals.add("frog");
        animals.add("birdasas");

        Collections.sort(animals, new StringComp());
        System.out.println(animals);
        /*
         [d, dog, cat, bird, frog, birdasas]
         */

        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(30);
        numbers.add(-3);
        numbers.add(13);

        Collections.sort(numbers, new IntegerComp());
        System.out.println(numbers);
        /*
         [30, 13, 3, -3]
         */
    }

    public static void example2() {
        List<String> animals = new ArrayList<>();
        animals.add("dog");
        animals.add("cat");
        animals.add("d");
        animals.add("bird");
        animals.add("frog");
        animals.add("birdasas");

        Collections.sort(animals, ((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            }
            return 0;
        }));
        System.out.println(animals);
        /*
         [d, dog, cat, bird, frog, birdasas]
         */

        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(30);
        numbers.add(-3);
        numbers.add(13);

        Collections.sort(numbers, ((o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }
            return 0;
        }));
        System.out.println(numbers);
        /*
         [30, 13, 3, -3]
         */
    }

    public static void example3() {
        List<Person> users = new ArrayList<>();
        users.add(new Person(1, "Vasya"));
        users.add(new Person(4, "Petya"));
        users.add(new Person(3, "Kolya"));
        users.add(new Person(2, "Igor"));

        Collections.sort(users, ((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId() < o2.getId()) {
                return -1;
            }
            return 0;
        }));
        System.out.println(users);

    }
}

class StringComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        }
        return 0;
    }
}

class IntegerComp implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2) {
            return -1;
        } else if (o1 < o2) {
            return 1;
        }
        return 0;
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + "'" +
                "}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
