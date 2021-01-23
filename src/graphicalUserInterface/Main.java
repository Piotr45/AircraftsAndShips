package graphicalUserInterface;

import com.sun.javafx.tk.Toolkit;
import com.sun.scenario.animation.AbstractMasterTimer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import other.TravelRoute;
import vehicles.Ship;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    public static double ratio = 1900.0 / 974.0;
    public static double mapMaxWidth = 1760.0;
    public static double mapMaxHeight = mapMaxWidth / ratio;
    public static double initMapWidth = (int) mapMaxWidth * 0.9;
    public static double initMapHeight = (int) initMapWidth / ratio;

    public static Stage mapStage = new Stage();
    public static Controller controller;

    static {
        try {
            controller = new Controller();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static MapPanelView mapPanelView = new MapPanelView(mapStage, controller);
    public static ControlPanelView controlPanelView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mapStage.setResizable(false);
        setMapStage();
        scaleMapStage();


        mapPanelView.showWindow();
        controlPanelView = new ControlPanelView(primaryStage, controller);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mapPanelView.resetUI();
            }
        };
        animationTimer.start();

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

    private void setCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                for (Ship ship : controller.getListOfShips()) {
                    ship.getThread().stop();
                }
                Platform.exit();
                System.exit(0);
            }
        });
        mapStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                for (Ship ship : controller.getListOfShips()) {
                    ship.getThread().stop();
                }
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}
