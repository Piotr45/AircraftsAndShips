package graphicalUserInterface;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import enumerates.MyColors;

public class MapPanelView {

    private final double minWidth = 960.0;
    private final double minHeight = 540.0;
    private final double maxWidth = 1920.0 * 0.8;
    private final double maxHeight = 1080.0 * 0.8;
    private final double initWidth = minWidth*1.2;
    private final double initHeight = minHeight*1.2;
    final double ratio = maxWidth/maxHeight;

    final StackPane pane = new StackPane();

    private final Stage mapWindow;
    private Group root = new Group();
    private Scene scene;
    private Image image;
    private  ImageView imageView;

    public MapPanelView(Stage mapWindow) {
        this.mapWindow = mapWindow;
        mapWindow.setTitle("Map");
        buildUI();
    }

    private void setMapWindow(){
        mapWindow.setMinWidth(minWidth);
        mapWindow.setMinHeight(minHeight);
        mapWindow.setMaxWidth(maxWidth);
        mapWindow.setMaxHeight(maxHeight);
        pane.setMaxSize(maxWidth, maxHeight);
        pane.setMinSize(minWidth, minHeight);
    }

    private void setImage(){
        image = new Image("map2.png");
    }

    private void buildUI() {
        setMapWindow();
        setImage();
        try{
//            pane.setStyle("-fx-border-color: " + String.valueOf(MyColors.keyLime.hexCode));
            pane.getChildren().add(root);

            imageView = new ImageView(image);
            root.getChildren().add(imageView);

            drawCircle();
            scale();

            scene = new Scene(pane, initWidth, initHeight);
            mapWindow.setScene(scene);
            mapWindow.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void scale(){
        mapWindow.widthProperty().addListener((obs, oldVal, newVal) -> {
            ObservableValue<Double> observableValue = new SimpleDoubleProperty(mapWindow.getWidth() / ratio).asObject();
//            System.out.println("Pane width: " + pane.getWidth() + "\t Window width: " + mapWindow.getWidth());
//            System.out.println("Pane height: " + pane.getHeight() + "\t Window height: " + mapWindow.getHeight());
            imageView.fitWidthProperty().bind(mapWindow.widthProperty());
            imageView.fitHeightProperty().bind(observableValue);

            mapWindow.setHeight(mapWindow.getWidth() / ratio);
        });
        mapWindow.heightProperty().addListener((obs, oldVal, newVal) -> {
            ObservableValue<Double> observableValue = new SimpleDoubleProperty(mapWindow.getHeight() * ratio).asObject();
            imageView.fitWidthProperty().bind(observableValue);
            imageView.fitHeightProperty().bind(mapWindow.heightProperty());

            mapWindow.setHeight(mapWindow.getWidth() / ratio);
        });
    }

    private void drawCircle(){
        double wspX = 1000.0;
        double wspY = 500.0;

        Circle circle = new Circle();
        circle.setCenterX(wspX);
        circle.setCenterY(wspY);

        mapWindow.widthProperty().addListener((obs, oldVal, newVal) -> {
            ObservableValue<Double> observableValue = new SimpleDoubleProperty(mapWindow.getWidth() / maxWidth * wspX).asObject();
            circle.centerXProperty().bind(observableValue);
        });
        mapWindow.heightProperty().addListener((obs, oldVal, newVal) -> {
            ObservableValue<Double> observableValue = new SimpleDoubleProperty(mapWindow.getHeight() / maxHeight * wspY).asObject();
            circle.centerYProperty().bind(observableValue);
        });

        circle.setRadius(5);
        circle.setFill(Paint.valueOf(String.valueOf(MyColors.asparagus.hexCode)));

        root.getChildren().add(circle);
    }
}
