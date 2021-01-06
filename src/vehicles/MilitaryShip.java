package vehicles;

import enumerates.typesOfArms;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;

public class MilitaryShip extends Ship {//implements planeFactory{

    private typesOfArms typeOfArms;

    public MilitaryShip(Pair<DoubleProperty, DoubleProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, String firmName, int speed, typesOfArms typeOfArms, TravelRoute travelRoute) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers, speed, travelRoute);
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
