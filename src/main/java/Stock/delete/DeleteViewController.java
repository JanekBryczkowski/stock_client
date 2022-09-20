package Stock.delete; //package delete inside package Stock

import Stock.Firebase_Controller;
import Stock.Main;
import Stock.PendingOrder;
import Stock.order.OrderViewController;
import javafx.event.ActionEvent; //Graphical User Interface import needed for the button to be working without FXML operations
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.util.List; //needed for using lists
import java.util.ResourceBundle; //needed for the initializable method
import java.util.concurrent.TimeUnit; //needed to stop the application for a second

public class DeleteViewController implements Initializable {

    //declaring variables
    private Main main;

    //declaring FXML variables
    @FXML
    private Button YesButton;

    @FXML
    private Button NoButton;

    //initialize method starts when this window is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //updating data from Firebase
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();
        try {
            //wait 1 second
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //set style when mouse is on the area of the yes button
        YesButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                YesButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the yes button
        YesButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                YesButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //action when yes button is clicked - when a user wants to delete pending order
        YesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //create list of existing pending orders
                    List<PendingOrder> pendingOrders = Firebase_Controller.persons.get(Main.IndexOfUser).getPendingOrder();

                    //delete selected pending order from the list
                    pendingOrders.remove(OrderViewController.numberRepresentingDeleteButton);

                    //update list of pending orders to the Firebase
                    Firebase_Controller.changePendingOrder(pendingOrders);
                    OrderViewController.numberRepresentingDeleteButton = -1;

                    //close window and show previous window
                    Main.closeDelete();
                    Main.showOrderView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //set style when mouse is on the area of the no button
        NoButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NoButton.setStyle("-fx-background-color: #bfa005; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //set style when mouse is out of the area of the no button
        NoButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NoButton.setStyle("-fx-background-color: #ffdc00; -fx-border-color: #000000; -fx-background-radius: 8; -fx-border-radius: 8");
            }
        });

        //action when no button is clicked - when a user does not want to delete pending order
        NoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //close window
                    Main.closeDelete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
