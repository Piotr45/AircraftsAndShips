package graphicalUserInterface;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;

public class Main extends Application {

    public static double ratio = 1900.0 / 974.0;
    private double mapMaxWidth = 1760.0;
    private double mapMaxHeight = mapMaxWidth / ratio;

    public static Stage mapStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        setMapStage();
        scaleMapStage();

        Controller controller = new Controller();

        controller.getListOfIds().add(0);

//        TravelRoute travelWarsawNewYork = new TravelRoute();
//        travelWarsawNewYork.addCheckpointToList(airport1.getCoordinates());
//        travelWarsawNewYork.addCheckpointToList(airport3.getCoordinates());
//        controller.addTravelRouteToListOfTravelRoutes(travelWarsawNewYork);
//
//        Aircraft aircraft = new PassengerAircraft(airport1.getCoordinates(), 200, new SimpleIntegerProperty(100), controller.addId(),
//                new SimpleIntegerProperty(100), new SimpleIntegerProperty(100), 20, airport1, airport3, null);
//        controller.addAircraftToListOfAircrafts(aircraft);

//        TravelRoute seeRouteExample = new TravelRoute();
//        seeRouteExample.addCheckpointToList(new Pair<>(new SimpleIntegerProperty(100), new SimpleIntegerProperty(100)));
//        seeRouteExample.addCheckpointToList(new Pair<>(new SimpleIntegerProperty(200), new SimpleIntegerProperty(100)));
//        seeRouteExample.addCheckpointToList(new Pair<>(new SimpleIntegerProperty(200), new SimpleIntegerProperty(200)));
//        seeRouteExample.addCheckpointToList(new Pair<>(new SimpleIntegerProperty(100), new SimpleIntegerProperty(200)));
//        controller.addTravelRouteToListOfTravelRoutes(seeRouteExample);

        MapPanelView mapPanelView = new MapPanelView(mapStage, controller);

        //ControlPanelView controlPanelView = new ControlPanelView(primaryStage, controller);
    }

    private void setMapStage() {
        mapStage.setWidth(mapMaxWidth * 0.9);
        System.out.println(mapStage.getWidth());
        mapStage.setHeight(mapMaxHeight* 0.9);
        System.out.println(mapStage.getHeight());
        mapStage.setMaxWidth(mapMaxWidth);
        mapStage.setMaxHeight(mapMaxHeight);
        mapStage.setMinWidth(mapMaxWidth * 0.9 * 0.6);
        mapStage.setMinHeight(mapMaxHeight * 0.9 * 0.6);
    }

    private void scaleMapStage() {
        // TODO napraw skalowanie bokiem
        mapStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            mapStage.setHeight(newVal.doubleValue() / ratio);
        });
        mapStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            mapStage.setWidth(newVal.doubleValue() * ratio);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
