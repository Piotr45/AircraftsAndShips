package vehicles;

import enumerates.FirmNames;
import enumerates.typesOfArms;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

public abstract class Ship extends Vehicle {

    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");

    private int speed;

    public Ship(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, int speed) {
        super(coordinates, id);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
        this.speed = speed;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void shuttle() {
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers + "\n" +
                "Velocity of ship: " + speed;
    }

    @Override
    public String toString() {
        return "Ship " + this.getId();
    }
}
