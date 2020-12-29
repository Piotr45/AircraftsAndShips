package other;

import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;

public class Entity {

    private Pair<IntegerProperty, IntegerProperty> coordinates;
    private String name;

    public Entity(Pair<IntegerProperty, IntegerProperty> coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public Entity(String name) {
        this.name = name;
    }

    public Entity(Pair<IntegerProperty, IntegerProperty> coordinates) {
        this.coordinates = coordinates;
    }

    public Entity() {
    }

    public Pair<IntegerProperty, IntegerProperty> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<IntegerProperty, IntegerProperty> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
