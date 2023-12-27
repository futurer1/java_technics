List
----

```java
ArrayList<Integer> arrayList = new ArrayList<>();
// Поиск O(1)
// Вставка O(n)
// Удаление O(n)
```

```java
        LinkedList<Integer> linkedList = new LinkedList<>();
        // Поиск O(n)
        // Вставка O(1)
        // Удаление O(n) чтобы найти что удалять нужно проитерироваться n
        linkedList.add(1);
        Integer v1 = linkedList.get(0);
```

```java
        Stack<Integer> stack = new Stack<>();
        // Поиск O(n)
        // Вставка O(n)
        // Удаление O(n)
```

Set
---

```java
        HashSet<Integer> hashSet = new HashSet<>();
        // Поиск O(1)
        // Вставка O(1)
        // Удаление O(1)
        hashSet.add(1);
```

```java
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        // Поиск O()
        // Вставка O()
        // Удаление O()
        linkedHashSet.add(1);
```

```java
        TreeSet<Integer> treeSet = new TreeSet<>();
        // Поиск O(log(n))
        // Вставка O(log(n))
        // Удаление O(log(n))
        treeSet.add(1);
```

Queue
-----

```java
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.poll();
        // Поиск/получение O(log(n))
        // Вставка O(log(n))
        // Удаление O(log(n))
```

Map
---

```java
        Map<String, Integer> hashMap = new HashMap<>();
        // Получение O(1) hashMap.get("a");
        // Вставка O(1) hashMap.put("a", 1);
        // Удаление O(1) hashMap.remove(1);
```

```java
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        // Получение O(n)
        // Вставка O(1)
        // Удаление O(1)
```

```java
        Map<String, Integer> treeMap = new TreeMap<>();
        // Поиск ключа O(log(n)) поиск значения O(n)
        // Вставка O(log(n))
        // Удаление O(log(n))
```

```java
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, Integer> weakHashMap = new WeakHashMap<>();
```

```java
        Map<String, Integer> hashtable = new Hashtable<>();
        // Получение O(1)
        // Вставка O(1)
        // Удаление O(1)
```
