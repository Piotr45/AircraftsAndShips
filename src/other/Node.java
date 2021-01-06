package other;

import graphicalUserInterface.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Pair;

public class Node {

    private Pair<DoubleProperty, DoubleProperty> coordinates = new Pair<>(new SimpleDoubleProperty(0), new SimpleDoubleProperty(0));
    private String name;
    private Pair<Double, Double> primeCoordinates = new Pair<>(0.0, 0.0);

    public Node(Pair<DoubleProperty, DoubleProperty> coordinates, String name) {
        init(coordinates);
        this.name = name;
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(Pair<DoubleProperty, DoubleProperty> coordinates) {
        init(coordinates);
    }

    public Node() {
    }

    private void init(Pair<DoubleProperty, DoubleProperty> coordinates){
        this.primeCoordinates = new Pair<>(coordinates.getKey().get(), coordinates.getValue().get());
        setX(primeCoordinates.getKey() * Main.mapStage.getWidth() / Main.mapStage.getMaxWidth());
        setY(primeCoordinates.getValue() * Main.mapStage.getHeight() / Main.mapStage.getMaxHeight());
        attachListener();
    }

    private void attachListener() {
        Main.mapStage.widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            setX(primeCoordinates.getKey() * newValue.doubleValue() / Main.mapStage.getMaxWidth());
        }));
        Main.mapStage.heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            setY(primeCoordinates.getValue() * newValue.doubleValue() / Main.mapStage.getMaxHeight());
        }));
    }

    public Pair<DoubleProperty, DoubleProperty> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<DoubleProperty, DoubleProperty> coordinates) {
        this.coordinates = coordinates;
    }

    public void setX(double value) {
        coordinates.getKey().set((int) value);
    }

    public void setY(double value) {
        coordinates.getValue().set((int) value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getInfo() {
        return "Name: " + name + "\n" +
                "Coordinates: (" + coordinates.getKey().get() + ", " + coordinates.getValue().get() + ")";

    }

    @Override
    public String toString() {
        if (name == null){
            return "(" + coordinates.getKey().get() + ", " + coordinates.getValue().get() + ")";
        }
        else {
            return name;
        }
    }
}
