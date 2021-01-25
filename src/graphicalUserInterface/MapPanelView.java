package graphicalUserInterface;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import enumerates.MyColors;
import javafx.util.Pair;
import other.Node;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.*;

import java.util.List;

public class MapPanelView {

    final double ratio = 1900.0 / 974.0;

    private final double maxWidth = 1900 * 0.9;
    private final double maxHeight = maxWidth / ratio;

    private final double minWidth = maxWidth * 0.6;
    private final double minHeight = minWidth / ratio;

    private final double initWidth = maxWidth * 0.9;
    private final double initHeight = initWidth / ratio;


    public static StackPane pane = new StackPane();

    private final Stage mapWindow;
    public static Group root = new Group();
    private Scene scene;
    private Image image;
    private ImageView imageView;

    private Controller controller;

    public MapPanelView(Stage mapWindow, Controller controller) {
        this.mapWindow = mapWindow;
        this.controller = controller;
        mapWindow.setTitle("Map");
        setMapWindow();
        setImage();
        setImageView();
        pane.getChildren().add(root);
        scene = new Scene(pane, initWidth, initHeight);
        scaleImageView();
        mapWindow.setScene(scene);
        buildUI();
    }

    private void setMapWindow() {
        pane.setMaxSize(maxWidth, maxHeight);
        pane.setMinSize(minWidth, minHeight);
    }

    private void setImage() {
        image = new Image("newMap.png");
    }

    public void resetUI() {
        root.getChildren().clear();
        buildUI();
    }

    private void buildUI() {
        try {
            drawTravelRoutes();
            drawAirports();
            drawShips();
            drawAircrafts();

            root.getChildren().add(0, imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showWindow() {
        mapWindow.show();
    }

    private void setImageView() {
        imageView = new ImageView(image);
        imageView.setFitWidth(initWidth);
        imageView.setFitHeight(initHeight);
    }

    private void scaleImageView() {
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
    }

    private void drawAirports() {
        for (Airport airport : controller.getListOfAirports()) {
            drawAirport(airport);
        }
    }

    private <T extends Node> String chooseColor(T entity) {
        if (entity instanceof CivilianAirport) {
            return String.valueOf(MyColors.darkBlueColor.hexCode);
        } else if (entity instanceof MilitaryAirport) {
            return String.valueOf(MyColors.rubyRed.hexCode);
        } else if (entity instanceof PassengerAircraft) {
            return String.valueOf(MyColors.rajah.hexCode);
        } else if (entity instanceof PassengerShip) {
            return String.valueOf(MyColors.bluePantone.hexCode);
        }
        return "#fffff";
    }

    private void drawAirport(Airport airport) {
        Pair<Double, Double> coordinates = airport.getCoordinates();

        Circle circle = new Circle();
        circle.setCenterX(coordinates.getKey());
        circle.setCenterY(coordinates.getValue());

        circle.setRadius(5);
        circle.setFill(Paint.valueOf(chooseColor(airport)));

        assignTooltipToNode(airport, circle);

        root.getChildren().add(circle);

    }

    private <T extends Node> void assignTooltipToNode(T node, Circle circle) {
        var ref = new Object() {
            Tooltip tooltip = new Tooltip(node.getInfo());
        };

        mapWindow.widthProperty().addListener((obs, oldVal, newVal) -> {
            Tooltip.uninstall(circle, ref.tooltip);
            ref.tooltip = new Tooltip(node.getInfo());
            Tooltip.install(circle, ref.tooltip);
        });
        mapWindow.heightProperty().addListener((obs, oldVal, newVal) -> {
            Tooltip.uninstall(circle, ref.tooltip);
            ref.tooltip = new Tooltip(node.getInfo());
            Tooltip.install(circle, ref.tooltip);
        });
        Tooltip.install(circle, ref.tooltip);
    }

    private void drawAircraft(Aircraft aircraft) {
        Pair<Double, Double> coordinates = aircraft.getCoordinates();
        Circle circle = new Circle();
        circle.setCenterX(coordinates.getKey());
        circle.setCenterY(coordinates.getValue());
        circle.setRadius(3);
        circle.setFill(Paint.valueOf(chooseColor(( ((PassengerAircraft) aircraft) ))));
        root.getChildren().add(circle);
    }

    private void drawAircrafts() {
        for (Aircraft aircraft : controller.getListOfAircrafts()) {
            drawAircraft(aircraft);
        }
    }

    public void drawShip(Ship ship) {
        Pair<Double, Double> coordinates = ship.getCoordinates();
        Circle circle = new Circle();
        circle.setCenterX(coordinates.getKey());
        circle.setCenterY(coordinates.getValue());
        circle.setRadius(3);
        circle.setFill(Paint.valueOf(chooseColor(ship)));
        root.getChildren().add(circle);
    }

    private void drawShips() {
        for (Ship ship : controller.getListOfShips()) {
            drawShip(ship);
        }
    }

    private boolean isItSeaRoute(TravelRoute travelRoute) {
        if (controller.getListOfTravelRoutes().indexOf(travelRoute) == 1 ||
                controller.getListOfTravelRoutes().indexOf(travelRoute) == 9 ||
                controller.getListOfTravelRoutes().indexOf(travelRoute) == 10) {
            return true;
        }
        return false;
    }

    private boolean isItMilitaryRoute(TravelRoute travelRoute) {
        if (controller.getListOfTravelRoutes().indexOf(travelRoute) == 7 ||
                controller.getListOfTravelRoutes().indexOf(travelRoute) == 8) {
            return true;
        }
        return false;
    }

    private void drawTravelRoutes() {
        for (TravelRoute travelRoute : controller.getListOfTravelRoutes()) {
            if (isItSeaRoute(travelRoute)) {
                drawTravelRoute(travelRoute, MyColors.lightBlue.hexCode);
            } else if (isItMilitaryRoute(travelRoute)) {
                drawTravelRoute(travelRoute, MyColors.lightRed.hexCode);
            } else {
                drawTravelRoute(travelRoute, MyColors.lightGrey.hexCode);
            }
        }
    }

    private void drawTravelRoute(TravelRoute travelRoute, String color) {
        List<Node> routeCheckpoints = travelRoute.getCheckpoints();
        for (int index = 0; index < routeCheckpoints.size(); index++) {
            Line line = new Line();
            line.setStroke(Paint.valueOf(color));

            line.setStartX(routeCheckpoints.get(index).getCoordinates().getKey());
            line.setStartY(routeCheckpoints.get(index).getCoordinates().getValue());

            if (index == routeCheckpoints.size() - 1) {
                line.setEndX(routeCheckpoints.get(0).getCoordinates().getKey());
                line.setEndY(routeCheckpoints.get(0).getCoordinates().getValue());
            } else {
                line.setEndX(routeCheckpoints.get(index + 1).getCoordinates().getKey());
                line.setEndY(routeCheckpoints.get(index + 1).getCoordinates().getValue());
            }

            Circle circle = new Circle();
            circle.setCenterX(routeCheckpoints.get(index).getCoordinates().getKey());
            circle.setCenterY(routeCheckpoints.get(index).getCoordinates().getValue());
            circle.setRadius(2);
            circle.setFill(Paint.valueOf(color));

            assignTooltipToNode(routeCheckpoints.get(index), circle);

            root.getChildren().add(line);
            root.getChildren().add(circle);
        }
    }

}