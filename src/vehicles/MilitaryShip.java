package vehicles;

import enumerates.typesOfArms;
import javafx.util.Pair;
import interfaces.planeFactory;

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
    public void createPlane() {

    }

}
