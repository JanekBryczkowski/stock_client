package Stock.stocks;

import Stock.Firebase_Controller;
import Stock.Main;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.image.Image; //Graphical User Interface import needed to use images
import javafx.scene.image.ImageView; //Graphical User Interface import needed to use images in ImageViews
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ArrayList; //needed for using lists
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class StocksViewController implements Initializable {

    //declaring variables
    private Main main;

    public static int indexOfStockGraphButton = -1;

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
    private Button BuySellButtonOne;

    @FXML
    private Button BuySellButtonTwo;

    @FXML
    private Button BuySellButtonThree;

    @FXML
    private Button BuySellButtonFour;

    @FXML
    private Button BuySellButtonFive;

    @FXML
    private Button BuySellButtonSix;

    @FXML
    private Button BuySellButtonSeven;

    @FXML
    private Button BuySellButtonEight;

    @FXML
    private Button ButtonGraphOne;

    @FXML
    private ImageView ImageViewGraphOne;

    @FXML
    private Button ButtonGraphTwo;

    @FXML
    private ImageView ImageViewGraphTwo;

    @FXML
    private Button ButtonGraphThree;

    @FXML
    private ImageView ImageViewGraphThree;

    @FXML
    private Button ButtonGraphFour;

    @FXML
    private ImageView ImageViewGraphFour;

    @FXML
    private Button ButtonGraphFive;

    @FXML
    private ImageView ImageViewGraphFive;

    @FXML
    private Button ButtonGraphSix;

    @FXML
    private ImageView ImageViewGraphSix;

    @FXML
    private Button ButtonGraphSeven;

    @FXML
    private ImageView ImageViewGraphSeven;

    @FXML
    private Button ButtonGraphEight;

    @FXML
    private ImageView ImageViewGraphEight;

    @FXML
    private ImageView ImageView;

    @FXML
    private Label nameLabelOne;

    @FXML
    private Label nameLabelTwo;

    @FXML
    private Label nameLabelThree;

    @FXML
    private Label nameLabelFour;

    @FXML
    private Label nameLabelFive;

    @FXML
    private Label nameLabelSix;

    @FXML
    private Label nameLabelSeven;

    @FXML
    private Label nameLabelEight;

    @FXML
    private Label stockPriceLabelOne;

    @FXML
    private Label stockPriceLabelTwo;

    @FXML
    private Label stockPriceLabelThree;

    @FXML
    private Label stockPriceLabelFour;

    @FXML
    private Label stockPriceLabelFive;

    @FXML
    private Label stockPriceLabelSix;

    @FXML
    private Label stockPriceLabelSeven;

    @FXML
    private Label stockPriceLabelEight;

    @FXML
    private Label changeLabelOne;

    @FXML
    private Label changeLabelTwo;

    @FXML
    private Label changeLabelThree;

    @FXML
    private Label changeLabelFour;

    @FXML
    private Label changeLabelFive;

    @FXML
    private Label changeLabelSix;

    @FXML
    private Label changeLabelSeven;

    @FXML
    private Label changeLabelEight;

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

        //create list of labels for name and add all labels to the list
        List<Label> nameLabels = new ArrayList<>();
        nameLabels.add(nameLabelOne);
        nameLabels.add(nameLabelTwo);
        nameLabels.add(nameLabelThree);
        nameLabels.add(nameLabelFour);
        nameLabels.add(nameLabelFive);
        nameLabels.add(nameLabelSix);
        nameLabels.add(nameLabelSeven);
        nameLabels.add(nameLabelEight);

        //create list of labels for stock price and add all labels to the list
        List<Label> stockPriceLabels = new ArrayList<>();
        stockPriceLabels.add(stockPriceLabelOne);
        stockPriceLabels.add(stockPriceLabelTwo);
        stockPriceLabels.add(stockPriceLabelThree);
        stockPriceLabels.add(stockPriceLabelFour);
        stockPriceLabels.add(stockPriceLabelFive);
        stockPriceLabels.add(stockPriceLabelSix);
        stockPriceLabels.add(stockPriceLabelSeven);
        stockPriceLabels.add(stockPriceLabelEight);

        //create list of labels for change and add all labels to the list
        List<Label> changeLabels = new ArrayList<>();
        changeLabels.add(changeLabelOne);
        changeLabels.add(changeLabelTwo);
        changeLabels.add(changeLabelThree);
        changeLabels.add(changeLabelFour);
        changeLabels.add(changeLabelFive);
        changeLabels.add(changeLabelSix);
        changeLabels.add(changeLabelSeven);
        changeLabels.add(changeLabelEight);

        //create list of buttons and add all buttons to the list
        List<Button> listOfButtons = new ArrayList<>();
        listOfButtons.add(ButtonGraphOne);
        listOfButtons.add(ButtonGraphTwo);
        listOfButtons.add(ButtonGraphThree);
        listOfButtons.add(ButtonGraphFour);
        listOfButtons.add(ButtonGraphFive);
        listOfButtons.add(ButtonGraphSix);
        listOfButtons.add(ButtonGraphSeven);
        listOfButtons.add(ButtonGraphEight);

        //fill data on the window
        for (int i = 0; i < Firebase_Controller.companies.size(); i++) {
            //fill out name
            nameLabels.get(i).setText("" + Firebase_Controller.companies.get(i).getName());

            //fill out stock price
            stockPriceLabels.get(i).setText("$" + Firebase_Controller.companies.get(i).getStockPrices().get(Firebase_Controller.companies.get(i).getStockPrices().size() - 1).getValue());

            //fill out change
            if (Firebase_Controller.companies.get(i).getStockPrices().size() == 1) {
                //if there is no change
                changeLabels.get(i).setText("$0.0 (0.0%)");
                changeLabels.get(i).setStyle("-fx-text-fill: #505050; -fx-border-width: 0 1 1 0; -fx-border-color: #000000");
            } else {
                //there is a change
                //declare necessary variables
                double change = 0;
                double rateOfChange = 0;
                double previousValue = Firebase_Controller.companies.get(i).getStockPrices().get(Firebase_Controller.companies.get(i).getStockPrices().size() - 2).getValue();
                double currentValue = Firebase_Controller.companies.get(i).getStockPrices().get(Firebase_Controller.companies.get(i).getStockPrices().size() - 1).getValue();

                //operate on the variables
                change = currentValue - previousValue;
                rateOfChange = ((currentValue - previousValue) / previousValue) * 100;

                //if change is positive or negative
                if (change > 0) {
                    //positive change - fill out the label
                    changeLabels.get(i).setText("$+" + round(change) + " (+" + round(rateOfChange) + "%)");
                    changeLabels.get(i).setStyle("-fx-text-fill: #04b906; -fx-border-width: 0 1 1 0; -fx-border-color: #000000");
                } else {
                    //negative change - fill out the label
                    changeLabels.get(i).setText("$" + round(change) + " (" + round(rateOfChange) + "%)");
                    changeLabels.get(i).setStyle("-fx-text-fill: #ff0000; -fx-border-width: 0 1 1 0; -fx-border-color: #000000");
                }
            }
        }

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

        //set style when mouse is on the area of the buy or sell button one button
        BuySellButtonOne.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonOne.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button one button
        BuySellButtonOne.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonOne.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button two button
        BuySellButtonTwo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonTwo.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button two button
        BuySellButtonTwo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonTwo.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button three button
        BuySellButtonThree.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonThree.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button three button
        BuySellButtonThree.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonThree.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button four button
        BuySellButtonFour.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonFour.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button four button
        BuySellButtonFour.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonFour.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button five button
        BuySellButtonFive.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonFive.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button five button
        BuySellButtonFive.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonFive.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button six button
        BuySellButtonSix.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonSix.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button six button
        BuySellButtonSix.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonSix.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button seven button
        BuySellButtonSeven.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonSeven.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button seven button
        BuySellButtonSeven.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonSeven.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the buy or sell button eight button
        BuySellButtonEight.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonEight.setStyle("-fx-background-color: #4395ae; -fx-border-color: #000000");
            }
        });

        //set style when mouse is out of the area of the buy or sell button eight button
        BuySellButtonEight.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuySellButtonEight.setStyle("-fx-background-color: #99cfe0; -fx-border-color: #000000");
            }
        });

        //set style when mouse is on the area of the graph one button
        ButtonGraphOne.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphOne.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphOne.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph one button
        ButtonGraphOne.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphOne.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphOne.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph two button
        ButtonGraphTwo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphTwo.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphTwo.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph two button
        ButtonGraphTwo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphTwo.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphTwo.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph three button
        ButtonGraphThree.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphThree.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphThree.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph three button
        ButtonGraphThree.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphThree.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphThree.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph four button
        ButtonGraphFour.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphFour.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphFour.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph four button
        ButtonGraphFour.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphFour.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphFour.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph five button
        ButtonGraphFive.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphFive.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphFive.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph five button
        ButtonGraphFive.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphFive.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphFive.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph six button
        ButtonGraphSix.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphSix.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphSix.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph six button
        ButtonGraphSix.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphSix.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphSix.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph seven button
        ButtonGraphSeven.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphSeven.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphSeven.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph seven button
        ButtonGraphSeven.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphSeven.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphSeven.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
            }
        });

        //set style when mouse is on the area of the graph eight button
        ButtonGraphEight.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphEight.setStyle("-fx-background-color: #898585; -fx-border-color: #000000");
                ImageViewGraphEight.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks2.png")));
            }
        });

        //set style when mouse is out of the area of the graph eight button
        ButtonGraphEight.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ButtonGraphEight.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
                ImageViewGraphEight.setImage(new Image(getClass().getResourceAsStream("/Photos/Stocks.png")));
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

    //method of going to buy or sell window
    @FXML
    private void goBuySellOne() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellTwo() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellThree() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellFour() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellFive() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellSix() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellSeven() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to buy or sell window
    @FXML
    private void goBuySellEight() throws IOException {
        main.showBuyingSelling();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphOne() throws IOException {
        indexOfStockGraphButton = 0;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphTwo() throws IOException {
        indexOfStockGraphButton = 1;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphThree() throws IOException {
        indexOfStockGraphButton = 2;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphFour() throws IOException {
        indexOfStockGraphButton = 3;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphFive() throws IOException {
        indexOfStockGraphButton = 4;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphSix() throws IOException {
        indexOfStockGraphButton = 5;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphSeven() throws IOException {
        indexOfStockGraphButton = 6;
        main.showGraph();
    }

    //method of going to the graph window and changing the value of the index of stock graph button
    @FXML
    private void goGraphEight() throws IOException {
        indexOfStockGraphButton = 7;
        main.showGraph();
    }

    //method of rounding double variables to two decimal places
    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
