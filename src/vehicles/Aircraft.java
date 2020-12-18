package vehicles;

import ports.Airport;
import javafx.util.Pair;
import enumerates.typesOfArms;
import java.util.List;

public abstract class Aircraft implements Runnable{

    // Variables

    private float maximumAmountOfFuel;
    private float currentAmountOfFuel;
    private int maximumAmountOfPassengers;
    private int currentAmountOfPassengers;
    private int amountOfStaff;
    private Airport lastVisitedAirport;
    private Airport nextAirport;
    private String travelRoute;
    private int id;
    private Pair<Integer, Integer> coordinates;
    private typesOfArms typeOfArms;

    // Constructor

    public Aircraft(float maximumAmountOfFuel, int maximumAmountOfPassengers, int currentAmountOfPassengers, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, String travelRoute, int id, Pair<Integer, Integer> coordinates) {
        setMaximumAmountOfFuel(maximumAmountOfFuel);
        setCurrentAmountOfFuel(maximumAmountOfFuel);
        setMaximumAmountOfPassengers(maximumAmountOfPassengers);
        setCurrentAmountOfPassengers(currentAmountOfPassengers);
        setAmountOfStaff(amountOfStaff);
        setLastVisitedAirport(lastVisitedAirport);
        setNextAirport(nextAirport);
        setTravelRoute(travelRoute);
        setId(id);
        setCoordinates(coordinates);
    }

    public Aircraft() {
        setMaximumAmountOfFuel(0);
        setCurrentAmountOfFuel(0);
        setMaximumAmountOfPassengers(0);
        setCurrentAmountOfPassengers(0);
        setAmountOfStaff(0);
        setLastVisitedAirport(null);
        setNextAirport(null);
        setTravelRoute(null);
        setId(-1);
        setCoordinates(null);
    }

    // Getters and Setters

    public float getMaximumAmountOfFuel() {
        return maximumAmountOfFuel;
    }

    public void setMaximumAmountOfFuel(float maximumAmountOfFuel) {
        this.maximumAmountOfFuel = maximumAmountOfFuel;
    }

    public float getCurrentAmountOfFuel() {
        return currentAmountOfFuel;
    }

    public void setCurrentAmountOfFuel(float currentAmountOfFuel) {
        this.currentAmountOfFuel = currentAmountOfFuel;
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
        this.currentAmountOfPassengers = currentAmountOfPassengers;
    }

    public int getAmountOfStaff() {
        return amountOfStaff;
    }

    public void setAmountOfStaff(int amountOfStaff) {
        this.amountOfStaff = amountOfStaff;
    }

    public Airport getLastVisitedAirports() {
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

    public String getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(String travelRoute) {
        this.travelRoute = travelRoute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    public void setTypeOfArms(typesOfArms typeOfArms) {
        this.typeOfArms = typeOfArms;
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
        return "Aircraft " + id;
    }
}
