package readfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Example {

    /**
     * Чтение ввода из клавиатуры
     */
    public static void example1()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
    }

    public static void example2(String patch, String filename) throws FileNotFoundException
    {
        File file = new File(patch+filename);

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    /**
     * Парсит строку в файле по разделителям и считает сумму целых значений
     * @param patch
     * @param filename
     * @throws FileNotFoundException
     */
    public static void example3(String patch, String filename) throws FileNotFoundException
    {
        File file = new File(patch+filename);
        Scanner scanner = new Scanner(file);

        // прочитаем первую строку в файле
        String contentLine = scanner.nextLine();

        // разберем значения в массив строк
        String[] numbers = contentLine.split(" ");
        scanner.close();

        for (String num: numbers) {
            System.out.println(num);
        }

        // посчитаем сумму чисел в массиве
        Integer summ = Arrays
                .stream(numbers)
                .reduce(0, (integer, string) -> (integer + Integer.parseInt(string)), Integer::sum
        );
        System.out.println("Summ = " + summ);
    }

    /**
     * Пишет в файл строку. Если файла нет, то он создаётся.
     */
    public static void example4(String patch, String filename) throws FileNotFoundException
    {
        File file = new File(patch+filename);
        PrintWriter pw = new PrintWriter(file);
        pw.println("Test line 1.");
        pw.close();
    }
}
