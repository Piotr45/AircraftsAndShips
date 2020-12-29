package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;

public class PassengerShip extends Ship {

    public PassengerShip(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers1, String firmName, int speed) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers1, firmName, speed);
    }

    public PassengerShip() {
        super();
    }

    @Override
    public String toString() {
        return this.getFirmName() + " Ship " + this.getId();
    }
}
