package readfile;

import java.io.Serializable;

public class TestData implements Serializable {
    private int id;
    private String name;

    public TestData(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + "; name: " + name;
    }
}
