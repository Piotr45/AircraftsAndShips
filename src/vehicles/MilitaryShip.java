package vehicles;

import enumerates.typesOfArms;
import graphicalUserInterface.ControlPanel;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import interfaces.planeFactory;
import other.TravelRoute;
import ports.Airport;

public class MilitaryShip extends Ship {//implements planeFactory{

    private typesOfArms typeOfArms;

    public MilitaryShip(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers1, IntegerProperty currentAmountOfPassengers1, String firmName, int speed, typesOfArms typeOfArms) {
        super(coordinates, id, maximumAmountOfPassengers1, currentAmountOfPassengers1, firmName, speed);
        this.typeOfArms = typeOfArms;
    }

    public MilitaryShip() {
    }

    // Methods

    public void launchNewMilitaryAircraft(){}

//    @Override
//    public Aircraft createPlane(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArm) throws Exception {
//        return new MilitaryAircraft(maximumAmountOfFuel, amountOfStaff, null, nextAirport, travelRoute, ControlPanel.addId(), this.getCoordinates(), typeOfArm);
//    }


    @Override
    public String toString() {
        return "Military Ship " + this.getId();
    }
}
