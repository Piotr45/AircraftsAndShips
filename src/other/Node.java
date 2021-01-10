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

    public Node(Pair<DoubleProperty, DoubleProperty> coordinates, int switcher) {
        this.primeCoordinates = new Pair<>(coordinates.getKey().get(), coordinates.getValue().get());
        this.coordinates = new Pair<>(new SimpleDoubleProperty(coordinates.getKey().get()), new SimpleDoubleProperty(coordinates.getValue().get()));
        //attachListener();
    }

    public Node() {
    }

    private void init(Pair<DoubleProperty, DoubleProperty> coordinates){
        this.primeCoordinates = new Pair<>(coordinates.getKey().get(), coordinates.getValue().get());
        setX(primeCoordinates.getKey() * Main.initMapWidth/ Main.mapMaxWidth);
        setY(primeCoordinates.getValue() * Main.initMapHeight / Main.mapMaxHeight);
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public String getInfo() {
        return "Name: " + this.name + "\n" +
                "Coordinates: (" + round(this.coordinates.getKey().get(), 2) + ", " + round(this.coordinates.getValue().get(),2) + ")";

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
