package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import other.TravelRoute;

/**
 * This class describes military airport.
 */
public class MilitaryAirport extends Airport implements planeFactory{

    /**
     * Constructor of MilitaryAirport class with two params.
     * @param name - name of an airport e.g. Area 51.
     * @param coordinates - coordinates of an airport.
     */
    public MilitaryAirport(String name, Pair<Double, Double> coordinates) {
        super(name, coordinates);
    }

    /**
     * This method creates military aircraft.
     * @param coordinates - original coordinates of object.
     * @param id - unique identifier for an object.
     * @param amountOfStaff - amount of stuff working in object.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @param typeOfArms - type of arming.
     * @return - returns military aircraft object.
     */
    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport,
                                   TravelRoute travelRoute, typesOfArms typeOfArms)
    {
        return new MilitaryAircraft(coordinates, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute, typeOfArms);
    }

    /**
     * This method returns full name of an airport.
     * @return - returns name of an airport + string "Airport".
     */
    @Override
    public String toString() {
        return this.getName() + " Airport";
    }
}
