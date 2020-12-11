package vehicles;

import enumerates.typesOfArms;
import ports.Airport;
import javafx.util.Pair;
import java.util.List;

public class MilitaryAircraft extends Aircraft{

    public MilitaryAircraft(float maximumAmountOfFuel, float currentAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, List<Airport> listOfVisitedAirports, Airport nextAirport, String travelRoute, int id, Pair<Integer, Integer> coordinates, typesOfArms typeOfArms) {
        super(maximumAmountOfFuel, currentAmountOfFuel, maximumAmountOfPassengers, currentAmountOfPassengers, amountOfStaff, listOfVisitedAirports, nextAirport, travelRoute, id, coordinates);
        setTypeOfArms(typeOfArms);
    }

    public MilitaryAircraft() {
        super();
        setTypeOfArms(typesOfArms.NONE);
    }

    @Override
    public void run() {

    }
}
