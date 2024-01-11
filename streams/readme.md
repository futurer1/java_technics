## Stream API

<a href="https://struchkov.dev/blog/ru/java-stream-api/" target="_blank">Полезный ресурс про Stream API</a>

Стрим не меняет исходные данные, он формирует новую структуру данных.

`stream()` - создание потока

Из коллекции:
```java
Collection<Integer> list = new ArrayList<>();
Stream<Integer> stream = list.stream();
```

Из массива:
```java
int[] numbers = {1, 2, 3};
Stream<Integer> stream = Arrays.stream(numbers).boxed();
```

Из строки:
```java
String str = "Hello";
IntStream stream = str.chars();
```

Из файла:
```java
Path path = Paths.get("file.txt");
Stream stream = Files.lines(path);
```

Из билдера:
```java
Stream.Builder builder = Stream.builder();
builder.add(1);
builder.add(2);
builder.add(3);
Stream stream = builder.build();
```

Stateless операции (без состояния) могут быть распараллелены и запускаются группами (объединение нескольких операций в секции).
Инициатором запуска (точкой синхронизации) секции становится операция statefull (с состоянием) следующая загруппой операций без состояния.
Statefull операция, которая требует наличия всех элементов стрима и производит над элементами общее действие (например, сортировку).

### Промежуточне операции 

Промежуточные операции в результате работы возвращают новый объект `Stream`.

- `filter()` - фильтрация элементов по предикату (Predicate)
- `distinct` - убрать дубликаты
- `map()` - преобразование каждого элемента в отдельности через реализацию функционального интерфейса. Может быть изменен тип элементов.
- `flatMap` - создает один stream из множества стримов ([например](flatMap), из коллекции потоков)
- `sorted()` - сортировка элементов

### Терминальные операции

После терминальной операции больше нельзя работать со стримом, т.к. она заканчивает обработку потока.
К терминальным относятся:
- `forEach(System.out::println)` - выполнить действие над каждым элементом
- `count()` - количество элементов в потоке
- `collect()` - накопление результатов стрима

### Spliterator
Разделяет коллекцию на части (нужно для параллельной обработки).
В его основе лежит интерфейс Iterator. 
Методы `parallelStream()` и `parallel()` для параллельной обработки. Местоположение этого метода в потоке неважно.

### Примеры

[flatMap]
```java
List<List<Integer>> listOfLists = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(4, 5, 6),
        Arrays.asList(7, 8, 9)
);

Stream<Integer> flattenedStream = listOfLists.stream()
        .flatMap(Collection::stream);

flattenedStream.forEach(System.out::println); // prints 1, 2, 3, 4, 5, 6, 7, 8, 9
```
