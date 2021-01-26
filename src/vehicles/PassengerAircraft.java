package vehicles;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import java.util.List;

public class PassengerAircraft extends Aircraft {

    private int maximumAmountOfPassengers;
    private int currentAmountOfPassengers;

    public PassengerAircraft(Pair<Double, Double> coordinates, int maximumAmountOfPassengers,
                             int currentAmountOfPassengers, int id, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
    }

    public PassengerAircraft() {
    }

    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers = (currentAmountOfPassengers);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers;
    }

    @Override
    public String toString() {
        return "Passenger Aircraft " + this.getId();
    }
}
