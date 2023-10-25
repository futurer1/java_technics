package thread;

import java.util.Scanner;

public class Thread2 {
    public static void main(String[] args) {
        example1();
    }

    /**
     * Ввод в консоль останавливает поток MyThread2
     */
    public static void example1() {
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // ждем ввод в консоль
        myThread2.shutdown();
    }
}

class MyThread2 extends Thread {
    /**
     * условие работы потока. Значение volatile, поэтому не кешируется.
     */
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("MyThread2 is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        System.out.println("MyThread2 is shutting down...");
        running = false;
    }
}
