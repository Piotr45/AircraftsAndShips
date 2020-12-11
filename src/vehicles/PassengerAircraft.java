package vehicles;

import javafx.util.Pair;
import ports.Airport;
import java.util.List;

public class PassengerAircraft extends Aircraft {

    public PassengerAircraft(float maximumAmountOfFuel, float currentAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, List<Airport> listOfVisitedAirports, Airport nextAirport, String travelRoute, int id, Pair<Integer, Integer> coordinates) {
        super(maximumAmountOfFuel, currentAmountOfFuel, maximumAmountOfPassengers, currentAmountOfPassengers, amountOfStaff, listOfVisitedAirports, nextAirport, travelRoute, id, coordinates);
    }

    public PassengerAircraft() {
        super();
    }

    @Override
    public void run() {

    }
}
