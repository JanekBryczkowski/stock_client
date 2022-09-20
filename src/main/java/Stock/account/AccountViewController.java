package Stock.account; //package account inside package Stock

import Stock.*;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.collections.FXCollections; //Graphical User Interface import needed for the TableView
import javafx.collections.ObservableList; //Graphical User Interface import needed for the TableView
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.*; //Graphical User Interface
import javafx.scene.control.cell.PropertyValueFactory; //Graphical User Interface import needed to fill out TableView
import javafx.scene.image.Image; //Graphical User Interface import needed to use images
import javafx.scene.image.ImageView; //Graphical User Interface import needed to use images in ImageViews
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.scene.layout.AnchorPane; //Graphical User Interface import to operate with AnchorPane
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second


public class AccountViewController implements Initializable {

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
    private Button BuySellButton;

    @FXML
    private ImageView ImageView;

    @FXML
    public AnchorPane ThisAnchorPane;

    @FXML
    private Label ValueOfWallet;

    @FXML
    private Label ProfitLost;

    @FXML
    private Label ProfitLostPercent;

    @FXML
    private Label userLabel;

    @FXML
    private TableView<CurrentStockInfoOfUser> TableViewOfOwnedStocks;

    @FXML
    private TableColumn<CurrentStockInfoOfUser, String> ColumnOfNames;

    @FXML
    private TableColumn<CurrentStockInfoOfUser, String> ColumnOfAmounts;

    @FXML
    private TableColumn<CurrentStockInfoOfUser, String> ColumnOfStocks;

    @FXML
    private TableColumn<CurrentStockInfoOfUser, String> ColumnOfProfitLost;

    //declaring and initializing list used to fill out TableView
    public ObservableList<CurrentStockInfoOfUser> listForTableView = FXCollections.observableArrayList();

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

        //set ValueOfWallet Label to be the current logged user's current amount of money
        ValueOfWallet.setText("$" + (Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney()));

        //set profitLost variable to be the amount of money compared to the initial value
        double profitLost = Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney() - 1000.00;
        if (profitLost < 0) {
            //when profit is less then zero
            ProfitLost.setText("$" + round(profitLost));
        } else if (profitLost > 0) {
            //when profit is more than zero
            ProfitLost.setText("$+" + round(profitLost));
        } else {
            //when there is no profit and the current amount is the same as the initial amount
            ProfitLost.setText("$0.0");
        }

        //set profitLostPercent variable to be the amount of money compared to the initial value expressed in percent
        double profitLostPercent = ((Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney() - 1000) / 1000) * 100;
        if (profitLostPercent < 0) {
            //when profit is less than zero
            ProfitLostPercent.setText("" + round(profitLostPercent) + "%");
            ProfitLostPercent.setStyle("-fx-text-fill: #ff0000;");
        } else if (profitLostPercent > 0) {
            //when profit is more than zero
            ProfitLostPercent.setText("+" + round(profitLostPercent) + "%");
            ProfitLostPercent.setStyle("-fx-text-fill: #04b906;");
        } else {
            //when there is no profit and the current amount is the same as the initial amount
            ProfitLostPercent.setText("0.0%");
            ProfitLostPercent.setStyle("-fx-text-fill: #505050;");
        }

        //loop for filling out list of stocks owned by a logged user
        for (int i = 0; i < Firebase_Controller.persons.get(Main.IndexOfUser).getStockOwned().size(); i++) {
            //creating new CurrentStockInfoOfUser object in order to put that object into the TableView
            CurrentStockInfoOfUser currentStockInfoOfUser = new CurrentStockInfoOfUser();

            //adding name of each company to the CurrentStockInfoOfUser
            currentStockInfoOfUser.setNameOfTheCompany(Firebase_Controller.persons.get(Main.IndexOfUser).
                    getStockOwned().get(i).getName());

            //adding amount of stocks of each company which logged user owns to the CurrentStockInfoOfUser
            currentStockInfoOfUser.setAmountOfStocks(String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).
                    getStockOwned().get(i).getAmount()));

            //loop for finding the index of the company in the list of Companies to find the last stock price
            int indexOfTheCompanyInCompaniesFromTheUser = -1;
            for (int j = 0; j < Firebase_Controller.persons.get(Main.IndexOfUser).getStockOwned().size(); j++) {
                //if both names equals that means the index was found
                //this if at some point of the loop has to be equal to some name from the Firebase
                if (Firebase_Controller.persons.get(Main.IndexOfUser).getStockOwned().get(i).getName().
                        equals(Firebase_Controller.companies.get(j).getName())) {
                    indexOfTheCompanyInCompaniesFromTheUser = j;
                }
            }

            //adding last stock price of each company to the CurrentStockInfoOfUser
            currentStockInfoOfUser.setLatestStockPrice(String.valueOf(Firebase_Controller.companies.
                    get(indexOfTheCompanyInCompaniesFromTheUser).getStockPrices().get(Firebase_Controller.companies.
                    get(indexOfTheCompanyInCompaniesFromTheUser).getStockPrices().size() - 1).getValue()));

            //checking if the last stock price rose or dropped
            if (Firebase_Controller.companies.get(indexOfTheCompanyInCompaniesFromTheUser).getStockPrices().size() == 1) {
                //last stock price is the same as the previous one
                currentStockInfoOfUser.setProfitOrLoss("$0.0 (0.0%)");
            } else {
                //last stock price is not the same as the previous one
                //declaring and initializing variables necessary for determining the growth or decrease
                double change = 0;
                double rateOfChange = 0;
                double previousValue = Firebase_Controller.companies.get(indexOfTheCompanyInCompaniesFromTheUser).
                        getStockPrices().get(Firebase_Controller.companies.get(indexOfTheCompanyInCompaniesFromTheUser).
                        getStockPrices().size() - 2).getValue();
                double currentValue = Firebase_Controller.companies.get(indexOfTheCompanyInCompaniesFromTheUser).
                        getStockPrices().get(Firebase_Controller.companies.get(indexOfTheCompanyInCompaniesFromTheUser).
                        getStockPrices().size() - 1).getValue();

                //operations on the variables
                change = currentValue - previousValue;
                rateOfChange = ((currentValue - previousValue) / previousValue) * 100;
                if (change > 0) {
                    //adding rounded increased profitOrLoss to the CurrentStockInfoOfUser
                    currentStockInfoOfUser.setProfitOrLoss("$+" + round(change) + " (+" + round(rateOfChange) + "%)");
                } else {
                    //adding rounded decreased profitOrLoss to the CurrentStockInfoOfUser
                    currentStockInfoOfUser.setProfitOrLoss("$" + round(change) + " (" + round(rateOfChange) + "%)");
                }
            }

            //adding CurrentStockInfoOfUser to the list created for TableView
            listForTableView.add(currentStockInfoOfUser);

            //the process repeats to add to the list all of the information of owned stocks
        }

        //operations on the name column
        ColumnOfNames.setCellValueFactory(new PropertyValueFactory<CurrentStockInfoOfUser, String>("nameOfTheCompany"));
        ColumnOfNames.setStyle("-fx-alignment: CENTER;");
        ColumnOfNames.setSortable(false);

        //operations on the amount column
        ColumnOfAmounts.setCellValueFactory(new PropertyValueFactory<CurrentStockInfoOfUser, String>("amountOfStocks"));
        ColumnOfAmounts.setStyle("-fx-alignment: CENTER;");
        ColumnOfAmounts.setSortable(false);

        //operations on the stock price column
        ColumnOfStocks.setCellValueFactory(new PropertyValueFactory<CurrentStockInfoOfUser, String>("latestStockPrice"));
        ColumnOfStocks.setStyle("-fx-alignment: CENTER;");
        ColumnOfStocks.setSortable(false);

        //operations on the profit or loss column
        ColumnOfProfitLost.setCellValueFactory(new PropertyValueFactory<CurrentStockInfoOfUser, String>("profitOrLoss"));
        ColumnOfProfitLost.setStyle("-fx-alignment: CENTER;");
        ColumnOfProfitLost.setSortable(false);

        //add items from the list to the TableView
        TableViewOfOwnedStocks.setItems(listForTableView);

        //set style of the TableView
        TableViewOfOwnedStocks.setStyle("-fx-border-color: #000000; -fx-border-width: 0 0 1 0;");

        //set TableView to not interact with the user
        TableViewOfOwnedStocks.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
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

        //set style when mouse is on the area of the buy or sell button
        BuySellButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButton.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000; -fx-border-radius: 10; -fx-background-radius: 15;");
            }
        });

        //set style when mouse is out of the area of the buy or sell button
        BuySellButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButton.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000; -fx-border-radius: 10; -fx-background-radius: 15;");
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

    //method of going to the buy or sell window
    @FXML
    private void goBuySell() throws IOException {
        main.showBuyingSelling();
    }

    //method of rounding double variables to two decimal places
    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
