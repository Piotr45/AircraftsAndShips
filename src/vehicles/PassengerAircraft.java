package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import java.util.List;

public class PassengerAircraft extends Aircraft {

    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");

    public PassengerAircraft(Pair<IntegerProperty, IntegerProperty> coordinates, int maximumAmountOfPassengers,
                             IntegerProperty currentAmountOfPassengers, int id, IntegerProperty maximumAmountOfFuel,
                             IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, maximumAmountOfFuel, currentAmountOfFuel, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
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
        return currentAmountOfPassengers.get();
    }

    public IntegerProperty currentAmountOfPassengersProperty() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers.set(currentAmountOfPassengers);
    }

    @Override
    public void run() {

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
