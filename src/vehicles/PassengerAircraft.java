package vehicles;

import javafx.util.Pair;
import ports.Airport;
import java.util.List;

public class PassengerAircraft extends Aircraft {

    public PassengerAircraft(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, String travelRoute, int id, Pair<Integer, Integer> coordinates) {
        super(maximumAmountOfFuel, maximumAmountOfPassengers, currentAmountOfPassengers, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute, id, coordinates);
    }

    public PassengerAircraft() {
        super();
    }

    @Override
    public void run() {

    }
}
