package vehicles;

import com.sun.javafx.geom.Vec2d;
import enumerates.FirmNames;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.TravelRoute;

import java.util.Vector;

/**
 * This class describes passenger ship object.
 */
public class PassengerShip extends Ship {

    private FirmNames firmName;
    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");

    /**
     * Passenger ship constructor with six parameters.
     * @param id - unique identifier of vehicle.
     * @param maximumAmountOfPassengers - maximum amount of passengers that can be on ship.
     * @param currentAmountOfPassengers - current amount of passengers that are on ship.
     * @param firmName - firm name of ship.
     * @param speed - velocity of ship.
     * @param travelRoute - travel route of an object.
     */
    public PassengerShip(int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers,
                         FirmNames firmName, int speed, TravelRoute travelRoute) {
        super(id, speed, travelRoute);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
        this.firmName = firmName;
    }

    /**
     * Empty constructor.
     */
    public PassengerShip() {
        super();
    }

    /**
     * Sets maximum amount of passengers.
     * @param maximumAmountOfPassengers - new maximum amount of passengers.
     */
    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    /**
     * Gets current amount of people on ship.
     * @return - returns current amount of passengers on ship.
     */
    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers.get();
    }

    /**
     * Gets maximum amount of passengers.
     * @return - returns amount of passengers.
     */
    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    /**
     * Gets firm name that made this ship.
     * @return - returns firm name.
     */
    public FirmNames getFirmName() {
        return firmName;
    }

    /**
     * Changes firm name.
     * @param firmName - name of new firm.
     */
    public void setFirmName(FirmNames firmName) {
        this.firmName = firmName;
    }

    /**
     * Adds new information about this ship, such as firm name, maximum and current amount of passengers.
     * @return - returns string with information about this object.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Firm name: " + firmName + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers;
    }

    /**
     * Converts name of this object to string.
     * @return - returns string: firm name + " Ship " + id of this ship.
     */
    @Override
    public String toString() {
        return this.getFirmName() + " Ship " + this.getId();
    }
}
