package thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Thread6 {
    // потокобезопасная очередь FIFO
    // имеет вместимость 10
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {

        // поток 1 добавляет значения в очередь
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // потом 2 забирает значения из очереди
        Thread thread2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    /**
     * Продюсер публикует в очередь новые значения
     *
     * @throws InterruptedException
     */
    private static void produce() throws InterruptedException {
        Random rand = new Random();

        while (true) {
            Thread.sleep(200);

            // добавим задание в очередь, если это позволяет вместимость ArrayBlockingQueue
            queue.put(rand.nextInt(100));
        }
    }

    /**
     * Консьюмер забирает готовые результаты из очереди
     * @throws InterruptedException
     */
    private static void consumer() throws InterruptedException {
        Random rand = new Random();

        while (true) {
            Thread.sleep(110);

            // забирает из очереди задание
            // забираем чуть медленнее или так же, как пополняем очередь
            if (rand.nextInt(10) > 4) {
                System.out.println(queue.take());
            }

            System.out.println("Queue size: " + queue.size());
        }
    }
}
