package thread;

public class Thread3 {

    private int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread3 test = new Thread3();
        test.example1();
    }

    /**
     * Потоки дожидаются своей очереди, чтобы вызвать тело метода increment()
     * synchronized может быть только у метода
     */
    public synchronized void increment() {
        counter++;
    }

    public void example1() throws InterruptedException {
      
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();
        // возникает race condition

        thread1.join(); // добавляем ожидание завершения потока
        thread2.join();

        System.out.println(counter);
    }
}
