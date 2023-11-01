import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestReflection1 {
    public static void main(String[] args)
            throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
        Scanner scanner = new Scanner(System.in);
        // название_класса1 название_класса2 название_метода
        Class obj1 = Class.forName(scanner.next()); // название_класса1
        Class obj2 = Class.forName(scanner.next()); // название_класса2
        String methodName = scanner.next(); // название_метода

        Method method = obj1.getMethod(methodName, obj2); // название_метода, аргумент метода

        Object o1 = obj1.getConstructor().newInstance(); // объект первого метода
        Object o2 = obj2.getConstructor(String.class).newInstance("String value");

        method.invoke(o1, o2);
        System.out.println(o1);
        /*
         Входные данные:
            java.lang.Thread java.lang.String setName
            или
            Person java.lang.String setName
         */
    }
}
