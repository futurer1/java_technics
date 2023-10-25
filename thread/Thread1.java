package thread;

public class Thread1 {
    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
    }

    /**
     * Создание потока через класс
     */
    public static void example1() {
        MyThread myThread = new MyThread();
        myThread.start(); // создаёт новый поток и запускает метод run()

        MyThread myThread2 = new MyThread();
        myThread2.start();
    }

    /**
     * Создание потока через Runnable
     */
    public static void example2() {
        Thread myThread = new Thread(new Runner());
        myThread.start();
    }

    /**
     * Создание потока через Runnable лямбда синтаксис
     */
    public static void example3() {
        Thread myThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("From example3 thread " + i);
            }
        });
        myThread.start();
    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("From example2 thread " + i);
        }
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("From example1 thread " + i);
        }
    }
}
