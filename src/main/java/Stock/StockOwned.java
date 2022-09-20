package Stock;

public class StockOwned {
    private String name;
    private int amount;

    //constructor
    public StockOwned(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    //empty constructor
    public StockOwned(){

    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString(){
        return "Stock Owned: (name " + getName() + ", amount " + getAmount() + ")";
    }
}
