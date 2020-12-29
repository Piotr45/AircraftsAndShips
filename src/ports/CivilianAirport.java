package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;
import graphicalUserInterface.ControlPanel;

public class CivilianAirport extends Airport {

    public CivilianAirport(String name, Pair<IntegerProperty, IntegerProperty> coordinates) {
        super(name, coordinates);
    }

    public CivilianAirport() {
        super();
    }

    @Override
    public Aircraft createAircraft(Pair<IntegerProperty, IntegerProperty> coordinates, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        return new PassengerAircraft(coordinates, maximumAmountOfPassengers, currentAmountOfPassengers, id, maximumAmountOfFuel, currentAmountOfFuel, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
    }

    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
