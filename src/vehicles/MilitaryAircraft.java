package vehicles;

import enumerates.typesOfArms;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import other.TravelRoute;
import ports.Airport;
import javafx.util.Pair;
import java.util.List;

/**
 * This class represents military aircraft.
 */
public class MilitaryAircraft extends Aircraft{

    private typesOfArms typeOfArms;

    /**
     * MilitaryAircraft class constructor with seven parameters.
     * @param coordinates - original coordinates of aircraft.
     * @param id - unique identifier of vehicle.
     * @param amountOfStaff - amount of staff.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of military aircraft.
     * @param typeOfArms - type of arming.
     */
    public MilitaryAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                            Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        super(coordinates, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
        this.typeOfArms = typeOfArms;
    }

    /**
     * Empty class constructor.
     */
    public MilitaryAircraft() {
    }

    /**
     * Gets information about aircraft.
     * @return - returns string with information about aircraft.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Type of arm: " + typeOfArms.toString();
    }

    /**
     * Gets type of arming.
     * @return - returns type of arming.
     */
    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    /**
     * Sets type of arming.
     * @param typeOfArms - type of arming from class typeOfArms.
     */
    public void setTypeOfArms(typesOfArms typeOfArms) {
        this.typeOfArms = typeOfArms;
    }

    /**
     * Converts object name to string.
     * @return - returns string: "MilitaryAircraft " + id of this object.
     */
    @Override
    public String toString() {
        return "MilitaryAircraft " + this.getId();
    }
}
