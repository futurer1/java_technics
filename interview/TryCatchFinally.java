package java_technics.interview;

public class TryCatchFinally {

        public static void main(String[] args) {
        
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
                System.out.println(integerSupplier.get()); // всегда будет 3
                
                
                var obj = new Test();
                try {
                    obj.testFinally();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }

        public void testFinally() throws URISyntaxException, FileNotFoundException {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File("test.txt"));
                    while (scanner.hasNext()) {
                        System.out.println(scanner.nextLine());
                        throw new RuntimeException("Something happened.");
                    }
                } finally {
                    if (scanner != null) {
                        scanner.close();
                        throw new RuntimeException("Something happened close."); // если в finally есть catch, то он будет выбрасываться в первую очередь и заменит собой catch в блоке try
                    }
                }
        }
}
