package vehicles;

import javafx.util.Pair;

public class PassengerShip extends Ship {

    public PassengerShip(int id, Pair<Integer, Integer> coordinates, int maximumAmountOfPassengers, int currentAmountOfPassengers, String firmName, int speed) {
        super(id, coordinates, maximumAmountOfPassengers, currentAmountOfPassengers, firmName, speed);
    }

    public PassengerShip() {
        super();
    }
}
