// primitive Streams
        System.out.println("\nPrimitive Streams");

        Integer intStreamValue = IntStream.range(1, 10).sum();
        System.out.println("\n" + intStreamValue);
        var listValue = IntStream.rangeClosed(1, 10)
                .filter(i -> i % 2 != 0)
                .boxed()
                .toList();
        System.out.println(listValue);
