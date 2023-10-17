package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Example {

    static int var1 = 10;
    public static void example1() {
        System.out.println("Example1");

        Button button = new Button("Click me!");

        // запись в стиле анонимного класса с функциональным интерфейсом
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pressed");
            }
        });

        int i = 10; // внешние переменный захватываются лямбдой
        // ограничения в Java: переменная должна быть immutable (final)

        var1 = 20; // меняется только значение по ссылке, но не сама переменная
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.set(20); // внутреннее свойство можно менять


        button.addActionListener(e -> JOptionPane.showMessageDialog(null, "Pressed" + i));
        button.addActionListener(e -> JOptionPane.showMessageDialog(null, "Pressed" + var1));
        button.addActionListener(e -> JOptionPane.showMessageDialog(null, "Pressed" + atomicInteger.toString()));

    }

    @FunctionalInterface
    interface FuncInterface <T1, T2, R> {
        R apply(T1 arg1, T2 arg2);
    }

    @FunctionalInterface
    interface FuncInterface1 <T1, R> {
        R apply(T1 arg1);
    }

    public static void example2()
    {
        System.out.println("Example2");
        FuncInterface<Integer, Integer, Integer> function = (arg1, arg2) -> arg1 + arg2;
        System.out.println(function.apply(1, 5));
    }

    public static void example3()
    {
        System.out.println("Example3");
        // реализация функционального интерфеса может быть заменена ссылкой на функцию с такой же сигнатурой метода
        FuncInterface<Integer, Integer, Integer> myFunction = Integer::sum;
        System.out.println(myFunction.apply(3, 5));

        FuncInterface1<Integer, Integer> myFunction1 = Integer::reverse;
        System.out.println(myFunction1.apply(1));
    }

    public static void example4()
    {
        System.out.println("Example4");
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client("Igor", 5, true));
        clients.add(new Client("Vasya", 10, true));
        clients.add(new Client("Petya", 20, false));
        clients.add(new Client("Masha", 30, true));
        clients.add(new Client("Liza", 40, false));

        // уменьшение баланса в цикле
        for (Client client: clients) {
            if (client.getActive()) {
                client.setSum(client.getSum() - 1);
                System.out.println(client.getSum());
            }
        }
        System.out.println();

        // уменьшение баланса с помощью лямбды
        clients.forEach(client -> {
            client.setSum(client.getSum() - 1);
            System.out.println(client.getSum());
        });
        System.out.println();

        // подсчет суммы элементов с одновременной фильтрацией
        int balance = clients.stream()
                .filter(c -> c.getSum() > 7) // с балансом больше 7
                .filter(Client::getActive) // только активные
                .reduce(0, ((integer, client) -> integer + client.getSum()), Integer::sum);
        System.out.println("balance = " + balance);

    }

    public static void example5()
    {
        Client cl = new Client("Igor", 5, true);

        // добавляем приветствие к имени, пользуясь ссылками на методы
        addHello(cl::getName, cl::setName);

        System.out.println(cl.getName());
    }

    /**
     * Передаются ссылки на методы
     * @param sup ссылка на метод получения имени клиента
     * @param cons ссылка на метод присвоения имени клиенту
     */
    public static void addHello(Supplier<String> sup, Consumer<String> cons)
    {
        cons.accept(String.format("Hello, %s !", sup.get()));
    }

    /**
     * Проверяем, что объект не null и только после этого выводим его имя
     * Таким образом избегаем NullPointerException
     */
    public static void example6()
    {
        Client client1 = null;
        Client client2 = new Client("Igor", 5, true);
        chechForNull(client1, c -> {
            System.out.println(client1.getName());
        });

        chechForNull(client2, c -> {
            System.out.println(client2.getName());
        });
    }

    public static <T> void chechForNull(T param, Consumer<T> cons)
    {
        if (param != null) {
            cons.accept(param);
        }
    }

    public static void example7()
    {
        Client client1 = new Client("Igor", 5, true);
        ManagerClient managerClient = new ManagerClient(client1);
        managerClient.change(c -> c.setName("New value"));
    }

    static class ManagerClient
    {
        private final Client client;

        public ManagerClient(Client client) {
            this.client = client;
        }

        public void change(Consumer<Client> consumer)
        {
            System.out.println("Вызов основного метода");
            consumer.accept(client);
            updateLog();
        }

        public void updateLog()
        {
            System.out.println("Вызов дополнительного метода");
        }
    }
}
