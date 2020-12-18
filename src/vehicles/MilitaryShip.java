package vehicles;

import enumerates.typesOfArms;
import graphicalUserInterface.ControlPanel;
import javafx.util.Pair;
import interfaces.planeFactory;
import ports.Airport;

public class MilitaryShip extends Ship implements planeFactory{

    public MilitaryShip(int id, Pair<Integer, Integer> coordinates, int maximumAmountOfPassengers, int currentAmountOfPassengers, String firmName, int speed, typesOfArms typeOfArms) {
        super(id, coordinates, maximumAmountOfPassengers, currentAmountOfPassengers, firmName, speed);
        setTypeOfArms(typeOfArms);
    }

    public MilitaryShip() {
        super();
        setTypeOfArms(typesOfArms.NONE);
    }

    // Methods

    public void launchNewMilitaryAircraft(){}

    @Override
    public void createPlane(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport nextAirport, String travelRoute, typesOfArms typeOfArm) throws Exception {
        Aircraft newAircraft = new MilitaryAircraft(maximumAmountOfFuel, amountOfStaff, null, nextAirport, travelRoute, ControlPanel.addId(), this.getCoordinates(), typeOfArm);
    }
}
