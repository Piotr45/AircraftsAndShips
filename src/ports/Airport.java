package ports;

import vehicles.Aircraft;
import javafx.util.Pair;

public abstract class Airport {

    // Variables

    private String name;
    private Pair<Integer, Integer> coordinates;
    private Aircraft currentServicedAircraft;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Aircraft getCurrentServicedAircraft() {
        return currentServicedAircraft;
    }

    public void setCurrentServicedAircraft(Aircraft currentServicedAircraft) {
        this.currentServicedAircraft = currentServicedAircraft;
    }

    // Methods

    public void serviceAircraft(){}

    @Override
    public String toString() {
        return name + " Airport";
    }
}
