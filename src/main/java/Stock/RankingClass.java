package Stock;

import javafx.scene.control.Label;

public class RankingClass {
    private Label number;
    private Label name;
    private Label valueOfWallet;
    private Label profitAndLossInMoney;
    private Label profitAndLossInPercent;

    //constructor
    public RankingClass(Label number, Label name, Label valueOfWallet, Label profitAndLossInMoney, Label profitAndLossInPercent) {
        this.number = number;
        this.name = name;
        this.valueOfWallet = valueOfWallet;
        this.profitAndLossInMoney = profitAndLossInMoney;
        this.profitAndLossInPercent = profitAndLossInPercent;
    }

    //empty constructor
    public RankingClass(){

    }

    //getters and setters
    public Label getNumber() {
        return number;
    }

    public void setNumber(Label number) {
        this.number = number;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getValueOfWallet() {
        return valueOfWallet;
    }

    public void setValueOfWallet(Label valueOfWallet) {
        this.valueOfWallet = valueOfWallet;
    }

    public Label getProfitAndLossInMoney() {
        return profitAndLossInMoney;
    }

    public void setProfitAndLossInMoney(Label profitAndLossInMoney) {
        this.profitAndLossInMoney = profitAndLossInMoney;
    }

    public Label getProfitAndLossInPercent() {
        return profitAndLossInPercent;
    }

    public void setProfitAndLossInPercent(Label profitAndLossInPercent) {
        this.profitAndLossInPercent = profitAndLossInPercent;
    }
}
