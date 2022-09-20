package Stock;

public class PendingOrder {
    private String nameOfTheCompany;
    private boolean buyOrSell;
    private int amount;
    private Time time;

    //constructor
    public PendingOrder(String nameOfTheCompany, boolean buyOrSell, int amount, Time time) {
        this.nameOfTheCompany = nameOfTheCompany;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
        this.time = time;
    }

    //empty constructor
    public PendingOrder(){

    }

    //getters and setters
    public String getNameOfTheCompany() {
        return nameOfTheCompany;
    }

    public void setNameOfTheCompany(String nameOfTheCompany) {
        this.nameOfTheCompany = nameOfTheCompany;
    }

    public boolean isBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(boolean buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String toString(){
        return "Pending Order: (name of the Company: " + getNameOfTheCompany() + ", buy or sell" + isBuyOrSell() + ", amount " + getAmount() + ", " + getTime() +")";
    }
}
