package thread;

import java.util.Random;

/**
 * Поток 1 запускается и после сна 3 сек начинает работу
 * Поток 2 останавливает Поток 1 через случайные промежутки времени
 * - если прерывание произошло в процессе сна Потока 1, то возникает InterruptedException
 * - если прерывание произошло в процессе вычислений, то поток завершается штатно по break на 29 строке
 */
public class Thread14 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; i++) {

                if (i == 0) {
                    try {
                        System.out.println("Поток 1 заснул на 3 секунды");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Поток 1 прервали в процессе сна");
                        break;
                    }
                }

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Поток 1 прервали в ходе вычислений");
                    break;
                }

                Random random = new Random();
                Math.cos(random.nextDouble());
            }
        });

        Thread thread2 = new Thread(() -> {
            Random random = new Random();
            int millis = random.nextInt(7);

            System.out.println("Поток 2 ждет " + millis + " сек.");
            try {
                Thread.sleep(millis * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            thread1.interrupt();
        });

        thread1.start(); // запустили потоки
        thread2.start();

        thread1.join(); // добавили потоки к основному
        thread2.join();

        System.out.println("Завершился main поток");
    }
}
