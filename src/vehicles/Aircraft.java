package vehicles;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import other.TravelRoute;
import ports.Airport;
import javafx.util.Pair;
import enumerates.typesOfArms;

import java.util.List;

public abstract class Aircraft extends Vehicle{

    private IntegerProperty maximumAmountOfFuel = new SimpleIntegerProperty(this, "maximumAmountOfFuel");
    private IntegerProperty currentAmountOfFuel = new SimpleIntegerProperty(this, "currentAmountOfFuel");
    private int amountOfStaff;
    //TODO aktualizuj lotniska
    private Airport lastVisitedAirport;
    private Airport nextAirport;

    public Aircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, 10, travelRoute);
        this.maximumAmountOfFuel = maximumAmountOfFuel;
        this.currentAmountOfFuel = currentAmountOfFuel;
        this.amountOfStaff = amountOfStaff;
        this.lastVisitedAirport = lastVisitedAirport;
        this.nextAirport = nextAirport;
    }

    public Aircraft() {
    }

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

    public void land() {

    }

    private boolean checkIfInRange(int bound, int x) {
        if (bound < 0) {
            return x > bound && x < (bound * (-1));
        }
        return x < bound && x > (bound * (-1));
    }

    public void fly() {
//        int nextAirportX = nextAirport.getCoordinates().getKey().get();
//        int nextAirportY = nextAirport.getCoordinates().getValue().get();
//        int myX = this.getCoordinates().getKey().get();
//        int myY = this.getCoordinates().getValue().get();
//
//        Pair<Integer, Integer> velocityVector = new Pair<>((nextAirportX - myX) / 10, (nextAirportY - myY) / 10);
//
//        int i = 0;
//        while (!nextAirport.getCoordinates().equals(this.getCoordinates())) {
//            if ((checkIfInRange(velocityVector.getKey(), nextAirportX - this.getCoordinates().getKey().get())) && (
//                    checkIfInRange(velocityVector.getValue(), nextAirportY - this.getCoordinates().getValue().get()))) {
//                this.setCoordinates(nextAirport.getCoordinates());
//                break;
//            }
//
//            this.setCoordinates(new Pair<IntegerProperty, IntegerProperty>(
//                    new SimpleIntegerProperty((this.getCoordinates().getKey().get() + velocityVector.getKey())),
//                    new SimpleIntegerProperty((this.getCoordinates().getValue().get() + velocityVector.getValue()))));
//            System.out.println("(" + this.getCoordinates().getKey().get() + ", " + this.getCoordinates().getValue().get() + ")");
//
//            i++;
//            if (i == 11) {
//                break;
//            }
//        }
    }

    public void tankUp() {
    }

    public void changePassengers() {
    }

    public void reportGlitch() {
    }

    public void emergencyLanding() {
    }

    public void stayInQueue() {
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of fuel: " + maximumAmountOfFuel.toString() + "\n" +
                "Current amount of fuel: " + currentAmountOfFuel.toString() + "\n" +
                "Travel route: " + getTravelRoute().toString() + "\n" +
                "Last visited airport: " + lastVisitedAirport.toString() + "\n" +
                "Next airport: " + nextAirport.toString() + "\n" +
                "Amount of staff: " + amountOfStaff;
    }

    @Override
    public String toString() {
        return "Aircraft " + this.getId();
    }
}
