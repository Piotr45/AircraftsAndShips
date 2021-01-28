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

/**
 * This class represents aircraft.
 */
public abstract class Aircraft extends Vehicle {

    private double maximumAmountOfFuel;
    private double currentAmountOfFuel;
    private int amountOfStaff;
    private Airport lastVisitedAirport;
    private Airport currentAirport;
    private Airport nextAirport;
    private Boolean damaged = false;
    private double distanceBetweenAircraftAndAirport;

    /**
     * Aircraft class constructor with six parameters.
     * @param coordinates - original coordinates of aircraft.
     * @param id - unique identifier of vehicle.
     * @param amountOfStaff - amount of staff.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of this aircraft.
     */
    public Aircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                    Airport nextAirport, TravelRoute travelRoute) {
        super(coordinates, id, 230, travelRoute);
        this.setCoordinates(coordinates);

        this.maximumAmountOfFuel = 100.0;
        this.currentAmountOfFuel = 100.0;
        this.amountOfStaff = amountOfStaff;
        this.lastVisitedAirport = lastVisitedAirport;
        this.nextAirport = nextAirport;
        this.currentAirport = (Airport) getNode(getCoordinates(), travelRoute);
        newDestinationBasedOnCurrent();
    }

    /**
     * Empty constructor.
     */
    public Aircraft() {
    }

    /**
     * Gets list of nodes. List contains last visited airport, current airport and destination airport.
     * @return - returns list of nodes.
     */
    public List<Airport> getListOfNodes() {
        List<Airport> arrayList = new ArrayList<>();
        arrayList.add(lastVisitedAirport);
        arrayList.add(currentAirport);
        arrayList.add(nextAirport);
        return arrayList;
    }

    /**
     * Gets current airport.
     * @return - returns current airport.
     */
    public Airport getCurrentAirport() {
        return currentAirport;
    }

    /**
     * Gets maximum amount of fuel.
     * @return - returns maximum amount of fuel.
     */
    public double getMaximumAmountOfFuel() {
        return maximumAmountOfFuel;
    }

    /**
     * Sets maximum amount of fuel.
     * @param maximumAmountOfFuel - new maximum amount of fuel.
     */
    public void setMaximumAmountOfFuel(double maximumAmountOfFuel) {
        this.maximumAmountOfFuel = (maximumAmountOfFuel);
    }

    /**
     * Gets current amount of fuel.
     * @return - returns current amount of fuel.
     */
    public double getCurrentAmountOfFuel() {
        return currentAmountOfFuel;
    }

    /**
     * Sets current amount of fuel.
     * @param currentAmountOfFuel - new current amount of fuel.
     */
    public void setCurrentAmountOfFuel(double currentAmountOfFuel) {
        this.currentAmountOfFuel = (currentAmountOfFuel);
    }

    /**
     * Gets amount of staff.
     * @return - returns amount of staff.
     */
    public int getAmountOfStaff() {
        return amountOfStaff;
    }

    /**
     * Sets amount of staff.
     * @param amountOfStaff - new amount of staff.
     */
    public void setAmountOfStaff(int amountOfStaff) {
        this.amountOfStaff = amountOfStaff;
    }

    /**
     * Gets last visited airport.
     * @return - returns last visited airport.
     */
    public Airport getLastVisitedAirport() {
        return lastVisitedAirport;
    }

    /**
     * Sets last visited airport.
     * @param lastVisitedAirport - new last visited airport.
     */
    public void setLastVisitedAirport(Airport lastVisitedAirport) {
        this.lastVisitedAirport = lastVisitedAirport;
    }

    /**
     * Gets destination airport.
     * @return - returns destination airport.
     */
    public Airport getNextAirport() {
        return nextAirport;
    }

    /**
     * Sets new next airport.
     * @param nextAirport - new destination airport.
     */
    public void setNextAirport(Airport nextAirport) {
        this.nextAirport = nextAirport;
    }

    /**
     * Overrides method form vehicle.
     * This method finds new destination airport based on current location of aircraft.
     * @throws NullPointerException - throws null pointer exception.
     */
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

    /**
     * Overrides method from vehicle class.
     * This method updates last visited airport, current airport and destination airport to new values.
     */
    @Override
    public void updateNodes() {
        lastVisitedAirport = (Airport) getNode(currentAirport.getCoordinates(), this.getTravelRoute());
        if (this.damaged) {
            currentAirport = nextAirport;
        } else {
            currentAirport = (Airport) getNode(nextAirport.getCoordinates(), this.getTravelRoute());
        }
        newDestinationBasedOnCurrent();
    }

    /**
     * Overrides method from vehicle class.
     * This method is responsible for states and movement of aircraft.
     * @param deltaT - delta time.
     * @throws InterruptedException - trying to sleep thread can cause InterruptedException.
     */
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
                    Controller.removeAircraftFromListOfAircrafts(this);
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

    /**
     * Runs thread.
     */
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

    /**
     * This method is sending aircraft to closest airport and marks it as damaged.
     */
    public void emergencyLanding() {
        this.nextAirport = findClosestAirport();
        damaged = true;
    }

    /**
     * Gets information about aircraft.
     * @return - returns information about aircraft as string.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of fuel: " + maximumAmountOfFuel + "\n" +
                "Current amount of fuel: " + currentAmountOfFuel + "\n" +
                "Last visited airport: " + currentAirport.toString() + "\n" +
                "Next airport: " + nextAirport.toString() + "\n" +
                "Amount of staff: " + amountOfStaff;
    }

    /**
     * Converts object name to string.
     * @return - returns string: "Aircraft " + id of it.
     */
    @Override
    public String toString() {
        return "Aircraft " + this.getId();
    }
}
