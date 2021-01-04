package graphicalUserInterface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import other.TravelRoute;

public class Main extends Application {

    public static double ratio = 1900.0 / 974.0;
    private double mapMaxWidth = 1760.0;
    private double mapMaxHeight = mapMaxWidth / ratio;

    public static Stage mapStage = new Stage();
    public static Controller controller;

    static {
        try {
            controller = new Controller();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        setMapStage();
        scaleMapStage();

        TravelRoute travelWarsawNewYork = new TravelRoute();
        travelWarsawNewYork.addCheckpointToList(controller.getListOfAirports().get(0).getCoordinates());
        travelWarsawNewYork.addCheckpointToList(controller.getListOfAirports().get(1).getCoordinates());
        //controller.addTravelRouteToListOfTravelRoutes(travelWarsawNewYork);

        for (TravelRoute travelRoute: controller.getListOfTravelRoutes()){
            System.out.println(travelRoute.getCheckpoints().get(0).getValue().get());
        }
//
//        Aircraft aircraft = new PassengerAircraft(airport1.getCoordinates(), 200, new SimpleIntegerProperty(100), controller.addId(),
//                new SimpleIntegerProperty(100), new SimpleIntegerProperty(100), 20, airport1, airport3, null);
//        controller.addAircraftToListOfAircrafts(aircraft);

        MapPanelView mapPanelView = new MapPanelView(mapStage, controller);

        //ControlPanelView controlPanelView = new ControlPanelView(primaryStage, controller);
        runThreads();

        setCloseEvent(primaryStage);
    }

    private void setMapStage() {
        mapStage.setWidth(mapMaxWidth * 0.9);
        mapStage.setHeight(mapMaxHeight * 0.9);

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

    public void runThreads(){
        for (Thread thread : controller.getListOfThreads()) {
            thread.start();
        }
    }

    private void setCloseEvent(Stage primaryStage){
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        mapStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}
