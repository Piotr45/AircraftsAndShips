package vehicles;

import enumerates.States;
import graphicalUserInterface.MapPanelView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import other.SeaNode;
import other.TravelRoute;
import ports.Airport;
import javafx.util.Pair;
import enumerates.typesOfArms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Aircraft extends Vehicle{

    private IntegerProperty maximumAmountOfFuel = new SimpleIntegerProperty(this, "maximumAmountOfFuel");
    private IntegerProperty currentAmountOfFuel = new SimpleIntegerProperty(this, "currentAmountOfFuel");
    private int amountOfStaff;
    private Airport lastVisitedAirport;
    private Airport currentAirport;
    private Airport nextAirport;

    public Aircraft(Pair<Double, Double> coordinates, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, 200, travelRoute);
        this.setCoordinates(coordinates);

        this.maximumAmountOfFuel = maximumAmountOfFuel;
        this.currentAmountOfFuel = currentAmountOfFuel;
        this.amountOfStaff = amountOfStaff;
        this.lastVisitedAirport = lastVisitedAirport;
        this.nextAirport = nextAirport;
        this.currentAirport = (Airport) getNode(getCoordinates(), travelRoute);
        newDestinationBasedOnCurrent();
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

    @Override
    public void newDestinationBasedOnCurrent() {
        if (currentAirport.equals(this.getTravelRoute().getCheckpoints().get(
                this.getTravelRoute().getCheckpoints().size() - 1))){
            Collections.reverse(this.getTravelRoute().getCheckpoints());
            nextAirport = (Airport) this.getTravelRoute().getCheckpoints().get(1);
        }
        else {
            nextAirport = (Airport) this.getTravelRoute().getCheckpoints().get(this.getTravelRoute().getCheckpoints().indexOf(currentAirport) + 1);
        }
    }

    @Override
    public void updateNodes() {
        lastVisitedAirport = (Airport) getNode(currentAirport.getCoordinates(), this.getTravelRoute());
        currentAirport = (Airport) getNode(nextAirport.getCoordinates(), this.getTravelRoute());
        newDestinationBasedOnCurrent();
    }

    @Override
    public synchronized void move(double deltaT) throws InterruptedException {
        switch (this.getState()) {
            case traveling:
                if (moveTo(this, deltaT, nextAirport)) {
                    this.setState(States.waiting);
                }
                break;
            case waiting:
                if (nextAirport.isNodeFree()) {
                    updateNodes();
                    setState(States.arriving);
                } else {
                    Thread.sleep(250);
                    System.out.println("Failed");
                }
                break;
            case arriving:
                this.setCoordinates(currentAirport.getCoordinates());
                currentAirport.occupy(this);
                if (true) {
                    currentAirport.freeNode();
                    setState(States.traveling);
                }
                break;
        }
    }

    @Override
    public void run() {
        super.run();
    }

    public void reportGlitch() {
    }

    public void emergencyLanding() {
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
