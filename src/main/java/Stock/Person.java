package Stock;

import java.util.List;

public class Person {
    private String login;
    private String password;
    private double currentMoney;
    private double money;
    private List<StockOwned> stockOwned;
    private List<PendingOrder> pendingOrder;

    //constructor
    public Person(String login, String password, double currentMoney, double money, List<StockOwned> stockOwned, List<PendingOrder> pendingOrder) {
        this.login = login;
        this.password = password;
        this.currentMoney = currentMoney;
        this.money = money;
        this.stockOwned = stockOwned;
        this.pendingOrder = pendingOrder;
    }

    //empty constructor
    public Person(){

    }

    //getters and setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<StockOwned> getStockOwned() {
        return stockOwned;
    }

    public void setStockOwned(List<StockOwned> stockOwned) {
        this.stockOwned = stockOwned;
    }

    public List<PendingOrder> getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(List<PendingOrder> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public String toString() {
        return "Person: (login: " + getLogin() + ", password " + getPassword() + ", current Money " + getCurrentMoney() + ", Money " + getMoney() + ", " + getStockOwned() + ", " + getPendingOrder() + ")";
    }
}
