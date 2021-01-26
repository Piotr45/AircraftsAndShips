package vehicles;

import enumerates.States;
import graphicalUserInterface.Controller;
import graphicalUserInterface.MapPanelView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
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

public abstract class Aircraft extends Vehicle {

    private double maximumAmountOfFuel;
    private double currentAmountOfFuel;
    private int amountOfStaff;
    private Airport lastVisitedAirport;
    private Airport currentAirport;
    private Airport nextAirport;
    private Boolean damaged = false;
    private double distanceBetweenAircraftAndAirport;

    public Aircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                    Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, 30, travelRoute);
        this.setCoordinates(coordinates);

        this.maximumAmountOfFuel = 100.0;
        this.currentAmountOfFuel = 100.0;
        this.amountOfStaff = amountOfStaff;
        this.lastVisitedAirport = lastVisitedAirport;
        this.nextAirport = nextAirport;
        this.currentAirport = (Airport) getNode(getCoordinates(), travelRoute);
        newDestinationBasedOnCurrent();
    }

    public Aircraft() {
    }

    public List<Airport> getListOfNodes() {
        List<Airport> arrayList = new ArrayList<>();
        arrayList.add(lastVisitedAirport);
        arrayList.add(currentAirport);
        arrayList.add(nextAirport);
        return arrayList;
    }

    public Airport getCurrentAirport() {
        return currentAirport;
    }

    public double getMaximumAmountOfFuel() {
        return maximumAmountOfFuel;
    }

    public double maximumAmountOfFuelProperty() {
        return maximumAmountOfFuel;
    }

    public void setMaximumAmountOfFuel(double maximumAmountOfFuel) {
        this.maximumAmountOfFuel = (maximumAmountOfFuel);
    }

    public double getCurrentAmountOfFuel() {
        return currentAmountOfFuel;
    }

    public void setCurrentAmountOfFuel(double currentAmountOfFuel) {
        this.currentAmountOfFuel = (currentAmountOfFuel);
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
    public void newDestinationBasedOnCurrent() throws NullPointerException{
        if (currentAirport.equals(this.getTravelRoute().getCheckpoints().get(
                this.getTravelRoute().getCheckpoints().size() - 1))) {
            Collections.reverse(this.getTravelRoute().getCheckpoints());
            nextAirport = (Airport) this.getTravelRoute().getCheckpoints().get(1);
        } else {
            nextAirport = (Airport) this.getTravelRoute().getCheckpoints().get(this.getTravelRoute().getCheckpoints().indexOf(currentAirport) + 1);
        }
    }

    private Boolean checkIfCanFly() {
        for (Aircraft aircraft : Controller.getListOfAircrafts()) {
            if ((aircraft.getListOfNodes().get(2) == this.currentAirport && aircraft.getState().equals(States.traveling)
                    && aircraft.getListOfNodes().get(1) == this.nextAirport) || (aircraft.getListOfNodes().get(2) == this.nextAirport
                    && aircraft.getState().equals(States.traveling) && aircraft.getListOfNodes().get(1) == this.currentAirport)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateNodes() {
        lastVisitedAirport = (Airport) getNode(currentAirport.getCoordinates(), this.getTravelRoute());
        System.out.println(nextAirport.toString());
        if (this.damaged) {
            currentAirport = nextAirport;
        } else {
            currentAirport = (Airport) getNode(nextAirport.getCoordinates(), this.getTravelRoute());
        }
        newDestinationBasedOnCurrent();
    }

    @Override
    public synchronized void move(double deltaT) throws InterruptedException {
        switch (this.getState()) {
            case traveling:
                if (moveTo(this, deltaT, nextAirport)) {
                    this.setState(States.waiting);
                }
                updateFuel();
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
                if (damaged) {
                    Thread.currentThread().join();
                    Controller.getListOfAircrafts().remove(Controller.getListOfAircrafts().indexOf(this));
                } else {
                    currentAirport.occupy(this);
                    if (checkIfCanFly()) {
                        setState(States.traveling);
                        distanceBetweenAircraftAndAirport = findDistanceBetweenAircraftAndAirport(nextAirport);
                    } else {
                        Thread.sleep(100);
                    }
                }
                currentAirport.freeNode();
                break;
        }
    }

    @Override
    public void run() {
        super.run();
    }

    private void updateFuel() {
        currentAmountOfFuel = findDistanceBetweenAircraftAndAirport(nextAirport) / distanceBetweenAircraftAndAirport * 100;
    }

    private double findDistanceBetweenAircraftAndAirport (Airport airport) {
        return  Math.sqrt(Math.pow((airport.getCoordinates().getKey() - this.getCoordinates().getKey()), 2) +
                Math.pow((airport.getCoordinates().getValue() - this.getCoordinates().getValue()), 2));
    }

    private Airport findClosestAirport() {
        Airport closestAirport = Controller.getListOfAirports().get(0);
        double distance = findDistanceBetweenAircraftAndAirport(closestAirport);
        for (Airport airport : Controller.getListOfAirports()) {
            double newDistance = findDistanceBetweenAircraftAndAirport(airport);
            if (distance > newDistance) {
                closestAirport = airport;
                distance = newDistance;
            }
        }
        return closestAirport;
    }

    public void emergencyLanding() {
        this.nextAirport = findClosestAirport();
        damaged = true;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of fuel: " + maximumAmountOfFuel + "\n" +
                "Current amount of fuel: " + currentAmountOfFuel + "\n" +
                "Last visited airport: " + currentAirport.toString() + "\n" +
                "Next airport: " + nextAirport.toString() + "\n" +
                "Amount of staff: " + amountOfStaff;
    }

    @Override
    public String toString() {
        return "Aircraft " + this.getId();
    }
}
