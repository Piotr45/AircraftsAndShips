package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.util.Pair;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import graphicalUserInterface.ControlPanel;

public class MilitaryAirport extends Airport implements planeFactory {

    public MilitaryAirport(String name, Pair<Integer, Integer> coordinates) {
        super(name, coordinates);
    }

    @Override
    public void createPlane(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport nextAirport, String travelRoute, typesOfArms typeOfArm) throws Exception {
        Aircraft newAircraft = new MilitaryAircraft(maximumAmountOfFuel, amountOfStaff, this, nextAirport, travelRoute, ControlPanel.addId(), this.getCoordinates(), typeOfArm);
    }
}
