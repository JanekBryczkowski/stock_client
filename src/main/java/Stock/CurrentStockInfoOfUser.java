package Stock;

public class CurrentStockInfoOfUser {
    private String nameOfTheCompany;
    private String amountOfStocks;
    private String latestStockPrice;
    private String profitOrLoss;

    //constructor
    public CurrentStockInfoOfUser(String nameOfTheCompany, String amountOfStocks, String latestStockPrice, String profitOrLoss) {
        this.nameOfTheCompany = nameOfTheCompany;
        this.amountOfStocks = amountOfStocks;
        this.latestStockPrice = latestStockPrice;
        this.profitOrLoss = profitOrLoss;
    }

    //empty constructor
    public CurrentStockInfoOfUser() {

    }

    //getters and setters
    public String getNameOfTheCompany() {
        return nameOfTheCompany;
    }

    public void setNameOfTheCompany(String nameOfTheCompany) {
        this.nameOfTheCompany = nameOfTheCompany;
    }

    public String getAmountOfStocks() {
        return amountOfStocks;
    }

    public void setAmountOfStocks(String amountOfStocks) {
        this.amountOfStocks = amountOfStocks;
    }

    public String getLatestStockPrice() {
        return latestStockPrice;
    }

    public void setLatestStockPrice(String latestStockPrice) {
        this.latestStockPrice = latestStockPrice;
    }

    public String getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(String profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
}
