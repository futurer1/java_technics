## Assertions 
(утверждения) позволяют сравнить ожидаемый результат с фактическим результатом теста.
          методы в класса org.junit.jupiter.Assertions


Проверка на исключение:
```
Assertions.assertThrows(IllegalArgumentException.class, () -> {
    MyObj obj = new MyObj();
    obj.doSomething();
});
```

Библиотека для параметризованного теста:
```
junit-jupiter-params
```

Параметризированный тест выполняется так:
```
@ParametrizedTest
@ValueSource(ints = {-1, 0, 100})
public void myTest(int x) {
    MyObj obj = new MyObj();
    obj.doSomething1(x); // тест будет выполнен 3 раза с разным параметром x
}
```


## Assumptions 
(предположения) предоставляет staticметоды для поддержки выполнения условного теста на основе предположений. Неуспешное предположение приводит к прерыванию теста.
          Предположения обычно используются всякий раз, когда нет смысла продолжать выполнение данного метода тестирования. В отчете о тестировании эти тесты будут отмечены как пройденные.
          Предположения класс имеет три таких методов: assumeFalse(), assumeTrue() и assumingThat()

Аннотации JUnit 5
JUnit 5 предлагает следующие аннотации для написания тестов.

## Аннотации

@BeforeEach - Аннотированный метод будет запускаться перед каждым тестовым методом в тестовом классе.

@AfterEach - Аннотированный метод будет запускаться после каждого тестового метода в тестовом классе.

@BeforeAll - Аннотированный метод будет запущен перед всеми тестовыми методами в тестовом классе. Этот метод должен быть статическим. 

@AfterAll - Аннотированный метод будет запущен после всех тестовых методов в тестовом классе. Этот метод должен быть статическим.

@Test - Он используется, чтобы пометить метод как тест junit.

@DisplayName - Используется для предоставления любого настраиваемого отображаемого имени для тестового класса или тестового метода

@Disable - Он используется для отключения или игнорирования тестового класса или тестового метода из набора тестов.

@Nested - Используется для создания вложенных тестовых классов

@Tag - Пометьте методы тестирования или классы тестов тегами для обнаружения и фильтрации тестов.

@TestFactory - Отметить метод - это тестовая фабрика для динамических тестов.

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
