package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.util.Pair;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;
import graphicalUserInterface.ControlPanel;

public class CivilianAirport extends Airport implements planeFactory {

    public CivilianAirport(String name, Pair<Integer, Integer> coordinates) {
        super(name, coordinates);
    }

    @Override
    public void createPlane(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport nextAirport, String travelRoute, typesOfArms typeOfArm) throws Exception {
        Aircraft newAircraft = new PassengerAircraft(maximumAmountOfFuel, maximumAmountOfPassengers, currentAmountOfPassengers, amountOfStaff, this, nextAirport, travelRoute, ControlPanel.addId(), this.getCoordinates());
    }

}
