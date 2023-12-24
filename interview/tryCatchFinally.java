// try catch finally
        System.out.println("\nTry catch finally");

        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {

                try {
                    return 1;
                } catch (Exception e) {
                    return 2;
                } finally {
                    return 3;
                }
            }
        };
        System.out.println(integerSupplier.get());


        var obj = new Test();
        try {
            obj.testFinally();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
