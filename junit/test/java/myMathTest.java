import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class myMathTest {

    /* Тест обязательно должен выбросить исключение типа ArithmeticException */
    @Test(expected = ArithmeticException.class)
    public void zeroDenominationShouldThrowException() {
        MyMath.devide(1, 0);
    }

    /* Тест должен пройти за время не дольше 3000 мс */
    @Test(timeout = 3000)
    public void calculateLongTest() {
        int res = MyMath.calculateLong();
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Запуск перед всеми тестовыми методами");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Запуск после всех тестовых методов");
    }
}
