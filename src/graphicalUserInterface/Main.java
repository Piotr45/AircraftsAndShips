package graphicalUserInterface;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Control;
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

    Stage mapStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        ControlPanel controlPanel = new ControlPanel();

        controlPanel.getListOfIds().add(0);

        Pair<IntegerProperty, IntegerProperty> pair = new Pair<>( new SimpleIntegerProperty(100),new SimpleIntegerProperty(100));
        Airport airport1 = new CivilianAirport("Tokio", pair);
        controlPanel.addAirportToListOfAirports(airport1);

        Aircraft aircraft = new PassengerAircraft();
        controlPanel.addAircraftToListOfAircrafts(aircraft);


        //MapPanelView mapPanelView = new MapPanelView(mapStage);
        ControlPanelView controlPanelView = new ControlPanelView(primaryStage, controlPanel);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
