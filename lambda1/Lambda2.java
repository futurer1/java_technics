package lambda1;

/**
 * Функциональный интерфейс Executable1
 */
interface Executable1 {
    int execute(int x);
}

/**
 * Функциональный интерфейс Executable2
 */
interface Executable2 {
    int execute(int x, int y);
}

class Runner1 {
    public void run(Executable1 e) {
        int a = e.execute(5);
        System.out.println(a);
    }
}
class Runner2 {
    public void run(Executable2 e) {
        int a = e.execute(5, 4);
        System.out.println(a);
    }
}

public class Lambda2 {
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();

        runner1.run(x -> { // скобки (x) нужны только, если аргументов более 1
            int res = 10 + x;
            System.out.println("Execute1");
            return res;
        }); // 15

        Runner2 runner2 = new Runner2();
        System.out.println("Execute2");

        final int a = 8; // в лямбда можно использовать только final или ту переменную, которая не менялась
        int b = 1;
        runner2.run((x, y) -> x + y + a + b); // 18
        runner2.run(Integer::sum); // 9
        runner2.run(Integer::max); // 5

        // область видимости лямбда соответствует тому scope, где она была создана
    }
}
