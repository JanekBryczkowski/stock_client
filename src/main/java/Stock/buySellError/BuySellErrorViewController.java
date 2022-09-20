package Stock.buySellError; //package buySellError inside package Stock

import Stock.Main;
import javafx.event.ActionEvent; //Graphical User Interface import needed for the button to be working without FXML operations
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.util.ResourceBundle; //needed for the initializable method

public class BuySellErrorViewController implements Initializable {

    //declaring variables
    private Main main;

    //declaring FXML variable
    @FXML
    private Button OKButton;

    //initialize method starts when this window is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set style when mouse is on the area of the OKButton button
        OKButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OKButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the OKButton button
        OKButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OKButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //action when OK button is clicked
        OKButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //close buySellError window
                    Main.closeBuySellError();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}