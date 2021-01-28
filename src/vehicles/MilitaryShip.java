package vehicles;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;

/**
 * This class describes military ship.
 */
public class MilitaryShip extends Ship implements planeFactory {

    private typesOfArms typeOfArms;

    /**
     * MilitaryShip class constructor with four params.
     * @param id - unique identifier of vehicle.
     * @param speed - velocity of this ship.
     * @param typeOfArms - type of arming, that we choose form typesOfArms enumerate.
     * @param travelRoute - travel route of this ship.
     */
    public MilitaryShip(int id, int speed, typesOfArms typeOfArms, TravelRoute travelRoute) {
        super(id, speed, travelRoute);
        this.typeOfArms = typeOfArms;
    }

    /**
     * Empty constructor.
     */
    public MilitaryShip() {
    }

    /**
     * Gets type of arming.
     * @return - returns type of arming.
     */
    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    /**
     * Runs thread.
     */
    @Override
    public void run() {
        super.run();
    }

    /**
     * Adds type of arming to information about this ship.
     * @return - returns information about this ship as string.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Type of arm: " + typeOfArms.name();
    }

    /**
     * Converts name of this object to string.
     * @return - returns string: "Military ship " + id of this object.
     */
    @Override
    public String toString() {
        return "Military Ship " + this.getId();
    }

    /**
     * This method creates military aircraft object with the same type of arming.
     * @param coordinates - original coordinates of object.
     * @param id - unique identifier for an object.
     * @param amountOfStaff - amount of stuff working in object.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @param typeOfArms - type of arming.
     * @return - returns military aircraft.
     */
    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        return new MilitaryAircraft(coordinates, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute, typeOfArms);
    }
}
