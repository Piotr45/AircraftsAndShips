package other;

import graphicalUserInterface.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Pair;
import vehicles.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Node represents node.
 */
public class Node {

    private Pair<Double, Double> coordinates = new Pair<>(0.0, 0.0);
    private String name;
    private Pair<Double, Double> primeCoordinates = new Pair<>(0.0, 0.0);

    /**
     * Node class constructor with two params.
     * @param coordinates - original coordinates.
     * @param name - name of node.
     */
    public Node(Pair<Double, Double> coordinates, String name) {
        init(coordinates);
        this.name = name;
    }

    /**
     * Node class constructor with single param.
     * @param name - name of node.
     */
    public Node(String name) {
        this.name = name;
    }

    /**
     * Node class constructor with single param.
     * @param coordinates - original coordinates of node.
     */
    public Node(Pair<Double, Double> coordinates) {
        init(coordinates);
    }

    /**
     * Node class constructor with two params.
     * @param coordinates - original coordinates of node.
     * @param switcher - responsible for creating ship object in vehicle class.
     */
    public Node(Pair<Double, Double> coordinates, int switcher) {
        this.primeCoordinates = new Pair<>(coordinates.getKey(), coordinates.getValue());
        this.coordinates = new Pair<>(coordinates.getKey(), coordinates.getValue());
    }

    /**
     * Empty constructor.
     */
    public Node() {
    }

    private void init(Pair<Double, Double> coordinates){
        this.primeCoordinates = new Pair<>(coordinates.getKey(), coordinates.getValue());
        this.coordinates = new Pair<>((double)((int) (primeCoordinates.getKey() * Main.initMapWidth/ Main.mapMaxWidth)),
                (double)((int) (primeCoordinates.getValue() * Main.initMapHeight / Main.mapMaxHeight)));
    }

    /**
     * Gets pair of coordinates.
     * @return - returns coordinates of an object.
     */
    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates of an object.
     * @param coordinates - Pair of two doubles passed to set coordinates of an object.
     */
    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Sets name of an node.
     * @return - returns name of node.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of an node.
     * @param name - name of an object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rounds value of passed param.
     * @param value - value that we will round.
     * @param places - number of digits after ".".
     * @return - returns rounded number.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Method used to pass information of an node to one string.
     * @return - returns string that contains all params of node.
     */
    public String getInfo() {
        return "Name: " + this.name + "\n" +
                "Coordinates: (" + round(this.coordinates.getKey(), 2) + ", " + round(this.coordinates.getValue(),2) + ")";
    }

    /**
     * Changes object name to name or coordinates.
     * @return - returns coordinates as string or name.
     */
    @Override
    public String toString() {
        if (name == null){
            return "(" + coordinates.getKey() + ", " + coordinates.getValue() + ")";
        }
        else {
            return name;
        }
    }
}
