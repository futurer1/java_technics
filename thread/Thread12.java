package thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Два потока
 * Один переводит на баланс клиента 2 случайные суммы
 * Второй переводит на баланс клиента 1 случайные суммы
 * Общий баланс остаётся неизменным
 */
public class Thread12 {
    public static void main(String[] args) throws InterruptedException {
        Account client1 = new Account();
        Account client2 = new Account();

        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock1.lock();
                lock2.lock();
                Random randSum = new Random();
                try {
                    Processor.transferSum(client1, client2, randSum.nextInt(10));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock1.lock();
                lock2.lock();
                /*
                 Другой порядок блокировки у второго потока
                    lock2.lock();
                    lock1.lock();
                 вызовет dead lock
                 */

                Random randSum = new Random();
                try {
                    Processor.transferSum(client2, client1, randSum.nextInt(10));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Клиент 1: " + client1.getBalance());
        System.out.println("Клиент 2: " + client2.getBalance());
        System.out.println("Общий баланс: " + (client1.getBalance() + client2.getBalance()));
    }
}

class Processor {
    public static void transferSum (Account client1, Account client2, int sum) {
        client1.reduceSum(sum);
        client2.addSum(sum);
    }
}

class Account {
    private int balance = 1000;

    public void addSum(int sum) {
        balance += sum;
    }

    public void reduceSum(int sum) {
        balance -= sum;
    }

    public int getBalance() {
        return balance;
    }
}
