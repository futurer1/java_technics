package lambda1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda4 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        List<Integer> arr1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                arr[i] = i + 1;
            }
            arr1.add(i + 1);
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(arr1);

        // перебор и изменение через map
        int[] arr2 = Arrays.stream(arr).map(a -> a + 1).toArray();
        System.out.println(Arrays.toString(arr2));

        List<Integer> arr3 = arr1.stream().map(a -> a + 1).collect(Collectors.toList());
        System.out.println(arr3);

        // фильтрация лямбдой
        int[] arr4 = Arrays.stream(arr).filter(a -> a % 3 == 0).toArray();
        System.out.println(Arrays.toString(arr4));

        List<Integer> arr5 = arr1.stream().filter(a -> a % 3 == 0).toList();
        System.out.println(arr5);

        // вывод содержимого через forEach
        Arrays.stream(arr4).forEach(a -> System.out.println(a)); // 3, 6, 9
        arr5.stream().forEach(System.out::println); // 3, 6, 9, 12, 15, 18

        // reduce разные формы записи
        int val6 = Arrays.stream(arr4).reduce(0, (acc, x) -> acc + x);
        int val7 = Arrays.stream(arr4).reduce((acc, x) -> acc + x).getAsInt();
        int val8 = Arrays.stream(arr4).reduce(Integer::sum).getAsInt();
        System.out.println(val6); // 18
        System.out.println(val7); // 18
        System.out.println(val8); // 18

        // совмещение цепочек
        int val9 = Arrays.stream(arr)
                .filter(a -> a % 3 == 0)
                .reduce(0, (acc, x) -> acc + x);
        System.out.println(val9); // 18

        int val10 = arr5.stream()
                .filter(a -> a % 3 == 0)
                .reduce(0, (acc, x) -> acc + x + 2);
        System.out.println(val10); // 75
    }
}
