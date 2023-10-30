package lambda1;

interface Executable {
    void execute();
}

class Runner {
    public void run(Executable e) {
        e.execute();
    }
}

class ExecutableImpl implements Executable {

    @Override
    public void execute() {
        System.out.println("Execute");
    }
}

public class Lambda1 {
    public static void main(String[] args) {
        Runner runner = new Runner();

        // 1.
        runner.run(new ExecutableImpl());

        // 2.
        runner.run(new Executable() {
            @Override
            public void execute() {
                System.out.println("Execute1");
            }
        });

        // 3.
        runner.run(() -> {
            System.out.println("Execute2");
        });
    }
}
