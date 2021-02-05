package ports;

import enumerates.typesOfArms;
import interfaces.planeFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import other.Node;
import other.TravelRoute;
import vehicles.Aircraft;
import javafx.util.Pair;
import vehicles.PassengerAircraft;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * This class describes airport.
 */
public class Airport extends Node implements planeFactory {

    private Semaphore semaphore = new Semaphore(5);
    private Random random = new Random();

    /**
     * Class constructor with two params.
     * @param name - name of an airport e.g Warsaw.
     * @param coordinates - original coordinates of an airport.
     */
    public Airport(String name, Pair<Double, Double> coordinates) {
        super(coordinates, name);
    }

    /**
     * Class constructor with single param.
     * @param name - name of an airport.
     */
    public Airport(String name) {
        super(name);
    }

    /**
     * Empty class constructor.
     */
    public Airport() {
    }

    /**
     * In this method we will try to sleep thread and service aircraft that is occupying this airport right now.
     * @param aircraft - the plane that is occupying.
     */
    public void occupy(Aircraft aircraft) {
        try {
            Thread.sleep(3000);
            serviceAircraft(aircraft);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * In this method we will try to acquire semaphore.
     * @return - returns true if semaphore has some permits, else method returns false.
     */
    public Boolean isNodeFree() {
        if (this.semaphore.tryAcquire()) {
            return true;
        }
        return false;
    }

    /**
     * Releases semaphore.
     */
    public void freeNode() {
        semaphore.release();
    }

    private void serviceAircraft(Aircraft aircraft){
        try {
            ((PassengerAircraft) aircraft).setCurrentAmountOfPassengers(random.nextInt(
                    ((PassengerAircraft) aircraft).getMaximumAmountOfPassengers() - aircraft.getAmountOfStaff() ));
        } catch (Exception ignore) {
        }
        aircraft.setCurrentAmountOfFuel(aircraft.getMaximumAmountOfFuel());
    }

    /**
     * Gets info about airport.
     * @return - returns string with details about airport.
     */
    @Override
    public String getInfo() {
        return super.getInfo();
    }

    /**
     * Gets info about airport.
     * @return - returns string with name or coordinates of an airport.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Method used to create aircraft on airport.
     * @param coordinates - original coordinates of new aircraft.
     * @param maximumAmountOfPassengers - maximum amount of passengers that can fly in this aircraft.
     * @param currentAmountOfPassengers - current amount of passengers that are fling in this aircraft.
     * @param id - unique identifier of aircraft.
     * @param amountOfStaff - amount of people working in new aircraft.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @return - return aircraft.
     */
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int maximumAmountOfPassengers,
                                   int currentAmountOfPassengers, int id, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute) {
        return null;
    }

    /**
     * Method responsible for creating aircraft.
     * @param coordinates - original coordinates of object.
     * @param id - unique identifier for an object.
     * @param amountOfStaff - amount of stuff working in object.
     * @param lastVisitedAirport - last visited airport.
     * @param nextAirport - destination airport.
     * @param travelRoute - travel route of an aircraft.
     * @param typeOfArms - type of arming.
     * @return - returns aircraft.
     */
    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        return null;
    }
}
