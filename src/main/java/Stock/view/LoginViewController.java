package Stock.view;

import Stock.Firebase_Controller;
import javafx.animation.Animation; //Graphical User Interface import needed for the animation of clock
import javafx.animation.KeyFrame; //Graphical User Interface import needed for the animation of clock
import javafx.animation.Timeline; //Graphical User Interface import needed for the animation of clock
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import Stock.Main;
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.control.PasswordField; //Graphical User Interface import needed to operate with passwordFields
import javafx.scene.control.TextField; //Graphical User Interface import needed to operate with textFields
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.util.Duration; //Graphical User Interface import needed for the animation of clock

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.time.LocalDateTime; //needed for using current data about date and hour
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class LoginViewController implements Initializable {

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
    private Button OKButton;

    @FXML
    public TextField LoginTextField;

    @FXML
    public PasswordField PasswordTextField;

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

        //set style when mouse is on the area of the ok button
        OKButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OKButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });

        //set style when mouse is out of the area of the ok button
        OKButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OKButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        });
    }

    //method which allows to log into the application
    @FXML
    private void goHome() throws IOException, InterruptedException {
        //update information about admin status in the Firebase_Controller
        Firebase_Controller.getAdminStatus();

        //update data from Firebase
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();

        //sleep for 1 second
        TimeUnit.SECONDS.sleep(1);

        //get login and password from the user
        String login = LoginTextField.getText();
        String password = PasswordTextField.getText();

        //create necessary variables for the algorithm
        boolean ifLoginCorrect = false;
        boolean ifPasswordCorrect = false;
        int indexOfCorrectUser = -1;

        //check if the pair of login and password is correct
        for (int i = 0; i < Firebase_Controller.persons.size(); i++) {
            //if login is the same
            if (Firebase_Controller.persons.get(i).getLogin().equals(login)) {
                ifLoginCorrect = true;
                indexOfCorrectUser = i;
                if (Firebase_Controller.persons.get(indexOfCorrectUser).getPassword().equals(password)) {
                    //if password is the same
                    ifPasswordCorrect = true;
                }
            }
        }

        //if both login and password is the same
        if (ifLoginCorrect == true && ifPasswordCorrect == true && Firebase_Controller.adminStatus == true) {
            //open main window
            Main.IndexOfUser = indexOfCorrectUser;
            Main.showMainView();
        } else if (LoginTextField.getText().equals(null) || LoginTextField.getText().equals("") ||
                PasswordTextField.getText().equals(null) || PasswordTextField.getText().equals("")) {
            //else show login error
            Main.showLoginError();
        } else {
            //else show login window
            Main.showLoginError();
        }
    }
}
