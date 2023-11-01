import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Person();

        // 3 разных способа получить объект класса Class
        Class myPerson = person.getClass();
        Class myPerson1 = Person.class;
        Class myPerson2 = Class.forName("Person");

        Method[] allmethods = myPerson.getMethods();
        for (Method method: allmethods) {
            System.out.println(method.getName() + ", "
                    + method.getReturnType() + ", "
                    + Arrays.toString(method.getParameterTypes())
            );
        }
        System.out.println("");
        /*
            getName, class java.lang.String, []
            setName, void, [class java.lang.String]
            getId, int, []
            sayHello, void, []
            setId, void, [int]
            getNum, int, []
            setNum, void, [int]
            wait, void, [long, int]
            wait, void, []
            wait, void, [long]
            equals, boolean, [class java.lang.Object]
            toString, class java.lang.String, []
            hashCode, int, []
            getClass, class java.lang.Class, []
            notify, void, []
            notifyAll, void, []
         */

        Field[] fields = myPerson1.getFields(); // только public поля
        for (Field field: fields) {
            System.out.println(field.getName() + ", " + field.getType());
        }
        /*
            num, int
        */
        System.out.println("");

        Field[] declaredFields = myPerson2.getDeclaredFields(); // поля, включая private
        for (Field field: declaredFields) {
            System.out.println(field.getName() + ", " + field.getType());
        }
        /*
            id, int
            name, class java.lang.String
            num, int
        */
        System.out.println("");

        Annotation[] annotations = myPerson1.getAnnotations();
        for (Annotation annotation: annotations) {
            if (annotation instanceof Author) {
                System.out.println("is Author annotated");
            }
        }
        /*
            is Author annotated
        */
    }
}
