Assertions (утверждения) позволяют сравнить ожидаемый результат с фактическим результатом теста.
          методы в класса org.junit.jupiter.Assertions

Класс Assumptions (предположения) предоставляет staticметоды для поддержки выполнения условного теста на основе предположений. Неуспешное предположение приводит к прерыванию теста.
          Предположения обычно используются всякий раз, когда нет смысла продолжать выполнение данного метода тестирования. В отчете о тестировании эти тесты будут отмечены как пройденные.
          Предположения класс имеет три таких методов: assumeFalse(), assumeTrue() и assumingThat()

```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.howtodoinjava.junit5.examples.Calculator;

public class AppTest {

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }

    @Tag("DEV")
    @Test
    void testCalcOne()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals( 4 , Calculator.add(2, 2));
    }

    @Tag("PROD")
    @Disabled
    @Test
    void testCalcTwo()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals( 6 , Calculator.add(2, 4));
    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}
```
