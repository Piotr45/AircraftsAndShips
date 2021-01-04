package other;

import graphicalUserInterface.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

public class Node {

    private Pair<IntegerProperty, IntegerProperty> coordinates = new Pair<>(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    private String name;
    private Pair<Integer, Integer> primeCoordinates = new Pair<>(0, 0);

    public Node(Pair<IntegerProperty, IntegerProperty> coordinates, String name) {
        this.primeCoordinates = new Pair<>(coordinates.getKey().get(), coordinates.getValue().get());
        setX(primeCoordinates.getKey() * Main.mapStage.getWidth() / Main.mapStage.getMaxWidth());
        setY(primeCoordinates.getValue() * Main.mapStage.getHeight() / Main.mapStage.getMaxHeight());
        this.name = name;
        attachListener();
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(Pair<IntegerProperty, IntegerProperty> coordinates) {
        this.coordinates = coordinates;
    }

    public Node() {
    }

    private void attachListener() {
        Main.mapStage.widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            setX(primeCoordinates.getKey() * newValue.doubleValue() / Main.mapStage.getMaxWidth());
        }));
        Main.mapStage.heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            setY(primeCoordinates.getValue() * newValue.doubleValue() / Main.mapStage.getMaxHeight());
        }));
    }

    public Pair<IntegerProperty, IntegerProperty> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<IntegerProperty, IntegerProperty> coordinates) {
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
        return name;
    }
}
