package lambda;

public class Client {
    private String name;
    private Integer sum;
    private Boolean isActive;

    public Client(String name, Integer sum, Boolean isActive)
    {
        this.name = name;
        this.sum = sum;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public Integer getSum() {
        return sum;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
