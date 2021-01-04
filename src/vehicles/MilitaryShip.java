package vehicles;

import enumerates.typesOfArms;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;

public class MilitaryShip extends Ship {//implements planeFactory{

    private typesOfArms typeOfArms;

    public MilitaryShip(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, String firmName, int speed, typesOfArms typeOfArms) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers, speed);
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


    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Type of arm: " + typeOfArms.name();
    }

    @Override
    public String toString() {
        return "Military Ship " + this.getId();
    }
}
