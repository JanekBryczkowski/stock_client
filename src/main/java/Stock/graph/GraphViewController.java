package Stock.graph; //package graph inside package Stock

import Stock.Firebase_Controller;
import Stock.Main;
import Stock.stocks.StocksViewController;
import javafx.event.ActionEvent; //Graphical User Interface import needed for the button to be working without FXML operations
import javafx.event.EventHandler; //Graphical User Interface import needed to change colors of buttons when entered
import javafx.fxml.FXML; //Graphical User Interface import needed to create connection to FXML files
import javafx.fxml.Initializable; //Graphical User Interface import needed to create initializable method
import javafx.scene.chart.LineChart; //Graphical User Interface import needed to create a lineChart
import javafx.scene.chart.NumberAxis; //Graphical User Interface import needed to create axes
import javafx.scene.chart.XYChart; //Graphical User Interface import needed to be able to fill data into the lineChart
import javafx.scene.control.Button; //Graphical User Interface import needed to operate with buttons
import javafx.scene.control.Label; //Graphical User Interface import needed to operate with labels
import javafx.scene.input.MouseEvent; //Graphical User Interface import needed to detect mouse's events
import javafx.scene.paint.Color; //Graphical User Interface import needed to operate with colors

import java.io.IOException; //needed for exception when button clicked
import java.net.URL; //needed for the initializable method
import java.util.ResourceBundle; //needed for the initializable method

public class GraphViewController implements Initializable {

    //declaring variables
    private Main main;

    //declaring FXML variables
    @FXML
    private Button FiveButtonPeriod;

    @FXML
    private Button TenButtonPeriod;

    @FXML
    private Button TwentyButtonPeriod;

    @FXML
    private Button FiftyButtonPeriod;

    @FXML
    private Button HundredButtonPeriod;

    @FXML
    private Button TwoHundredButtonPeriod;

    @FXML
    private Button FiveHundredButtonPeriod;

    @FXML
    private Button ThousandButtonPeriod;

    @FXML
    private Button CancelButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label changeInMoneyLabel;

    @FXML
    private Label changeInPercentLabel;

    @FXML
    private LineChart<Integer, Double> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    //declaring and initializing int of number of periods desired
    public int numberOfPeriods = -1;

    //initialize method starts when this window is opened
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set name label to the correct name of the company
        nameLabel.setText(Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getName());

        //check if there was a change comparing last stock price and one before
        if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() == 1) {
            //no change - price stays the same
            changeInMoneyLabel.setText("$0.0");
            changeInMoneyLabel.setStyle("-fx-text-fill: #505050;");
        } else {
            //last stock price is not the same as the previous one
            //declaring and initializing variables necessary for determining the growth or decrease
            double change = 0;
            double rateOfChange = 0;
            double previousValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - 2).getValue();
            double currentValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - 1).getValue();

            //operations on the variables
            change = currentValue - previousValue;
            rateOfChange = ((currentValue - previousValue) / previousValue) * 100;
            if (change > 0) {
                //setting text of rounded change to be positive
                changeInMoneyLabel.setText("$+" + round(change));
                changeInMoneyLabel.setStyle("-fx-text-fill: #04b906;");
            } else {
                //setting negative rounded change
                changeInMoneyLabel.setText("$" + round(change));
                changeInMoneyLabel.setStyle("-fx-text-fill: #ff0000;");
            }
        }

        //do the same procedure as previously but with percents
        if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() == 1) {
            changeInPercentLabel.setText("(0.0%)");
            changeInPercentLabel.setStyle("-fx-text-fill: #505050;");
        } else {
            double change = 0;
            double rateOfChange = 0;
            double previousValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - 2).getValue();
            double currentValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - 1).getValue();
            change = currentValue - previousValue;
            rateOfChange = ((currentValue - previousValue) / previousValue) * 100;
            if (change > 0) {
                changeInPercentLabel.setText("(+" + round(rateOfChange) + "%)");
                changeInPercentLabel.setStyle("-fx-text-fill: #04b906;");
            } else {
                changeInPercentLabel.setText("(" + round(rateOfChange) + "%)");
                changeInPercentLabel.setStyle("-fx-text-fill: #ff0000;");
            }
        }

        //set default number of periods to be 20
        numberOfPeriods = 20;

        //get number of stock prices from the Firebase
        int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();

        //if the number of stock prices is less than number of periods
        if (numberOfStockPrices < numberOfPeriods) {
            //create variables necessary to operate with the lineChart
            double lowestStock;
            double highestStock;
            double[] array = new double[numberOfStockPrices];

            //create data series when data will be put
            XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();

            int finalVariable;
            if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size()
                    - numberOfPeriods < 0) {
                finalVariable = numberOfStockPrices - 1;
            } else {
                finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                        getStockPrices().size() - numberOfPeriods;
            }

            //algorithm for creating data with x and y coordinates
            for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV;
                 j--, i++, variable++) {
                int xValue = i + 1;
                double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                        getStockPrices().get(variable).getValue();
                array[i] = yValue;
                series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
            }

            //using sort method to determine the highest and the lowest amount to determine the graph's boundaries
            sort(array);
            lowestStock = array[0];
            highestStock = array[array.length - 1];
            double spacing = (highestStock - lowestStock) * 0.8;

            //adding data to the chart
            lineChart.getData().clear();
            lineChart.getData().add(series);

            //style of the chart
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxWidth(510);
            lineChart.getXAxis().setMinWidth(510);
            lineChart.getXAxis().setPrefWidth(510);

            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxWidth(50);
            lineChart.getYAxis().setMinWidth(50);
            lineChart.getYAxis().setPrefWidth(50);

            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(1);
            xAxis.setUpperBound(numberOfStockPrices);
            xAxis.setTickUnit(1);

            yAxis.setAutoRanging(false);
            if (lowestStock - spacing < 0) {
                yAxis.setLowerBound(0);
                yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                yAxis.setTickUnit(spacing);
            } else {
                yAxis.setLowerBound(lowestStock - spacing);
                yAxis.setUpperBound(highestStock + spacing);
                yAxis.setTickUnit(spacing);
            }
            yAxis.setMinorTickVisible(false);
        } else {
            //if the number of stock prices is greater or equal than number of periods

            //create variables necessary to operate with the lineChart
            double lowestStock;
            double highestStock;
            double[] array = new double[numberOfPeriods];

            //create data series when data will be put
            XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();

            //algorithm for creating data with x and y coordinates
            for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.
                    indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                int xValue = i + 1;
                double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                        getStockPrices().get(variable).getValue();
                series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                array[i] = yValue;
            }

            //using sort method to determine the highest and the lowest amount to determine the graph's boundaries
            sort(array);
            lowestStock = array[0];
            highestStock = array[array.length - 1];
            double spacing = (highestStock - lowestStock) * 0.8;

            //adding data to the chart
            lineChart.getData().clear();
            lineChart.getData().add(series);

            //style of the chart
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxHeight(45);
            lineChart.getXAxis().setMaxWidth(510);
            lineChart.getXAxis().setMinWidth(510);
            lineChart.getXAxis().setPrefWidth(510);

            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxHeight(166);
            lineChart.getYAxis().setMaxWidth(50);
            lineChart.getYAxis().setMinWidth(50);
            lineChart.getYAxis().setPrefWidth(50);

            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(1);
            xAxis.setUpperBound(numberOfPeriods);
            xAxis.setTickUnit(1);

            yAxis.setAutoRanging(false);
            if (lowestStock - spacing < 0) {
                yAxis.setLowerBound(0);
                yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                yAxis.setTickUnit(spacing);

            } else {
                yAxis.setLowerBound(lowestStock - spacing);
                yAxis.setUpperBound(highestStock + spacing);
                yAxis.setTickUnit(spacing);
            }
            yAxis.setMinorTickVisible(false);
        }

        //set style when mouse is on the area of the FiveButtonPeriod button
        FiveButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiveButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the FiveButtonPeriod button
        FiveButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiveButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the TenButtonPeriod button
        TenButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TenButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the TenButtonPeriod button
        TenButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TenButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the TwentyButtonPeriod button
        TwentyButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TwentyButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the TwentyButtonPeriod button
        TwentyButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TwentyButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the FiftyButtonPeriod button
        FiftyButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiftyButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the FiftyButtonPeriod button
        FiftyButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiftyButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the HundredButtonPeriod button
        HundredButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HundredButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the HundredButtonPeriod button
        HundredButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HundredButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the TwoHundredButtonPeriod button
        TwoHundredButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TwoHundredButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the TwoHundredButtonPeriod button
        TwoHundredButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TwoHundredButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the FiveHundredButtonPeriod button
        FiveHundredButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiveHundredButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the FiveHundredButtonPeriod button
        FiveHundredButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiveHundredButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the ThousandButtonPeriod button
        ThousandButtonPeriod.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ThousandButtonPeriod.setStyle("-fx-background-color: #d6d2d2; -fx-border-color: #9E9A9A; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is out of the area of the ThousandButtonPeriod button
        ThousandButtonPeriod.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ThousandButtonPeriod.setStyle("-fx-background-color: #ffffff; -fx-border-color: #aba7a7; -fx-border-width: 3 3 3 3");
            }
        });

        //set style when mouse is on the area of the CancelButton button
        CancelButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CancelButton.setStyle("-fx-background-color: #ff0000; -fx-border-color: #000000; -fx-border-radius: 3");
                CancelButton.setTextFill(Color.valueOf("#ffffff"));
            }
        });

        //set style when mouse is out of the area of the CancelButton button
        CancelButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CancelButton.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-radius: 3");
                CancelButton.setTextFill(Color.valueOf("#f56262"));
            }
        });

        //cancel button set on action when user wants to leave graph window
        CancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //close window
                    StocksViewController.indexOfStockGraphButton = -1;
                    numberOfPeriods = -1;
                    Main.closeGraph();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        FiveButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 5;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                        getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size()
                            - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                                getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV;
                         j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).
                                getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        TenButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 10;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        TwentyButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 20;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        FiftyButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 50;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        HundredButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 100;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        TwoHundredButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 200;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        FiveHundredButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 500;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });

        ThousandButtonPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfPeriods = 1000;
                int numberOfStockPrices = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size();
                if (numberOfStockPrices < numberOfPeriods) {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfStockPrices];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    int finalVariable;
                    if (Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods < 0) {
                        finalVariable = numberOfStockPrices - 1;
                    } else {
                        finalVariable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods;
                    }
                    for (int j = numberOfStockPrices, i = 0, variable = 0, fV = finalVariable; j > 0 || variable < fV; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        array[i] = yValue;
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfStockPrices);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                } else {
                    double lowestStock;
                    double highestStock;
                    double[] array = new double[numberOfPeriods];
                    XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();
                    for (int j = numberOfPeriods, i = 0, variable = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().size() - numberOfPeriods; j > 0; j--, i++, variable++) {
                        int xValue = i + 1;
                        double yValue = Firebase_Controller.companies.get(StocksViewController.indexOfStockGraphButton).getStockPrices().get(variable).getValue();
                        series.getData().add(new XYChart.Data<Integer, Double>(xValue, yValue));
                        array[i] = yValue;
                    }

                    sort(array);
                    lowestStock = array[0];
                    highestStock = array[array.length - 1];
                    double spacing = (highestStock - lowestStock) * 0.8;

                    lineChart.getData().clear();
                    lineChart.getData().add(series);

                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxHeight(45);
                    lineChart.getXAxis().setMaxWidth(510);
                    lineChart.getXAxis().setMinWidth(510);
                    lineChart.getXAxis().setPrefWidth(510);

                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxHeight(166);
                    lineChart.getYAxis().setMaxWidth(50);
                    lineChart.getYAxis().setMinWidth(50);
                    lineChart.getYAxis().setPrefWidth(50);

                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(1);
                    xAxis.setUpperBound(numberOfPeriods);
                    xAxis.setTickUnit(1);

                    yAxis.setAutoRanging(false);
                    if (lowestStock - spacing < 0) {
                        yAxis.setLowerBound(0);
                        yAxis.setUpperBound(highestStock + (highestStock * 0.1));
                        yAxis.setTickUnit(spacing);

                    } else {
                        yAxis.setLowerBound(lowestStock - spacing);
                        yAxis.setUpperBound(highestStock + spacing);
                        yAxis.setTickUnit(spacing);
                    }
                }
            }
        });
    }

    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    //method of sorting array by the values
    public static void sort(double[] array) {
        boolean sorted = false;
        double temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
}