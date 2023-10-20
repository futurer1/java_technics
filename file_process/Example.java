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

    /**
     * Сериализация объекта в файл
     */
    public static void example5(String patch, String filename)
    {
        TestData obj1 = new TestData(1, "Vasya");
        TestData obj2 = new TestData(2, "Petya");

        try {
            FileOutputStream os = new FileOutputStream(patch + filename);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(obj1);
            oos.writeObject(obj2);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Десериализация объекта из файла
     */
    public static void example6(String patch, String filename)
    {
        try {
            FileInputStream fis = new FileInputStream(patch + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            TestData obj1 = (TestData) ois.readObject();
            TestData obj2 = (TestData) ois.readObject();
            fis.close();

            System.out.println(obj1);
            System.out.println(obj2);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
