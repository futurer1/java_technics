package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2); // 1
        list.add(4);

        Iterator<Integer> myIterator = list.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            System.out.println(myIterator.next());

            if (i == 1) {
                myIterator.remove(); // удаление элемента по текущему указателю итератора
            }

            i++;
        }
        System.out.println(list); // [1, 4]

        for (Integer j: list) {
            System.out.println(j);
            list.remove(0);
            list.remove(0); // Исключение ConcurrentModificationException
            // возникает только в том случае, если foreach продолжит итерироваться по коллекции
            // если в коллекции оставалось всего 2 элемента, то удаление любого из них в конце цикла не приведет к ошибке
            // причем в случае удаления второго элемента цикл foreach отработает только один раз.
        }
        System.out.println(list); // []

    }
}
