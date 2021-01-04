package graphicalUserInterface;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import enumerates.MyColors;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;

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
    private Group root = new Group();
    private Scene scene;
    private Image image;
    private ImageView imageView;

    private Controller controller;

    public MapPanelView(Stage mapWindow, Controller controller) {
        this.mapWindow = mapWindow;
        this.controller = controller;
        mapWindow.setTitle("Map");
        buildUI();
    }

    private void setMapWindow() {
        pane.setMaxSize(maxWidth, maxHeight);
        pane.setMinSize(minWidth, minHeight);
    }

    private void setImage() {
        image = new Image("newMap.png");
    }

    private void buildUI() {
        setMapWindow();
        setImage();
        try {
            pane.getChildren().add(root);

            setImageView();

            drawAirports();
            scaleImageView();
            drawTravelRoutes();

            scene = new Scene(pane, initWidth, initHeight);
            mapWindow.setScene(scene);
            mapWindow.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setImageView() {
        imageView = new ImageView(image);
        imageView.setFitWidth(initWidth);
        imageView.setFitHeight(initHeight);

        root.getChildren().add(0, imageView);
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

    private String chooseColor(Airport airport) {
        if (airport instanceof CivilianAirport) {
            return String.valueOf(MyColors.darkBlueColor.hexCode);
        } else {
            return String.valueOf(MyColors.rubyRed.hexCode);
        }
    }

    private void drawAirport(Airport airport) {
        Pair<IntegerProperty, IntegerProperty> coordinates = airport.getCoordinates();

        Circle circle = new Circle();
        circle.centerXProperty().bind(coordinates.getKey());
        circle.centerYProperty().bind(coordinates.getValue());

        circle.setRadius(5);
        circle.setFill(Paint.valueOf(chooseColor(airport)));

        assignTooltipToAirport(airport, circle);

        root.getChildren().add(1, circle);

    }

    private void assignTooltipToAirport(Airport airport, Circle circle) {
        var ref = new Object() {
            Tooltip tooltip = new Tooltip(airport.getInfo());
        };

        mapWindow.widthProperty().addListener((obs, oldVal, newVal) -> {
            Tooltip.uninstall(circle, ref.tooltip);
            ref.tooltip = new Tooltip(airport.getInfo());
            Tooltip.install(circle, ref.tooltip);
        });
        mapWindow.heightProperty().addListener((obs, oldVal, newVal) -> {
            Tooltip.uninstall(circle, ref.tooltip);
            ref.tooltip = new Tooltip(airport.getInfo());
            Tooltip.install(circle, ref.tooltip);
        });
        Tooltip.install(circle, ref.tooltip);
    }

    private void drawPlane() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 10.0, 20.0);
        root.getChildren().add(triangle);
    }

    private void drawTravelRoutes() {
        for (TravelRoute travelRoute : controller.getListOfTravelRoutes()) {
            drawTravelRoute(travelRoute);
        }
    }

    private void drawTravelRoute(TravelRoute travelRoute) {
        List<Pair<IntegerProperty, IntegerProperty>> routeCheckpoints = travelRoute.getCheckpoints();
        for (int index = 0; index < routeCheckpoints.size(); index++) {
            Line line = new Line();
            line.startXProperty().bind(routeCheckpoints.get(index).getKey());
            line.startYProperty().bind(routeCheckpoints.get(index).getValue());
            if (index == routeCheckpoints.size() - 1) {
                line.endXProperty().bind(routeCheckpoints.get(0).getKey());
                line.endYProperty().bind(routeCheckpoints.get(0).getValue());
            } else {
                line.endXProperty().bind(routeCheckpoints.get(index + 1).getKey());
                line.endYProperty().bind(routeCheckpoints.get(index + 1).getValue());
            }
            root.getChildren().add(2, line);
        }
    }

}