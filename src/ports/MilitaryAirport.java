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

    public MilitaryAirport(String name, Pair<Double, Double> coordinates) {
        super(name, coordinates);
    }

    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport,
                                   TravelRoute travelRoute, typesOfArms typeOfArms)
    {
        return new MilitaryAircraft(coordinates, id, amountOfStaff,lastVisitedAirport,
                nextAirport, travelRoute, typeOfArms);
    }

    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
