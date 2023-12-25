package java_technics.interview;

import java.util.*;

public class Threads {

    public static void main(String[] args) {

        // потоки
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread = new Thread(() -> {


            threadLocal.set("Aaaaaa");                // поток thread заюзал threadLocal
            inheritableThreadLocal.set("Bbbbbbb");    // поток thread заюзал inheritableThreadLocal

            System.out.println("\nMajor thread");
            System.out.println(threadLocal.get());            // доступно, выводит значение
            System.out.println(inheritableThreadLocal.get()); // доступно, выводит значение

            Thread threadInner = new Thread(() -> {            // вложенный поток

                System.out.println("\nChild thread");
                System.out.println(threadLocal.get());            // НЕдоступно, выводит null
                System.out.println(inheritableThreadLocal.get()); // доступно, выводит значение
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

        Thread thread1 = new Thread(() -> {        // новый поток на одном уровне с потоком thread

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\nAnother major thread");
            System.out.println(threadLocal.get());            // НЕдоступно, выводит null (уже было занято потоком thread)
            System.out.println(inheritableThreadLocal.get()); // НЕдоступно, выводит null (уже было занято потоком thread)
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
