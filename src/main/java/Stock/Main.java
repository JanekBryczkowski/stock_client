package Stock;

import javafx.application.Application; //import for the class to extend application class
import javafx.fxml.FXMLLoader; //import necessary to load FXML files
import javafx.scene.Scene; //import necessary for scene to be operated
import javafx.scene.layout.AnchorPane; //Graphical User Interface import to operate with AnchorPane
import javafx.stage.Stage; //import necessary to operate on stages

import java.io.IOException; //needed for exception when button clicked

public class Main extends Application {

    //declaring stages and other variables
    public static Stage primaryStage;
    public static AnchorPane mainLayout;
    public static Stage buySellStage = new Stage();
    public static Stage deleteStage = new Stage();
    public static Stage graphStage = new Stage();
    public static Stage loginErrorStage = new Stage();
    public static Stage buySellErrorStage = new Stage();
    public static int IndexOfUser = -1;
    public static int indexOfAccountOrStocks = -1;

    //start method which opens when the program is opened (at start)
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        //execute method from Firebase controller for initializing the database
        Firebase_Controller.initDatabase();

        //update the data about persons and companies from Firebase
        Firebase_Controller.getListOfPersons();
        Firebase_Controller.getListOfCompanies();

        //set primary's stage purpose and title
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock Application");

        //open show login method
        showLogin();
    }

    //method which opens login window
    public static void showLogin() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/LoginView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens main window
    public static void showMainView() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/MainView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens account window
    public static void showAccountView() throws IOException {
        indexOfAccountOrStocks = 0;

        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/AccountView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens order window
    public static void showOrderView() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/OrderView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens stocks window
    public static void showStocks() throws IOException {
        indexOfAccountOrStocks = 1;

        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/StocksView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens ranking window
    public static void showRanking() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/RankingView.fxml"));

        //loads FXML file as a mainLayout
        mainLayout = loader.load();

        //set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method which opens buying and selling window
    public static void showBuyingSelling() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/Buying_SellingView.fxml"));

        //loads FXML file as a buyAndSell
        AnchorPane buyAndSell = loader.load();

        buySellStage.setTitle("Buy or sell");
        buySellStage.setAlwaysOnTop(true);

        //set scene and stage
        Scene scene = new Scene(buyAndSell);
        buySellStage.setScene(scene);
        buySellStage.show();
    }

    //method which closes buying and selling window
    public static void closeBuyingSelling() throws IOException {
        //close
        buySellStage.close();
    }

    //method which opens delete window
    public static void showDelete() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/DeleteView.fxml"));

        //loads FXML file as a deleteAnchor
        AnchorPane deleteAnchor = loader.load();

        deleteStage.setTitle("Delete");
        deleteStage.setAlwaysOnTop(true);

        //set scene and stage
        Scene scene = new Scene(deleteAnchor);
        deleteStage.setScene(scene);
        deleteStage.show();
    }

    //method which closes delete window
    public static void closeDelete() throws IOException {
        //close
        deleteStage.close();
    }

    //method which opens graph window
    public static void showGraph() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/GraphView.fxml"));

        //loads FXML file as a graphAnchor
        AnchorPane graphAnchor = loader.load();

        graphStage.setTitle("Graph");
        graphStage.setAlwaysOnTop(true);

        //set scene and stage
        Scene scene = new Scene(graphAnchor);
        graphStage.setScene(scene);
        graphStage.show();
    }

    //method which closes graph window
    public static void closeGraph() throws IOException {
        //close
        graphStage.close();
    }

    //method which opens login error window
    public static void showLoginError() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/LoginErrorView.fxml"));

        //loads FXML file as a LoginErrorAnchorPane
        AnchorPane LoginErrorAnchorPane = loader.load();

        loginErrorStage.setTitle("Error");
        loginErrorStage.setAlwaysOnTop(true);

        //set scene and stage
        Scene scene = new Scene(LoginErrorAnchorPane);
        loginErrorStage.setScene(scene);
        loginErrorStage.show();
    }

    //method which closes login error window
    public static void closeLoginError() throws IOException {
        //close
        loginErrorStage.close();
    }

    //method which opens buying or selling error window
    public static void showBuySellError() throws IOException {
        //creates loader to load FXML file
        FXMLLoader loader = new FXMLLoader();

        //shows the location of the FXML file
        loader.setLocation(Main.class.getResource("/FXML/BuySellErrorView.fxml"));

        //loads FXML file as a BuySellErrorAnchorPane
        AnchorPane BuySellErrorAnchorPane = loader.load();

        buySellErrorStage.setTitle("Error");
        buySellErrorStage.setAlwaysOnTop(true);

        //set scene and stage
        Scene scene = new Scene(BuySellErrorAnchorPane);
        buySellErrorStage.setScene(scene);
        buySellErrorStage.show();
    }

    //method which closes buying or selling error window
    public static void closeBuySellError() throws IOException {
        //close
        buySellErrorStage.close();
    }

    //main method
    public static void main(String[] args) {
        launch(args);
    }
}
