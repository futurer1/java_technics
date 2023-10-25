package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Thread5 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            // Добавление 5 заданий.
            // Эти задания Work должны реализовывать интерфейс Runnable
            executorService.submit(new Work(i));
        }

        // постановка новых заданий завершена, выполнение начинается
        executorService.shutdown();
        // Выполнение будет происходить с равномерным распределением
        // заданий между доступным кол-вом потоков в пуле

        System.out.println("Все задания назначены");

        // время, которое ожидается завершение всех потоков в пуле
        // это время ожидания результата (потоки могут не успеть доработать),
        // но сами потоки executorService продолжат работать дальше, это их не останавливает
        // спустя это время TimeUnit.MINUTES (или досрочно) продолжает работать поток main
        Boolean result = executorService.awaitTermination(6, TimeUnit.SECONDS);
        if (result) {
            System.out.println("Вся работа была выполнена");
        } else {
            System.out.println("Потоки не успели выполнить всю возложенную на них работу");
        }

        System.out.println("Поток main завершил свою работу и ждёт завершения дочерних потоков");

        /*
        Вывод:

        Все задания назначены
        Work id 1 was completed
        Work id 0 was completed
        Потоки не успели выполнить всю возложенную на них работу
        Поток main завершил свою работу и ждёт завершения дочерних потоков
        Work id 2 was completed
        Work id 3 was completed
        Work id 4 was completed

        Process finished with exit code 0
        */
    }
}

class Work implements Runnable {
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Work id " + id + " was completed");
    }
}
