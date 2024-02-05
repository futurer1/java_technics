<a href="https://for-each.dev/lessons/b/-java-hashmap-sort#5-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%BB%D1%8F%D0%BC%D0%B1%D0%B4%D0%B0-%D0%B2%D1%8B%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9-%D0%B8-%D0%BF%D0%BE%D1%82%D0%BE%D0%BA%D0%BE%D0%B2">Сортировка HashMap через stream</a>

## Сортировка по ключу

```java
map.entrySet()
  .stream()
  .sorted(Map.Entry.<String, Employee>comparingByKey())
  .forEach(System.out::println);
```

## Сортировка по значению

```java
map.entrySet()
  .stream()
  .sorted(Map.Entry.comparingByValue())
  .forEach(System.out::println);
```

Пересборка в новую мапу:
```java
Map<String, Employee> result = map.entrySet()
  .stream()
  .sorted(Map.Entry.comparingByValue())
  .collect(Collectors.toMap(
    Map.Entry::getKey, 
    Map.Entry::getValue, 
    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
```

Работа с вложенной структурой:
```java
Map<Integer, List<Long>> inputMap = new HashMap<>();
inputMap.put(1, Arrays.asList(0L, 1L, 2L));
inputMap.put(2, Arrays.asList(3L, 4L));

Map<Long, Integer> outputMap = inputMap.entrySet().stream()
        .flatMap(entry -> {
            var key = entry.getKey();
            var value = entry.getValue();
            return value.stream().map(num -> Map.entry(num, key));
        })
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

System.out.println(outputMap); // {0=1, 1=1, 2=1, 3=2, 4=2}
```

Мапа в мапе
```java
HashMap<String, Integer> mapInner = new HashMap<>();
mapInner.put("a", 10);
mapInner.put("b", 12);
mapInner.put("c", 14);
mapInner.put("d", 16);

HashMap<String, Integer> mapInner1 = new HashMap<>();
mapInner1.put("a1", 20);
mapInner1.put("b1", 22);
mapInner1.put("c1", 24);
mapInner1.put("d1", 26);

HashMap<String, Integer> mapInner2 = new HashMap<>();
mapInner2.put("a2", 30);
mapInner2.put("b2", 32);
mapInner2.put("c2", 34);
mapInner2.put("d2", 36);

HashMap<String, HashMap<String, Integer>> myMap = new HashMap<>();
myMap.put("one", mapInner);
myMap.put("two", mapInner1);
myMap.put("three", mapInner2);


Map<String, String> res = myMap
        .entrySet()
        .stream()
        .flatMap(el -> {
                    var key = el.getKey(); // "one", "two", "three"
                    var innerMapValue = el.getValue();
                    return innerMapValue
                            .entrySet()
                            .stream()
                            .map(num -> Map.entry(num.getKey() + "-" + num.getValue(), key));

                })
        .collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                )
        );
System.out.println(res);
```
