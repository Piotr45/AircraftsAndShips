package vehicles;

import enumerates.typesOfArms;
import ports.Airport;
import javafx.util.Pair;
import java.util.List;

public class MilitaryAircraft extends Aircraft{

    public MilitaryAircraft(float maximumAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, String travelRoute, int id, Pair<Integer, Integer> coordinates, typesOfArms typeOfArms) {
        setMaximumAmountOfFuel(maximumAmountOfFuel);
        setCurrentAmountOfFuel(maximumAmountOfFuel);
        setLastVisitedAirport(lastVisitedAirport);
        setAmountOfStaff(amountOfStaff);
        setNextAirport(nextAirport);
        setTravelRoute(travelRoute);
        setId(id);
        setCoordinates(coordinates);
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
