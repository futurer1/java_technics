package thread;

import java.util.Scanner;

public class Thread7 {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer started");

            // вызывается на объекте this (на котором синхронизация)
            // и только внутри синхронизованного блока
            this.wait(); // освобождаем intrinsic lock; ждем вызов notify() на объекте текущего контекста (this)
            // wait(1000); // милисекунды
            System.out.println("Producer resumed");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            System.out.println("Waiting for return");
            scanner.nextLine();
            this.notify(); // уведомляем один потоки, которые ждут на этом объекте.
            // если this не указан, то будет использован текущий контекст объекта, который был использован для синхронизации
            // Выполнение продолжит другой поток, который ждал (строка 47)

            // после вызова notify() монитор не освобождается
            Thread.sleep(5000); // это будет выполнено до перехода на другой поток
            // notifyAll(); - делает уведомление всех потоков, которые ждут на этом объекте

            // монитор освободится здесь только после завершения текущей секции synchronized
        }
    }
}
