package graphicalUserInterface;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import ports.Airport;
import ports.CivilianAirport;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;
import enumerates.typesOfArms;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ControlPanel controlPanel = new ControlPanel();

        Pair<Integer, Integer> pair = new Pair<>(1,1);
        Airport airport1 = new CivilianAirport("Tokio", pair);
        controlPanel.addAirportToListOfAirports(airport1);

        Aircraft aircraft = new PassengerAircraft();
        controlPanel.addAircraftToListOfAircrafts(aircraft);

        System.out.println(typesOfArms.blockbusterBomb.ordinal());
//        Airport airport = new CivilianAirport();
//        controlPanel.addAirportToListOfAirports(airport);

        ControlPanelView controlPanelView = new ControlPanelView(primaryStage, controlPanel);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
