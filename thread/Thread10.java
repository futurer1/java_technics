package thread;

import java.util.concurrent.locks.ReentrantLock;

public class Thread10 {

    public static void main(String[] args) throws InterruptedException {
        Test10 test = new Test10();

        Thread thread1 = new Thread(() -> {
            test.work1();
        });
        Thread thread2 = new Thread(test::work2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        test.result();
    }
}

class Test10 {
    private final ReentrantLock reentrantLock = new ReentrantLock(); // обеспечивает блокировку монитора
    private int counter;

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    public void work1() {
        reentrantLock.lock();
        increment();
        reentrantLock.unlock();
    }

    public void work2() {
        reentrantLock.lock();
        increment();
        reentrantLock.unlock();
    }

    public void result() {
        System.out.println(counter);
    }
}
