package interfaces;

import ports.Airport;
import enumerates.typesOfArms;

public interface planeFactory {

    void createPlane(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport nextAirport, String travelRoute, typesOfArms typeOfArm) throws Exception;
}
