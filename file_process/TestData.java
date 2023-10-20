package file_process;

import java.io.Serializable;

public class TestData implements Serializable {
    private int id;
    private String name;
    private transient String password; // Поле, значение которого не будет сериализовываться. Ему будет присвоено значение null

    public TestData(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public TestData(int id, String name, String password)
    {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "id: " + id + "; name: " + name;
    }
}
