package interfaces;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import enumerates.typesOfArms;
import vehicles.Aircraft;

/**
 * This interface is responsible for creating aircraft object.
 */
public interface planeFactory {

    /**
     * This method is used to create aircraft object.
     * @param coordinates - original coordinates of object.
     * @param id - unique identifier for an object.
     * @param amountOfStaff - amount of stuff working in object.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @param typeOfArms - type of arming.
     * @return - returns aircraft instance.
     */
    Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                            Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms);

}
