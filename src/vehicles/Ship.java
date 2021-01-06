package vehicles;

import enumerates.FirmNames;
import enumerates.typesOfArms;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;

public abstract class Ship extends Vehicle {

    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");


    public Ship(Pair<DoubleProperty, DoubleProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, int speed, TravelRoute travelRoute) {
        super(coordinates, id, speed, travelRoute);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
    }

    public Ship() {
    }

    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers.get();
    }

    public IntegerProperty currentAmountOfPassengersProperty() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers.set(currentAmountOfPassengers);
    }

    public void shuttle() {
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers + "\n" +
                "Velocity of ship: " + getVelocity();
    }

    @Override
    public String toString() {
        return "Ship " + this.getId();
    }
}
