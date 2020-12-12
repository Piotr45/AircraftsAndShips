package graphicalUserInterface;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import ports.Airport;
import ports.CivilianAirport;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ControlPanel controlPanel = new ControlPanel();

        Aircraft aircraft = new PassengerAircraft();
        controlPanel.addAircraftToListOfAircrafts(aircraft);

//        Airport airport = new CivilianAirport();
//        controlPanel.addAirportToListOfAirports(airport);

        ControlPanelView controlPanelView = new ControlPanelView(primaryStage, controlPanel);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
