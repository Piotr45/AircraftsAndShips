package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import other.TravelRoute;

public class MilitaryAirport extends Airport implements planeFactory{

    public MilitaryAirport(String name, Pair<DoubleProperty, DoubleProperty> coordinates) {
        super(name, coordinates);
    }

    @Override
    public Aircraft createAircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel,
                                   IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport,
                                   TravelRoute travelRoute, typesOfArms typeOfArms)
    {
        return new MilitaryAircraft(coordinates, id, maximumAmountOfFuel, currentAmountOfFuel, amountOfStaff,lastVisitedAirport,
                nextAirport, travelRoute, typeOfArms);
    }

    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
