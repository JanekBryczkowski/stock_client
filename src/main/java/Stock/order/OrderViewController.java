package Stock.order;

import Stock.Firebase_Controller;
import Stock.Main;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.event.ActionEvent; //Graphical User Interface import needed for the button to be working without FXML operations
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.image.Image; //Graphical User Interface import needed to use images
import javafx.scene.image.ImageView; //Graphical User Interface import needed to use images in ImageViews
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.scene.layout.HBox; //Graphical User Interface import needed to operate with HBoxes
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ArrayList; //needed for using lists
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class OrderViewController implements Initializable {

    //declaring variables
    private Main main;

    public static int numberRepresentingDeleteButton = -1;

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
    private Button Delete0Button;

    @FXML
    private Button Delete1Button;

    @FXML
    private Button Delete2Button;

    @FXML
    private Button Delete3Button;

    @FXML
    private Button Delete4Button;

    @FXML
    private Button Delete5Button;

    @FXML
    private Button Delete6Button;

    @FXML
    private ImageView ImageView;

    @FXML
    private Label Date0Label;

    @FXML
    private Label Date1Label;

    @FXML
    private Label Date2Label;

    @FXML
    private Label Date3Label;

    @FXML
    private Label Date4Label;

    @FXML
    private Label Date5Label;

    @FXML
    private Label Date6Label;

    @FXML
    private Label Hour0Label;

    @FXML
    private Label Hour1Label;

    @FXML
    private Label Hour2Label;

    @FXML
    private Label Hour3Label;

    @FXML
    private Label Hour4Label;

    @FXML
    private Label Hour5Label;

    @FXML
    private Label Hour6Label;

    @FXML
    private Label Name0Label;

    @FXML
    private Label Name1Label;

    @FXML
    private Label Name2Label;

    @FXML
    private Label Name3Label;

    @FXML
    private Label Name4Label;

    @FXML
    private Label Name5Label;

    @FXML
    private Label Name6Label;

    @FXML
    private Label Amount0Label;

    @FXML
    private Label Amount1Label;

    @FXML
    private Label Amount2Label;

    @FXML
    private Label Amount3Label;

    @FXML
    private Label Amount4Label;

    @FXML
    private Label Amount5Label;

    @FXML
    private Label Amount6Label;

    @FXML
    private Label BuyOrSell0Label;

    @FXML
    private Label BuyOrSell1Label;

    @FXML
    private Label BuyOrSell2Label;

    @FXML
    private Label BuyOrSell3Label;

    @FXML
    private Label BuyOrSell4Label;

    @FXML
    private Label BuyOrSell5Label;

    @FXML
    private Label BuyOrSell6Label;

    @FXML
    private HBox HBox0;

    @FXML
    private HBox HBox1;

    @FXML
    private HBox HBox2;

    @FXML
    private HBox HBox3;

    @FXML
    private HBox HBox4;

    @FXML
    private HBox HBox5;

    @FXML
    private HBox HBox6;

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

        //create list of labels for date labels and add labels to the list
        List<Label> listOfLabelOfDate = new ArrayList<>();
        listOfLabelOfDate.add(Date0Label);
        listOfLabelOfDate.add(Date1Label);
        listOfLabelOfDate.add(Date2Label);
        listOfLabelOfDate.add(Date3Label);
        listOfLabelOfDate.add(Date4Label);
        listOfLabelOfDate.add(Date5Label);
        listOfLabelOfDate.add(Date6Label);

        //create list of labels for hour labels and add labels to the list
        List<Label> listOfLabelOfHour = new ArrayList<>();
        listOfLabelOfHour.add(Hour0Label);
        listOfLabelOfHour.add(Hour1Label);
        listOfLabelOfHour.add(Hour2Label);
        listOfLabelOfHour.add(Hour3Label);
        listOfLabelOfHour.add(Hour4Label);
        listOfLabelOfHour.add(Hour5Label);
        listOfLabelOfHour.add(Hour6Label);

        //create list of labels for name labels and add labels to the list
        List<Label> listOfLabelOfName = new ArrayList<>();
        listOfLabelOfName.add(Name0Label);
        listOfLabelOfName.add(Name1Label);
        listOfLabelOfName.add(Name2Label);
        listOfLabelOfName.add(Name3Label);
        listOfLabelOfName.add(Name4Label);
        listOfLabelOfName.add(Name5Label);
        listOfLabelOfName.add(Name6Label);

        //create list of labels for amount labels and add labels to the list
        List<Label> listOfLabelOfAmount = new ArrayList<>();
        listOfLabelOfAmount.add(Amount0Label);
        listOfLabelOfAmount.add(Amount1Label);
        listOfLabelOfAmount.add(Amount2Label);
        listOfLabelOfAmount.add(Amount3Label);
        listOfLabelOfAmount.add(Amount4Label);
        listOfLabelOfAmount.add(Amount5Label);
        listOfLabelOfAmount.add(Amount6Label);

        //create list of labels for buy or sell labels and add labels to the list
        List<Label> listOfLabelOfBuyOrSell = new ArrayList<>();
        listOfLabelOfBuyOrSell.add(BuyOrSell0Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell1Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell2Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell3Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell4Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell5Label);
        listOfLabelOfBuyOrSell.add(BuyOrSell6Label);

        //create list of buttons for delete buttons and add buttons to the list
        List<Button> listOfLabelOfDeleteButton = new ArrayList<>();
        listOfLabelOfDeleteButton.add(Delete0Button);
        listOfLabelOfDeleteButton.add(Delete1Button);
        listOfLabelOfDeleteButton.add(Delete2Button);
        listOfLabelOfDeleteButton.add(Delete3Button);
        listOfLabelOfDeleteButton.add(Delete4Button);
        listOfLabelOfDeleteButton.add(Delete5Button);
        listOfLabelOfDeleteButton.add(Delete6Button);

        //create list of HBoxes and add HBoxes to the list
        List<HBox> listOfHBox = new ArrayList<>();
        listOfHBox.add(HBox0);
        listOfHBox.add(HBox1);
        listOfHBox.add(HBox2);
        listOfHBox.add(HBox3);
        listOfHBox.add(HBox4);
        listOfHBox.add(HBox5);
        listOfHBox.add(HBox6);

        //check if there is any pending order in the Firebase
        if (Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder() == null) {
            //there is no pending order in the Firebase

            //data on the page should be displayed as empty
            for (int i = 0; i < 7; i++) {
                listOfLabelOfDate.get(i).setText("");
                listOfLabelOfHour.get(i).setText("");
                listOfLabelOfName.get(i).setText("");
                listOfLabelOfAmount.get(i).setText("");
                listOfLabelOfBuyOrSell.get(i).setText("");
                listOfLabelOfBuyOrSell.get(i).setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0;");
                listOfHBox.get(i).getChildren().remove(listOfLabelOfDeleteButton.get(i));
            }
        } else {
            //there is some pending order/pending orders in the Firebase

            //creating labels with data from each pending order
            for (int i = 0; i < Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().size(); i++) {
                //filling out date label
                int dayInt = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getDay();
                String dayString;
                if (dayInt < 10) {
                    dayString = "0" + Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getDay();
                } else {
                    dayString = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getDay());
                }

                int monthInt = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMonth();
                String monthString;
                if (monthInt < 10) {
                    monthString = "0" + Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMonth();
                } else {
                    monthString = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMonth());
                }

                String year = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getYear());
                listOfLabelOfDate.get(i).setText(dayString + "/" + monthString + "/" + year);

                //filling out hour label
                int hourInt = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getHour();
                String hourString;
                if (hourInt < 10) {
                    hourString = "0" + Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getHour();
                } else {
                    hourString = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getHour());
                }

                int minuteInt = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMinute();
                String minuteString;
                if (minuteInt < 10) {
                    minuteString = "0" + Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMinute();
                } else {
                    minuteString = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getMinute());
                }

                int secondInt = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getSecond();
                String secondString;
                if (secondInt < 10) {
                    secondString = "0" + Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getSecond();
                } else {
                    secondString = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getTime().getSecond());
                }

                listOfLabelOfHour.get(i).setText(hourString + ":" + minuteString + ":" + secondString);

                //filling out name of the company label
                String name = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getNameOfTheCompany());
                listOfLabelOfName.get(i).setText(name);

                //filling out amount label
                String amount = String.valueOf(Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).getAmount());
                listOfLabelOfAmount.get(i).setText(amount);

                //filling out true or false label
                boolean buyOrSell = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().get(i).isBuyOrSell();
                if (buyOrSell == true) {
                    listOfLabelOfBuyOrSell.get(i).setText("BUY");
                    listOfLabelOfBuyOrSell.get(i).setStyle("-fx-text-fill: #04b906; -fx-border-color: #000000; -fx-border-width: 0 1 1 0;");
                } else {
                    listOfLabelOfBuyOrSell.get(i).setText("SELL");
                    listOfLabelOfBuyOrSell.get(i).setStyle("-fx-text-fill: #ff0000; -fx-border-color: #000000; -fx-border-width: 0 1 1 0;");
                }
            }

            //feel the rest of the labels to be empty if necessary
            for (int i = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder().size(); i < 7; i++) {
                listOfLabelOfDate.get(i).setText("");
                listOfLabelOfHour.get(i).setText("");
                listOfLabelOfName.get(i).setText("");
                listOfLabelOfAmount.get(i).setText("");
                listOfLabelOfBuyOrSell.get(i).setText("");
                listOfLabelOfBuyOrSell.get(i).setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0;");
                listOfHBox.get(i).getChildren().remove(listOfLabelOfDeleteButton.get(i));
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

        //set style when mouse is on the area of the delete0 button
        Delete0Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete0Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete0 button
        Delete0Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete0Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete1 button
        Delete1Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete1Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete1 button
        Delete1Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete1Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete2 button
        Delete2Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete2Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete2 button
        Delete2Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete2Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete3 button
        Delete3Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete3Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete3 button
        Delete3Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete3Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete4 button
        Delete4Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete4Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete4 button
        Delete4Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete4Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete5 button
        Delete5Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete5Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete5 button
        Delete5Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete5Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is on the area of the delete6 button
        Delete6Button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete6Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #fb4242; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the delete6 button
        Delete6Button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Delete6Button.setStyle("-fx-border-color: #ff0000; -fx-background-color: #ff8383; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //action when the delete0 button is clicked
        Delete0Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 0;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete1 button is clicked
        Delete1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 1;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete2 button is clicked
        Delete2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 2;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete3 button is clicked
        Delete3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 3;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete4 button is clicked
        Delete4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 4;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete5 button is clicked
        Delete5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 5;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //action when the delete6 button is clicked
        Delete6Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numberRepresentingDeleteButton = 6;

                    //show delete window
                    Main.showDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
}