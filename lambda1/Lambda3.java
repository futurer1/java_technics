package lambda1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка массива строк по длине на основе собственного компаратора из лямбды
 */
public class Lambda3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaaaa");
        list.add("bbbb");
        list.add("ccc");
        list.add("dd");
        list.add("e");

        Comparator<String> myComp = (x, y) -> {
            if (x.length() > y.length()) {
                return 1;
            } else if (x.length() < y.length()) {
                return -1;
            }
            return 0;
        };

        list.sort(myComp);

        System.out.println(list);
    }
}
