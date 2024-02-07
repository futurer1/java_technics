
Создание и запуск поторок с помощью стрима.
(Используется side-эффект операции peek)
```java
class Counter {
  volatile int x = 0;
}
Counter c = new Counter();

Runnable r = () -> {
  for (int i = 0; i < 1000000; i++) c.x++;
};

List<Thread> threads = Stream.generate(() -> new Thread(r))
    .limit(10).peek(Thread::start)
    .collect(Collectors.toList());

for (Thread thread: threads) {
  thread.join();
}
```
