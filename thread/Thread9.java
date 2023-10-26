package thread;

import java.util.concurrent.*;

public class Thread9 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3); // счетчик 3 шт.

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            // каждый поток уменьшит счетчик на 1
            executorService.submit(new Proc(countDownLatch));
        }
        executorService.shutdown(); // старт потоков
        countDownLatch.await(); // дожидаемся завершения счетчика
        System.out.println("Finish");
    }
}

class Proc implements Runnable {
    private final CountDownLatch countDownLatch;

    public Proc(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        countDownLatch.countDown(); // уменьшает счетчик на 1
    }
}
