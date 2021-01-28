package ports;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import vehicles.Aircraft;
import vehicles.PassengerAircraft;

/**
 * This class represents civilian airport
 */
public class CivilianAirport extends Airport {

    /**
     * CivilianAirport class constructor with two params.
     * @param name - name of an airport.
     * @param coordinates - coordinates of an airport.
     */
    public CivilianAirport(String name, Pair<Double, Double> coordinates) {
        super(name, coordinates);
    }

    /**
     * Creates aircraft.
     * @param coordinates - original coordinates of new aircraft.
     * @param maximumAmountOfPassengers - maximum amount of passengers that can fly in this aircraft.
     * @param currentAmountOfPassengers - current amount of passengers that are fling in this aircraft.
     * @param id - unique identifier of aircraft.
     * @param amountOfStaff - amount of people working in new aircraft.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @return
     */
    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int maximumAmountOfPassengers,
                                   int currentAmountOfPassengers, int id,int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute) {
        return new PassengerAircraft(coordinates, maximumAmountOfPassengers, currentAmountOfPassengers, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
    }

    /**
     * This method returns full name of an airport.
     * @return - returns this airport name + string "Airport"
     */
    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
