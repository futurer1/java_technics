package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Thread4 {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
    }

}

class Worker {
    Random random = new Random();

    // Объект lock1, монитор которого будет использован для блокировки доступа к list1
    Object lock1 = new Object();

    // Объект lock2, монитор которого будет использован для блокировки доступа к list2
    Object lock2 = new Object();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    /**
     * В этом методе возникает race condition
     * Синхронизируемся по блокировке объекта lock1
     */
    public void addToList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }
    }

    /**
     * В этом методе возникает race condition
     * Синхронизируемся по блокировке объекта lock2
     */
    public void addToList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            list2.add(random.nextInt(100));
        }
    }

    public void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void main() {
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            work();
        });
        Thread thread2 = new Thread(this::work);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long after = System.currentTimeMillis();
        System.out.println("Execute time: " + (after - before));

        System.out.println("List1: " + list1.size());
        System.out.println("List1: " + list2.size());
    }
}
