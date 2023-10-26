package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Использование интерфейса Callable для возврата результата из потока,
 * а также выбрасывание исключения из потока наружу
 */
public class Thread15 {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<Integer> future = es.submit(() -> {
            System.out.println("Begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End");
            Random rand = new Random();
            int randVal = rand.nextInt(100);

            if (randVal > 40)
                throw new Exception("Wrong rand value " + randVal);

            return randVal;
        });

        es.shutdown();

        try {
            Integer result = future.get(); // дожидается завершения потока
            // вывод полученного из потока результата
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // выброшенное в потоке исключение
            Throwable exception = e.getCause();
            System.out.println(exception.getMessage());
        }
    }
}
