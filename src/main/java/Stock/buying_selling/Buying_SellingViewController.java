package Stock.buying_selling; //package buying_selling inside package Stock

import Stock.*;
import javafx.collections.FXCollections; //Graphical User Interface import needed for the ComboBox
import javafx.collections.ObservableList; //Graphical User Interface import needed for the ComboBox
import javafx.event.ActionEvent; //Graphical User Interface import needed for the button to be working without FXML operations
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.ComboBox; //Graphical User Interface import needed to operate with ComboBox
import javafx.scene.control.TextField; //Graphical User Interface import needed to operate with TextField
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.time.ZonedDateTime; //needed for using current milliseconds
import java.util.ArrayList; //needed for using lists
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class Buying_SellingViewController implements Initializable {

    //declaring variables
    private Main main;

    public static boolean buyOrSell;

    //declaring FXML variables
    @FXML
    private Button BuyingButton;

    @FXML
    private Button SellingButton;

    @FXML
    public Button EndButton;

    @FXML
    public TextField AmountTextField;

    @FXML
    private ComboBox<String> ComboBoxOfInstruments;

    //initialize method starts when this window is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList();

        //loop for filling list out with companies names
        for (int i = 0; i < Firebase_Controller.companies.size(); i++) {
            options.add(Firebase_Controller.companies.get(i).getName());
        }

        //setting items of ComboBox to be the items from the list
        ComboBoxOfInstruments.setItems(options);

        //set style when mouse is on the area of the buying button
        BuyingButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuyingButton.setStyle("-fx-background-color: #06bb2d; -fx-border-color: #069d27; -fx-border-width: 5 5 5 5; -fx-border-radius: 10 0 0 10; -fx-background-radius: 15 0 0 15");
            }
        });

        //set style when mouse is out of the area of the buying button
        BuyingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuyingButton.setStyle("-fx-background-color: #00ff3f; -fx-border-color: #000000; -fx-border-radius: 10 0 0 10; -fx-background-radius: 15 0 0 15");
            }
        });

        //set style when mouse is on the area of the selling button
        SellingButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SellingButton.setStyle("-fx-background-color: #f1260f; -fx-border-color: #bf1b09; -fx-border-width: 5 5 5 5; -fx-border-radius: 0 10 10 0; -fx-background-radius: 0 15 15 0");
            }
        });

        //set style when mouse is out of the area of the selling button
        SellingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SellingButton.setStyle("-fx-background-color: #fa8072; -fx-border-color: #000000; -fx-border-radius: 0 10 10 0; -fx-background-radius: 0 15 15 0");
            }
        });

        //action when end button is clicked - when a user wants to create a pending order
        EndButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //creating new pending order

                //checking if the amount field's input is correct
                if (AmountTextField.getText().equals("") || AmountTextField.getText() == null ||
                        ComboBoxOfInstruments.getSelectionModel().isEmpty() ||
                        ifStringIsNumber(AmountTextField.getText()) == false) {
                    try {
                        //amount field's input is incorrect - showing error window
                        Main.showBuySellError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (buyOrSell == true && (Firebase_Controller.persons.get(Main.IndexOfUser).getCurrentMoney()
                        < Double.parseDouble(AmountTextField.getText()) *
                        Firebase_Controller.companies.get(getIndexOfTheCompany(Firebase_Controller.companies,
                                ComboBoxOfInstruments.getValue())).getStockPrices().get(getLastStock(Firebase_Controller.companies,
                                ComboBoxOfInstruments.getValue())).getValue())) {
                    try {
                        //amount field's input is incorrect - showing error window
                        Main.showBuySellError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (buyOrSell == false && (Firebase_Controller.persons.get(Main.IndexOfUser).getStockOwned().
                        get(getIndexOfTheCompany(Firebase_Controller.companies, ComboBoxOfInstruments.getValue())).
                        getAmount() < Double.parseDouble(AmountTextField.getText()))) {
                    try {
                        //amount field's input is incorrect - showing error window
                        Main.showBuySellError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder() != null &&
                        Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().size() == 7) {
                    try {
                        //amount field's input is incorrect - showing error window
                        Main.showBuySellError();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //amount field's input is correct
                    try {
                        //update data to be the current data in Firebase controller
                        Firebase_Controller.getListOfPersons();
                        Firebase_Controller.getListOfCompanies();

                        //sleep for one second for the process to be executed
                        TimeUnit.SECONDS.sleep(1);

                        //create list of current pending orders
                        List<PendingOrder> existingPendingOrders = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder();

                        //if there is no pending order in the Firebase
                        if (existingPendingOrders == null) {
                            existingPendingOrders = new ArrayList<>();
                        }

                        //creating new pending order for the pending order desired by the user to be created
                        PendingOrder pendingOrder = new PendingOrder();

                        //setting appropriate values of the pending order from the input data
                        pendingOrder.setAmount(Integer.parseInt(AmountTextField.getText()));
                        pendingOrder.setBuyOrSell(buyOrSell);
                        pendingOrder.setNameOfTheCompany(ComboBoxOfInstruments.getValue());

                        //creating time with current time when user wants to create a transaction
                        Time time = new Time();
                        time.setYear(LocalDateTime.now().getYear());
                        time.setMonth(LocalDateTime.now().getMonthValue());
                        time.setDay(LocalDateTime.now().getDayOfMonth());
                        time.setHour(LocalDateTime.now().getHour());
                        time.setMinute(LocalDateTime.now().getMinute());
                        time.setSecond(LocalDateTime.now().getSecond());
                        time.setMillisecond(ZonedDateTime.now().toInstant().toEpochMilli());

                        //setting time of pending order
                        pendingOrder.setTime(time);

                        //adding new pending order to the list of pending orders
                        existingPendingOrders.add(pendingOrder);

                        //saving new list of pending orders into the Firebase
                        Firebase_Controller.savePendingOrder(existingPendingOrders);

                        //closing the buying or selling window
                        Main.closeBuyingSelling();

                        //checking what is the index and open appropriate window
                        if (Main.indexOfAccountOrStocks == -1) {
                            Main.showAccountView();
                        } else if (Main.indexOfAccountOrStocks == 0) {
                            Main.showAccountView();
                        } else {
                            Main.showStocks();
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //set style when mouse is on the area of the end button
        EndButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EndButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the end button
        EndButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EndButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });
    }

    //methods connected with the FXML file

    //what happens when a buying button is clicked
    @FXML
    private void goBuy() {
        //style changes
        BuyingButton.setStyle("-fx-background-color: #06bb2d; -fx-border-color: #069d27; -fx-border-width: 5 5 5 5; " +
                "-fx-border-radius: 10 0 0 10; -fx-background-radius: 15 0 0 15");
        SellingButton.setStyle("-fx-background-color: #fa8072; -fx-border-color: #000000; -fx-border-radius: 0 10 10 0;" +
                " -fx-background-radius: 0 15 15 0");
        //setting current variable
        buyOrSell = true;

        //set style when mouse is on the area of the buying button
        BuyingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuyingButton.setStyle("-fx-background-color: #06bb2d; -fx-border-color: #069d27; -fx-border-width: 5 5 5 5;" +
                        " -fx-border-radius: 10 0 0 10; -fx-background-radius: 15 0 0 15");
            }
        });

        //set style when mouse is on the area of the selling button
        SellingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SellingButton.setStyle("-fx-background-color: #fa8072; -fx-border-color: #000000; -fx-border-radius: 0 10 10 0;" +
                        " -fx-background-radius: 0 15 15 0");
            }
        });
    }

    //what happens when a selling button is clicked
    @FXML
    private void goSell() {
        //style changes
        BuyingButton.setStyle("-fx-background-color: #00ff3f; -fx-border-color: #000000; -fx-border-radius: 10 0 0 10; " +
                "-fx-background-radius: 15 0 0 15");
        SellingButton.setStyle("-fx-background-color: #f1260f; -fx-border-color: #bf1b09; -fx-border-width: 5 5 5 5; " +
                "-fx-border-radius: 0 10 10 0; -fx-background-radius: 0 15 15 0");
        //setting current variable
        buyOrSell = false;

        //set style when mouse is out of the area of the selling button
        SellingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SellingButton.setStyle("-fx-background-color: #f1260f; -fx-border-color: #bf1b09; -fx-border-width: 5 5 5 5; " +
                        "-fx-border-radius: 0 10 10 0; -fx-background-radius: 0 15 15 0");
            }
        });

        //set style when mouse is out of the area of the buying button
        BuyingButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuyingButton.setStyle("-fx-background-color: #00ff3f; -fx-border-color: #000000; -fx-border-radius: 10 0 0 10; " +
                        "-fx-background-radius: 15 0 0 15");
            }
        });
    }

    //method for checking if string is an Integer
    public boolean ifStringIsNumber(String string) {
        int falseNumbers = 0;

        //creating array with every 1-digital number
        String[] arrayOfNumbers = new String[10];
        arrayOfNumbers[0] = "0";
        arrayOfNumbers[1] = "1";
        arrayOfNumbers[2] = "2";
        arrayOfNumbers[3] = "3";
        arrayOfNumbers[4] = "4";
        arrayOfNumbers[5] = "5";
        arrayOfNumbers[6] = "6";
        arrayOfNumbers[7] = "7";
        arrayOfNumbers[8] = "8";
        arrayOfNumbers[9] = "9";

        //loop for checking if every chart of the string is a number
        for (int i = 0; i < string.length(); i++) {
            int current = 0;
            for (int j = 0; j < arrayOfNumbers.length; j++) {
                if (string.substring(i, i + 1).equals(arrayOfNumbers[j])) {
                    current++;
                }
            }
            if (current > 0) {

            } else {
                falseNumbers++;
            }
        }

        //returning the final result of the method
        if (falseNumbers > 0) {
            return false;
        } else {
            return true;
        }
    }

    //method of getting the index of the company with given string name
    public int getIndexOfTheCompany(List<Company> companies, String name) {
        int finalNumber = -1;
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getName().equals(name)) {
                finalNumber = i;
            }
        }
        return finalNumber;
    }

    //method of getting the last stock of the company with the given string name
    public int getLastStock(List<Company> companies, String name) {
        int index = getIndexOfTheCompany(companies, name);
        return Firebase_Controller.companies.get(index).getStockPrices().size() - 1;
    }
}
