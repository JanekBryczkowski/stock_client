package Stock.main; //package main inside package Stock

import Stock.*;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.control.ListView; //Graphical User Interface import needed to operate with list view
import javafx.scene.image.Image; //Graphical User Interface import needed to operate with images
import javafx.scene.image.ImageView; //Graphical User Interface import needed to operate with imageViews
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.scene.layout.AnchorPane; //Graphical User Interface import to operate with AnchorPane
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ArrayList; //needed for using lists
import java.util.Collections; //needed for using lists
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second


public class MainViewController implements Initializable {

    //declaring variables
    private Main main;

    //declaring FXML variables
    @FXML
    private Label HourLabel;

    @FXML
    private Label MinuteLabel;

    @FXML
    private Label SecondLabel;

    @FXML
    private Button AccountButton;

    @FXML
    private Button PendingOrderButton;

    @FXML
    private Button StocksButton;

    @FXML
    private Button RankingButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button HomeButton;

    @FXML
    private ImageView ImageView;

    @FXML
    public AnchorPane ThisAnchorPane;

    @FXML
    private Label MoneyLabel;

    @FXML
    private Label MoneyPercentLabel;

    @FXML
    private ListView<String> ListViewOfRanking;

    @FXML
    private Label StockLabel1;

    @FXML
    private Label StockLabel2;

    @FXML
    private Label StockLabel3;

    @FXML
    private Label StockLabel4;

    @FXML
    private Label StockLabel5;

    @FXML
    private Label StockLabel6;

    @FXML
    private Label StockLabel7;

    @FXML
    private Label StockLabel8;

    @FXML
    private Label StockLabelChange1;

    @FXML
    private Label StockLabelChange2;

    @FXML
    private Label StockLabelChange3;

    @FXML
    private Label StockLabelChange4;

    @FXML
    private Label StockLabelChange5;

    @FXML
    private Label StockLabelChange6;

    @FXML
    private Label StockLabelChange7;

    @FXML
    private Label StockLabelChange8;

    @FXML
    private Label userLabel;

    //initialize method starts when this window is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //creating a clock on the top-right side
        //setting refreshing every 0.05 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            //algorithm for setting hour number
            if (LocalDateTime.now().getHour() < 10) {
                HourLabel.setText("0" + LocalDateTime.now().getHour());
            } else {
                HourLabel.setText(String.valueOf(LocalDateTime.now().getHour()));
            }

            //algorithm for setting minute number
            if (LocalDateTime.now().getMinute() < 10) {
                MinuteLabel.setText("0" + LocalDateTime.now().getMinute());
            } else {
                MinuteLabel.setText(String.valueOf(LocalDateTime.now().getMinute()));
            }

            //algorithm for setting second number
            if (LocalDateTime.now().getSecond() < 10) {
                SecondLabel.setText("0" + LocalDateTime.now().getSecond());
            } else {
                SecondLabel.setText(String.valueOf(LocalDateTime.now().getSecond()));
            }
        }));
        //setting timeline to be played indefinitely
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //filling variables from FXML file with data from Firebase

        //set userLabel to be the current logged user's login
        userLabel.setText("user: " + Firebase_Controller.persons.get(Main.IndexOfUser).getLogin());

        //set moneyLabel to be the current logged user's amount of money
        MoneyLabel.setText("$" + (round(Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney())));

        //create variable representing amount of profit or loss in percent
        double percent = ((round(Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney() - 1000.00)) / 1000.00) * 100;

        //if profit or loss in percent is greater than zero
        if (percent > 0) {
            MoneyPercentLabel.setText("(+" + percent + "%)");
            MoneyPercentLabel.setStyle("-fx-text-fill: #04b906;");
        } else if (percent == 0) {
            //if profit or loss in percent is equal to zero
            MoneyPercentLabel.setText("(" + percent + "%)");
            MoneyPercentLabel.setStyle("-fx-text-fill: #505050;");
        } else {
            //if profit or loss in percent is smaller than zero
            MoneyPercentLabel.setText("(" + percent + "%)");
            MoneyPercentLabel.setStyle("-fx-text-fill: #ff0000;");
        }

        //create list of names sorted by their current amount of money
        List<String> names = getSortedPersonsByMoney(Firebase_Controller.persons);

        //add data from the list to the list for ListView
        ListViewOfRanking.getItems().addAll(names);

        //create list of labels for stock price and add all labels to the list
        List<Label> listOfLabelsOfStockValues = new ArrayList<>();
        listOfLabelsOfStockValues.add(StockLabel1);
        listOfLabelsOfStockValues.add(StockLabel2);
        listOfLabelsOfStockValues.add(StockLabel3);
        listOfLabelsOfStockValues.add(StockLabel4);
        listOfLabelsOfStockValues.add(StockLabel5);
        listOfLabelsOfStockValues.add(StockLabel6);
        listOfLabelsOfStockValues.add(StockLabel7);
        listOfLabelsOfStockValues.add(StockLabel8);

        //fill out labels with data from the Firebase
        for (int i = 0; i < listOfLabelsOfStockValues.size(); i++) {
            int indexOfLastStockPrice = Firebase_Controller.companies.get(i).getStockPrices().size() - 1;
            listOfLabelsOfStockValues.get(i).setText("$" + (Firebase_Controller.companies.get(i).getStockPrices().get(indexOfLastStockPrice).getValue()));
        }

        //create list of labels for stock price change and add all labels to the list
        List<Label> listOfLabelsOfStockValuesChanges = new ArrayList<>();
        listOfLabelsOfStockValuesChanges.add(StockLabelChange1);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange2);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange3);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange4);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange5);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange6);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange7);
        listOfLabelsOfStockValuesChanges.add(StockLabelChange8);

        //fill out labels with data from the Firebase
        for (int i = 0; i < listOfLabelsOfStockValuesChanges.size(); i++) {
            //create variables to check the difference
            int indexOfLastStockPrice = Firebase_Controller.companies.get(i).getStockPrices().size() - 1;
            int indexOfStockPriceBeforeTheLastOne = Firebase_Controller.companies.get(i).getStockPrices().size() - 2;

            //check the difference
            if (indexOfStockPriceBeforeTheLastOne < 0) {
                //two stock prices are the same
                listOfLabelsOfStockValuesChanges.get(i).setText("0.0%");
                listOfLabelsOfStockValuesChanges.get(i).setStyle("-fx-text-fill: #505050; -fx-border-width: 0 0 1 0; -fx-border-color: #000000;");
            } else {
                double finalResult = ((Firebase_Controller.companies.get(i).getStockPrices().get(indexOfLastStockPrice).getValue() -
                        Firebase_Controller.companies.get(i).getStockPrices().get(indexOfStockPriceBeforeTheLastOne).getValue()) /
                        Firebase_Controller.companies.get(i).getStockPrices().get(indexOfStockPriceBeforeTheLastOne).getValue()) * 100.00;
                double finalResultToTwoDecimalPlaces = round(finalResult);
                if (finalResult < 0) {
                    //there is loss
                    listOfLabelsOfStockValuesChanges.get(i).setText(finalResultToTwoDecimalPlaces + "%");
                    listOfLabelsOfStockValuesChanges.get(i).setStyle("-fx-text-fill: #ff0000; -fx-border-width: 0 0 1 0; -fx-border-color: #000000;");
                } else if (finalResult > 0) {
                    //there is profit
                    listOfLabelsOfStockValuesChanges.get(i).setText("+" + finalResultToTwoDecimalPlaces + "%");
                    listOfLabelsOfStockValuesChanges.get(i).setStyle("-fx-text-fill: #04b906; -fx-border-width: 0 0 1 0; -fx-border-color: #000000;");
                } else {
                    //two stock prices are the same
                    listOfLabelsOfStockValuesChanges.get(i).setText("0.0%");
                    listOfLabelsOfStockValuesChanges.get(i).setStyle("-fx-text-fill: #505050; -fx-border-width: 0 0 1 0; -fx-border-color: #000000;");
                }
            }
        }

        //set ListView to not interact with the user
        ListViewOfRanking.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

        //set style when mouse is on the area of the account button
        AccountButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AccountButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is out of the area of the account button
        AccountButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AccountButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is on the area of the pending order button
        PendingOrderButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PendingOrderButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is out of the area of the pending order button
        PendingOrderButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PendingOrderButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is on the area of the stocks button
        StocksButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StocksButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is out of the area of the stocks button
        StocksButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StocksButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is on the area of the ranking button
        RankingButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RankingButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is out of the area of the ranking button
        RankingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RankingButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-width: 0 1 0 0");
            }
        });

        //set style when mouse is on the area of the log out button
        LogOutButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LogOutButton.setStyle("-fx-background-color: #d1d2d1; -fx-border-color: #000000; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });

        //set style when mouse is out of the area of the log out button
        LogOutButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LogOutButton.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });

        //set style when mouse is on the area of the home button
        HomeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HomeButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-width: 0 1 0 1");
                ImageView.setImage(new Image(getClass().getResourceAsStream("/Photos/Home2.png")));
            }
        });

        //set style when mouse is out of the area of the home button
        HomeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HomeButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-width: 0 1 0 1");
                ImageView.setImage(new Image(getClass().getResourceAsStream("/Photos/Home.jpg")));
            }
        });
    }

    //methods connected with the FXML file

    //method of going to the home window
    @FXML
    private void goHome() throws IOException, InterruptedException {
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        TimeUnit.SECONDS.sleep(1);
        main.showMainView();
    }

    //method of going to the account window
    @FXML
    private void goAccount() throws IOException, InterruptedException {
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        TimeUnit.SECONDS.sleep(1);
        main.showAccountView();
    }

    //method of going to the order window
    @FXML
    private void goOrder() throws IOException, InterruptedException {
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        TimeUnit.SECONDS.sleep(1);
        main.showOrderView();
    }

    //method of going to the login window
    @FXML
    private void goLogOut() throws IOException {
        Main.IndexOfUser = -1;
        main.showLogin();
    }

    //method of going to the stocks window
    @FXML
    private void goStocks() throws IOException, InterruptedException {
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        TimeUnit.SECONDS.sleep(1);
        main.showStocks();
    }

    //method of going to the ranking window
    @FXML
    private void goRanking() throws IOException, InterruptedException {
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        TimeUnit.SECONDS.sleep(1);
        main.showRanking();
    }

    //method of rounding double variables to two decimal places
    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    //sorting method of sorting persons by their current amount of money
    public static List<String> getSortedPersonsByMoney(List<Person> persons) {
        List<Person> currentPersons = new ArrayList<Person>();
        currentPersons.addAll(persons);
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < currentPersons.size() - 1; i++) {
                if (currentPersons.get(i).getCurrentMoney() < currentPersons.get(i + 1).getCurrentMoney()) {
                    Collections.swap(currentPersons, i, i + 1);
                    sorted = false;
                }
            }
        }

        List<String> finalList = new ArrayList<>();
        for (int i = 0; i < currentPersons.size(); i++) {
            int number = i + 1;
            finalList.add("     " + number + ". " + currentPersons.get(i).getLogin());
        }
        return finalList;
    }
}
