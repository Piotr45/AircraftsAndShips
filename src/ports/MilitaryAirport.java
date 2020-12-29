package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import graphicalUserInterface.ControlPanel;
import other.TravelRoute;

public class MilitaryAirport extends Airport implements planeFactory{

    public MilitaryAirport(String name, Pair<IntegerProperty, IntegerProperty> coordinates) {
        super(name, coordinates);
    }

    @Override
    public Aircraft createAircraft(Pair<IntegerProperty, IntegerProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        return null;
    }

    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
