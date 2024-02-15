```java

public class Sum {
    public static void main(String[] args) {

        String[] numbers = "1 5 2 7 3" . split(" ");

        Integer summ = Arrays
        .stream(numbers)
        .reduce(0, (integer, string) -> (integer + Integer.parseInt(string)), Integer::sum);

        Integer summ1 = Arrays.stream(numbers)
                        .mapToInt(Integer::valueOf)
                        .sum();

        Integer summ2 = Arrays.stream(numbers)
                .mapToInt(Integer::valueOf)
                .reduce(0, ArithmeticUtils::add);

        Integer max = Arrays.stream(numbers)
                .mapToInt(Integer::valueOf)
                .max()
                .orElse(0);
}

public static class ArithmeticUtils {

    public static int add(int a, int b) {
        return a + b;
    }
}
```
