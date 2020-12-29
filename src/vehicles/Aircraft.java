package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import other.TravelRoute;
import ports.Airport;
import javafx.util.Pair;
import enumerates.typesOfArms;
import java.util.List;

public abstract class Aircraft extends Vehicle implements Runnable{

    // Variables
    private IntegerProperty maximumAmountOfFuel = new SimpleIntegerProperty(this, "maximumAmountOfFuel");
    private IntegerProperty currentAmountOfFuel = new SimpleIntegerProperty(this, "currentAmountOfFuel");
    private int amountOfStaff;
    //TODO aktualizuj lotniska
    private Airport lastVisitedAirport;
    private Airport nextAirport;
    private TravelRoute travelRoute = new TravelRoute();


    // Constructors

    public Aircraft(Pair<IntegerProperty, IntegerProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id);
        this.maximumAmountOfFuel = maximumAmountOfFuel;
        this.currentAmountOfFuel = currentAmountOfFuel;
        this.amountOfStaff = amountOfStaff;
        this.lastVisitedAirport = lastVisitedAirport;
        this.nextAirport = nextAirport;
        this.travelRoute = travelRoute;
    }

    public Aircraft() {
    }

    // Getters and Setters

    public int getMaximumAmountOfFuel() {
        return maximumAmountOfFuel.get();
    }

    public IntegerProperty maximumAmountOfFuelProperty() {
        return maximumAmountOfFuel;
    }

    public void setMaximumAmountOfFuel(int maximumAmountOfFuel) {
        this.maximumAmountOfFuel.set(maximumAmountOfFuel);
    }

    public int getCurrentAmountOfFuel() {
        return currentAmountOfFuel.get();
    }

    public IntegerProperty currentAmountOfFuelProperty() {
        return currentAmountOfFuel;
    }

    public void setCurrentAmountOfFuel(int currentAmountOfFuel) {
        this.currentAmountOfFuel.set(currentAmountOfFuel);
    }

    public int getAmountOfStaff() {
        return amountOfStaff;
    }

    public void setAmountOfStaff(int amountOfStaff) {
        this.amountOfStaff = amountOfStaff;
    }

    public Airport getLastVisitedAirport() {
        return lastVisitedAirport;
    }

    public void setLastVisitedAirport(Airport lastVisitedAirport) {
        this.lastVisitedAirport = lastVisitedAirport;
    }

    public Airport getNextAirport() {
        return nextAirport;
    }

    public void setNextAirport(Airport nextAirport) {
        this.nextAirport = nextAirport;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }

    // Methods

    public void land(){

    }

    public void startFlight(){}

    public void tankUp(){}

    public void changePassengers(){}

    public void reportGlitch(){}
    // Optymalizacja nazwy z crashLanding na emergencyLanding
    public void emergencyLanding(){}

    public void stayInQueue(){}

    @Override
    public String toString() {
        return "Aircraft " + this.getId();
    }
}
