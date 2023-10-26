package thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Два потока
 * Один переводит на баланс клиента 2 случайные суммы
 * Второй переводит на баланс клиента 1 случайные суммы
 * Общий баланс остаётся неизменным
 * Отличие от Thread12 в том, что оба лока делаются в отдельном методе, 
 * который через tryLock() не допускает dead lock
 */
public class Thread13 {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Account1 client1 = new Account1();
        Account1 client2 = new Account1();



        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                takeLocks(lock1, lock2);
                Random randSum = new Random();
                try {
                    Processor1.transferSum(client1, client2, randSum.nextInt(10));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                takeLocks(lock2, lock1);

                Random randSum = new Random();
                try {
                    Processor1.transferSum(client2, client1, randSum.nextInt(10));
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

    /**
     * Разруливает локи через tryLock() и не допускает dead lock
     *
     * @param lock1
     * @param lock2
     */
    private static void takeLocks(ReentrantLock lock1, ReentrantLock lock2) {
        boolean lock1taken = false;
        boolean lock2taken = false;

        while (true) {
            // пытаемся сделать 2 лока, пока не получится

            try {
                // попытка сделать 2 лока
                lock1taken = lock1.tryLock();
                lock2taken = lock2.tryLock();
            } finally {
                if (lock1taken && lock2taken) {
                    // оба лока удалось сделать
                    return;
                }

                if (lock1taken) {
                    // освобождаем первый лок, который удалось сделать без второго
                    lock1.unlock();
                }
                if (lock2taken) {
                    // освобождаем второй лок, который удалось сделать без первого
                    lock2.unlock();
                }
            }

            try {
                // пауза для возможности другому потоку изменить состояние
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Processor1 {
    public static void transferSum (Account1 client1, Account1 client2, int sum) {
        client1.reduceSum(sum);
        client2.addSum(sum);
    }
}

class Account1 {
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
