package Stock.ranking;

import Stock.*;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.collections.FXCollections; //Graphical User Interface import needed for the TableView
import javafx.collections.ObservableList; //Graphical User Interface import needed for the TableView
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.control.TableColumn; //Graphical User Interface import needed to operate with TableColumn
import javafx.scene.control.TableView; //Graphical User Interface import needed to operate with TableView
import javafx.scene.control.cell.PropertyValueFactory; //Graphical User Interface import needed to fill out TableView
import javafx.scene.image.Image; //Graphical User Interface import needed to use images
import javafx.scene.image.ImageView; //Graphical User Interface import needed to use images in ImageViews
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ArrayList; //needed for using lists
import java.util.Collections; //needed for using lists
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class RankingViewController implements Initializable {

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
    private Label userLabel;

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
    private TableView<RankingClass> tableViewOfRanking;

    @FXML
    private TableColumn<RankingClass, Label> numberColumn;

    @FXML
    private TableColumn<RankingClass, Label> nameColumn;

    @FXML
    private TableColumn<RankingClass, Label> valueOfWalletColumn;

    @FXML
    private TableColumn<RankingClass, Label> profitAndLossInMoney;

    @FXML
    private TableColumn<RankingClass, Label> profitAndLossInPercent;

    //declaring and initializing list used to fill out TableView
    public ObservableList<RankingClass> listForTableViewOfRanking = FXCollections.observableArrayList();

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

        //creating sorted list of persons
        List<Person> persons = getSelectedNamesForMoney(Firebase_Controller.persons);

        //filling data in TableView in each row with one user
        for (int i = 0; i < Firebase_Controller.persons.size(); i++) {
            //create new ranking class
            RankingClass rankingClass = new RankingClass();

            //create new label for filling out number
            Label numberLabel = new Label();

            //create index of the user - his or her number in ranking
            int indexForTextInNumberLabel = i + 1;
            String textInNumberLabel = "" + indexForTextInNumberLabel + ".";

            //set text of the label
            numberLabel.setText(textInNumberLabel);

            //check if the user is the logged user
            if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                numberLabel.setStyle("-fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
            } else {
                numberLabel.setStyle("-fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
            }

            //set properties of the label
            numberLabel.setMaxHeight(30);
            numberLabel.setMinHeight(30);
            numberLabel.setPrefHeight(30);
            numberLabel.setMaxWidth(59);
            numberLabel.setMinWidth(59);
            numberLabel.setPrefWidth(59);

            //add label to ranking class
            rankingClass.setNumber(numberLabel);

            //create new label for filling out name
            Label nameLabel = new Label();

            //set text of label
            String textInNameLabel = persons.get(i).getLogin();
            nameLabel.setText(textInNameLabel);

            //check if the user is a logged user
            if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                nameLabel.setStyle("-fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
            } else {
                nameLabel.setStyle("-fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
            }

            //set properties of the label
            nameLabel.setMaxHeight(30);
            nameLabel.setMinHeight(30);
            nameLabel.setPrefHeight(30);
            nameLabel.setMaxWidth(190);
            nameLabel.setMinWidth(190);
            nameLabel.setPrefWidth(190);

            //add label to ranking class
            rankingClass.setName(nameLabel);

            //create new label for filling out value of wallet
            Label valueOfWalletLabel = new Label();

            //check if there is a profit or loss
            if (persons.get(i).getCurrentMoney() > 1000) {
                //set text of profit
                valueOfWalletLabel.setText("$" + persons.get(i).getCurrentMoney());

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #04b906; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #04b906; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else if (persons.get(i).getCurrentMoney() == 1000) {
                //set text of no change
                valueOfWalletLabel.setText("$" + persons.get(i).getCurrentMoney());

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #505050; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #505050; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else {
                //set text of loss
                valueOfWalletLabel.setText("$" + persons.get(i).getCurrentMoney());

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    valueOfWalletLabel.setStyle("-fx-text-fill: #ff0000; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            }

            //set properties of the label
            valueOfWalletLabel.setMaxHeight(30);
            valueOfWalletLabel.setMinHeight(30);
            valueOfWalletLabel.setPrefHeight(30);
            valueOfWalletLabel.setMaxWidth(190);
            valueOfWalletLabel.setMinWidth(190);
            valueOfWalletLabel.setPrefWidth(190);

            //add label to ranking class
            rankingClass.setValueOfWallet(valueOfWalletLabel);

            //create new label for filling out profit and loss in money
            Label profitAndLossInMoney = new Label();

            double moneyForProfitAndLossInMoney = round(persons.get(i).getCurrentMoney() - 1000);

            //check if there is a profit or loss
            if (moneyForProfitAndLossInMoney < 0) {
                //set text of profit
                profitAndLossInMoney.setText("$" + moneyForProfitAndLossInMoney);

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #ff0000; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else if (moneyForProfitAndLossInMoney == 0) {
                //set text of no change
                profitAndLossInMoney.setText("$0.00");

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #505050; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #505050; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else {
                //set text of loss
                profitAndLossInMoney.setText("$+" + moneyForProfitAndLossInMoney);

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #04b906; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInMoney.setStyle("-fx-text-fill: #04b906; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            }

            //set properties of the label
            profitAndLossInMoney.setMaxHeight(30);
            profitAndLossInMoney.setMinHeight(30);
            profitAndLossInMoney.setPrefHeight(30);
            profitAndLossInMoney.setMaxWidth(103);
            profitAndLossInMoney.setMinWidth(103);
            profitAndLossInMoney.setPrefWidth(103);

            //add label to ranking class
            rankingClass.setProfitAndLossInMoney(profitAndLossInMoney);

            //create new label for filling out profit and loss in percent
            Label profitAndLossInPercent = new Label();

            //create necessary variables for the calculations
            double change = 0;
            double rateOfChange = 0;
            double previousValue = 1000;
            double currentValue = persons.get(i).getCurrentMoney();
            change = currentValue - previousValue;
            rateOfChange = ((currentValue - previousValue) / previousValue) * 100;

            //if there is a profit or loss
            if (change > 0) {
                //set text of profit
                profitAndLossInPercent.setText("+" + round(rateOfChange) + "%");

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #04b906; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #04b906; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else if (change == 0) {
                //set text of no change
                profitAndLossInPercent.setText("0.0%");

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #505050; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #505050; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            } else {
                //set text of loss
                profitAndLossInPercent.setText(round(rateOfChange) + "%");

                //check if the user is logged user
                if (persons.get(i).getLogin().equals(Firebase_Controller.persons.get(Main.IndexOfUser).getLogin())) {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-background-color: #ebeb5f; -fx-alignment: CENTER; -fx-border-width: 0 0 0 0;");
                } else {
                    profitAndLossInPercent.setStyle("-fx-text-fill: #ff0000; -fx-alignment: CENTER;  -fx-border-width: 0 0 0 0;");
                }
            }

            //set properties of the label
            profitAndLossInPercent.setMaxHeight(30);
            profitAndLossInPercent.setMinHeight(30);
            profitAndLossInPercent.setPrefHeight(30);
            profitAndLossInPercent.setMaxWidth(103);
            profitAndLossInPercent.setMinWidth(103);
            profitAndLossInPercent.setPrefWidth(103);

            //add label to ranking class
            rankingClass.setProfitAndLossInPercent(profitAndLossInPercent);

            //add ranking class to the list for TableView
            listForTableViewOfRanking.add(rankingClass);
        }

        //operations on the number column
        numberColumn.setCellValueFactory(new PropertyValueFactory<RankingClass, Label>("number"));
        numberColumn.setStyle("-fx-alignment: CENTER;");
        numberColumn.setSortable(false);

        //operations on the name column
        nameColumn.setCellValueFactory(new PropertyValueFactory<RankingClass, Label>("name"));
        nameColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setSortable(false);

        //operations on the value of wallet column
        valueOfWalletColumn.setCellValueFactory(new PropertyValueFactory<RankingClass, Label>("valueOfWallet"));
        valueOfWalletColumn.setStyle("-fx-alignment: CENTER;");
        valueOfWalletColumn.setSortable(false);

        //operations on the profit and loss in money column
        profitAndLossInMoney.setCellValueFactory(new PropertyValueFactory<RankingClass, Label>("profitAndLossInMoney"));
        profitAndLossInMoney.setStyle("-fx-alignment: CENTER;");
        profitAndLossInMoney.setSortable(false);

        //operations on the profit and loss in percent column
        profitAndLossInPercent.setCellValueFactory(new PropertyValueFactory<RankingClass, Label>("profitAndLossInPercent"));
        profitAndLossInPercent.setStyle("-fx-alignment: CENTER;");
        profitAndLossInPercent.setSortable(false);

        //add items from the list to the TableView
        tableViewOfRanking.setItems(listForTableViewOfRanking);
        tableViewOfRanking.setStyle("-fx-border-color: #000000; -fx-border-width: 0 0 1 0;");

        //set TableView to not interact with the user
        tableViewOfRanking.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

        //add css style as a TableView's style
        tableViewOfRanking.getStylesheets().add("/CSS/TableView.css");

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
                LogOutButton.setStyle("-fx-background-color: #d1d2d1; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the log out button
        LogOutButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LogOutButton.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
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

    //sorting method of sorting persons based on their current amount of money
    public static List<Person> getSelectedNamesForMoney(List<Person> persons) {
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
        return currentPersons;
    }
}