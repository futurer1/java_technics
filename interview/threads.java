package java_technics.interview;

import java.util.*;

public class Test {

    public static void main(String[] args) {

// потоки
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread = new Thread(() -> {


            threadLocal.set("Aaaaaa");
            inheritableThreadLocal.set("Bbbbbbb");

            System.out.println("\nMajor thread");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread threadInner = new Thread(() -> {

                System.out.println("\nChild thread");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
            });
            threadInner.start();
            try {
                threadInner.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
        }

        Thread thread1 = new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\nAnother major thread");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
