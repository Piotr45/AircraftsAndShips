package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import other.Node;
import other.TravelRoute;
import vehicles.Aircraft;
import javafx.util.Pair;

public class Airport extends Node implements planeFactory {

    private Aircraft currentServicedAircraft = null;

    public Airport(String name, Pair<DoubleProperty, DoubleProperty> coordinates) {
        super(coordinates, name);
    }

    public Airport(String name) {
        super(name);
    }

    public Airport() {
    }

    public Aircraft getCurrentServicedAircraft() {
        return currentServicedAircraft;
    }

    public void setCurrentServicedAircraft(Aircraft currentServicedAircraft) {
        this.currentServicedAircraft = currentServicedAircraft;
    }


    public void serviceAircraft(){}

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Current serviced aircraft: " + this.getCurrentServicedAircraft();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Aircraft createAircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int maximumAmountOfPassengers,
                                   IntegerProperty currentAmountOfPassengers, int id, IntegerProperty maximumAmountOfFuel,
                                   IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute) {
        return null;
    }

    @Override
    public Aircraft createAircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel,
                                   IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        return null;
    }
}
