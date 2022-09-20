package Stock;

import java.util.List;

public class Company {
    private String name;
    private int amountOfInstruments;
    List<StockPrice> stockPrices;

    //constructor
    public Company(String name, int amountOfInstruments, List<StockPrice> stockPrices) {
        this.name = name;
        this.amountOfInstruments = amountOfInstruments;
        this.stockPrices = stockPrices;
    }

    //empty constructor
    public Company(){

    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfInstruments() {
        return amountOfInstruments;
    }

    public void setAmountOfInstruments(int amountOfInstruments) {
        this.amountOfInstruments = amountOfInstruments;
    }

    public List<StockPrice> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(List<StockPrice> stockPrices) {
        this.stockPrices = stockPrices;
    }
}
