package advanced1;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueExample {
    public static void main(String[] args) {
        example1();
    }

    public static void example1()
    {
        // очередь FIFO (first in first out)
        Queue<Person2> people = new ArrayBlockingQueue<>(4); // 4 - лимит вместительности очереди
        people.add(new Person2(2));
        people.add(new Person2(3));
        people.add(new Person2(1));
        people.add(new Person2(4));

        System.out.println(people.remove()); // вернул и удалил
        System.out.println(people.peek()); // вернул без удаления
        System.out.println(people);

        people.add(new Person2(5));
        try {
            people.add(new Person2(6));
        } catch (IllegalStateException e) {
            System.out.println("Элемент не добавлен, по причине " + e.getMessage());
        }

        boolean result = people.offer(new Person2(6)); // элемент тоже не добавлен, но без exception
        System.out.println(result);
        System.out.println(people);

        /*
        Вывод:
        
        Person2{id=2}
        Person2{id=3}
        [Person2{id=3}, Person2{id=1}, Person2{id=4}]
        Элемент не добавлен, по причине Queue full
        false
        [Person2{id=3}, Person2{id=1}, Person2{id=4}, Person2{id=5}]

        */

    }
}

class Person2 {
    private int id;

    public Person2(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                '}';
    }
}
