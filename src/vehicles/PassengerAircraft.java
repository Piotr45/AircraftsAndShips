package vehicles;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import java.util.List;

/**
 * PassengerAircraft class describes passenger aircraft.
 */
public class PassengerAircraft extends Aircraft {

    private int maximumAmountOfPassengers;
    private int currentAmountOfPassengers;

    /**
     * PassengerAircraft class constructor with eight parameters.
     * @param coordinates - original coordinates of aircraft.
     * @param maximumAmountOfPassengers - maximum amount of passengers that can be in aircraft.
     * @param currentAmountOfPassengers - current amount of passengers in aricraft.
     * @param id - unique identifier of vehicle.
     * @param amountOfStaff - amount of stuff, that are working in aircraft.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of aircraft.
     */
    public PassengerAircraft(Pair<Double, Double> coordinates, int maximumAmountOfPassengers,
                             int currentAmountOfPassengers, int id, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
    }

    /**
     * Empty constructor.
     */
    public PassengerAircraft() {
    }

    /**
     * Gets maximum amount of passengers.
     * @return - returns maximum amount of passengers.
     */
    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    /**
     * Sets new maximum amount of passengers.
     * @param maximumAmountOfPassengers - new maximum amount of passengers.
     */
    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    /**
     * Gets current amount of passengers.
     * @return - returns current amount of passengers.
     */
    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers;
    }

    /**
     * Sets new current amount of passengers.
     * @param currentAmountOfPassengers - new current amount of passengers.
     */
    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers = (currentAmountOfPassengers);
    }

    /**
     * Gets information about aircraft.
     * @return - returns string with information about aircraft.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers;
    }

    /**
     * Converts object name to string.
     * @return - returns string: "Passenger Aircraft " + id of this object.
     */
    @Override
    public String toString() {
        return "Passenger Aircraft " + this.getId();
    }
}
