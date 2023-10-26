package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Thread11 {
    public static void main(String[] args) throws InterruptedException {
        expamle1();
        example2();
    }

    /**
     * Пример занятия и освобождения разрешений в семафоре
     */
    public static void expamle1() {
        int permits = 4;
        Semaphore semaphore = new Semaphore(permits); // максимальное кол-во разрешений на использование одновременно

        try {
            semaphore.acquire(); // занимает одно свободное разрешение
            semaphore.acquire(); // 2
            semaphore.acquire(); // 3
            semaphore.acquire(); // 4

            System.out.println("All permits acquired");
            //semaphore.acquire(); // будет ждать, если свободных нет

            //System.out.println("Can`t do this"); // это не выполнится никогда, если не освободится разрешение через release()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release(); // освобождение одного разрешения
        int available = semaphore.availablePermits(); // кол-во доступных разрешений для свободного использования
        System.out.println("Доступно: " + available + " из " + permits);
    }

    /**
     * Пример выполнения 200 потоков через семафор с пропускной способностью 10 потоков одновременно.
     *
     * @throws InterruptedException
     */
    public static void example2() throws InterruptedException {
        int threads = 200; // потоки в пуле
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        ConnectionSingleton connection = ConnectionSingleton.getConnection();

        for (int i = 0; i < threads; i++) { // каждому потоку по задаче
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.workWithSemaphore();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println(connection.getProgress());
    }
}

class ConnectionSingleton {
    private static Semaphore semaphore = new Semaphore(10); // 10 разрешений на одновременную работу
    private static ConnectionSingleton connection = new ConnectionSingleton();

    /**
     * Кол-во занятых потоками разрешений в семафоре
     */
    private int conCount;

    /**
     * Общий прогресс выполнения всех задач в потоках - объем работы равен кол-ву потоков.
     */
    private int progress;

    private ConnectionSingleton() {
        /* Объекты этого класса запрещено создавать */
    }

    public static ConnectionSingleton getConnection() {
        return connection;
    }

    public void workWithSemaphore() throws InterruptedException {
        semaphore.acquire(); // здесь останавливается и ждёт пока не освободится разрешение в семафоре
        try {
            work();
        } finally {
            semaphore.release(); // освобождает разрешение
        }
    }

    private void work() throws InterruptedException {
        synchronized (this) {
            conCount++;
            progress++;
            System.out.println("+" + conCount);
        }

        Thread.sleep(5000);

        synchronized (this) {
            conCount--;
        }
    }

    public int getProgress() {
        return progress;
    }
}
