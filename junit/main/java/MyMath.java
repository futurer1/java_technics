public class MyMath {
    public static double devide(int n1, int n2) {
        if (n2 == 0)
            throw new ArithmeticException("Devision by zero.");
        return n1 / n2;
    }

    public static int calculateLong() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
