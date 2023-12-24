package java_technics.interview;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        System.out.println("1");
        Boolean b = new Boolean(false);
        System.out.println(b == Boolean.FALSE); // false из-за new, причем конструктор deprecated. Ссылки разные
        System.out.println(b.equals(Boolean.FALSE)); // true

        System.out.println("2");
        Integer first = 1;
        Integer second = 1;
        System.out.println(first == second); // true, т.к. значение кешируется JVM

        System.out.println("3");
        first = 1245;
        second = 1245;
        System.out.println(first == second); // false (если числа от -128 до 127, то будет true)
        System.out.println(first.equals(second)); // true
        System.out.println(first + " " + second); // 1245 1245
   }
}
