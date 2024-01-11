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
- `distinct` - убрать дубликаты, [пример](#distinct)
- `map()` - преобразование каждого элемента в отдельности через реализацию функционального интерфейса. Может быть изменен тип элементов.
- `flatMap` - создает один stream из множества стримов ([например](#flatMap), из коллекции потоков)
- `sorted()` - сортировка элементов по возрастанию. Можно предоставить собственный компаратор в качестве аргумента метода. [пример](#sorted)
- `limit(n)` - ограничить кол-во элементов результативного потока
- `skip(n)` - пропустить первые n элементов
- `takeWhile(Predicate)` - элементы будут перебираться до тех пор, пока удовлетворяют условию предиката. [пример](#takeWhile)
- `dropWhile(Predicate)` - элементы будут отбрасываться, пока не встретится элемент удовлетворяющий условию предиката. [пример](#dropWhile)
- `peek(Consumer)` - применяет операцию к каждому элементу потока (логирование, отладка, профилирование). С многопоточностью проблемы.
- `reduce()` - уменьшение кол-ва элементов потока в одно значение

### Терминальные операции

После терминальной операции больше нельзя работать со стримом, т.к. она заканчивает обработку потока.
К терминальным относятся:
- `forEach(Consumer)` - выполнить действие над каждым элементом, например, распечатать System.out::println
- `count()` - количество элементов в потоке
- `сollect(Collector)` - накопление результатов стрима, сбор результатов в структуру (List, Set, Map). [пример](#сollect)

### Spliterator
Разделяет коллекцию на части (нужно для параллельной обработки).
В его основе лежит интерфейс Iterator. 
Методы `parallelStream()` и `parallel()` для параллельной обработки. Местоположение этого метода в потоке неважно.

### Примеры

<a name="flatMap"></a>flatMap
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

<a name="distinct"></a>distinct
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 1, 4, 5, 3, 5);

List<Integer> uniqueNumbers = numbers.stream()
                                     .distinct()
                                     .collect(Collectors.toList());

System.out.println(uniqueNumbers); // prints [1, 2, 3, 4, 5]
```

<a name="sorted"></a>sorted
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

List<String> sortedNames = names.stream()
                                .sorted()
                                .collect(Collectors.toList());

System.out.println(sortedNames); // prints ["Alice", "Bob", "Charlie", "David"]
```

<a name="takeWhile"></a>takeWhile
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

List<Integer> takenNumbers = numbers.stream()
                                     .takeWhile(n -> n < 5)
                                     .collect(Collectors.toList());

System.out.println(takenNumbers); // prints [1, 2, 3, 4]
```

<a name="dropWhile"></a>dropWhile
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

List<Integer> droppedNumbers = numbers.stream()
                                       .dropWhile(n -> n < 5)
                                       .collect(Collectors.toList());

System.out.println(droppedNumbers); // prints [5, 6, 7, 8, 9, 10]
```

<a name="сollect"></a>сollect
```java
Stream<?> stream;
List<?> list = stream.collect(Collectors.toList());

//Коллектор выше аналогичен данному коду
list = stream.collect(
        () -> new ArrayList<>(), // определяем структуру накопителя (Supplier)
        (list, t) -> list.add(t), // определяем, как добавлять элементы
        (l1, l2) -> l1.addAll(l2) // и как объединять две структуры в одну
);
```
