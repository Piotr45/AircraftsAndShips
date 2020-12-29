package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.Entity;

public abstract class Vehicle extends Entity {

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
