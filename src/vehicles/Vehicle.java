package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.Node;

public abstract class Vehicle extends Node {

    private int id;

    public Vehicle(Pair<IntegerProperty, IntegerProperty> coordinates, int id) {
        super(coordinates);
        this.id = id;
    }

    public Vehicle(String name) {
        super(name);
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
