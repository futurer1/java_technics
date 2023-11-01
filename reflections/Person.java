@Author(name = "Myname", dateOfCreation = 2023)
public class Person {
    private int id;
    private String name;

    public int num;

    public Person() {
        this.id = -1;
        this.name = "noname";
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
