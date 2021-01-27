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

public class Airport extends Node implements planeFactory {

    private Semaphore semaphore = new Semaphore(5);
    private Random random = new Random();

    public Airport(String name, Pair<Double, Double> coordinates) {
        super(coordinates, name);
    }

    public Airport(String name) {
        super(name);
    }

    public Airport() {
    }

    public void occupy(Aircraft aircraft) {
        try {
            Thread.sleep(3000);
            serviceAircraft(aircraft);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isNodeFree() {
        if (this.semaphore.tryAcquire()) {
            return true;
        }
        return false;
    }

    public void freeNode() {
        semaphore.release();
    }


    public void serviceAircraft(Aircraft aircraft){
        try {
            ((PassengerAircraft) aircraft).setCurrentAmountOfPassengers(random.nextInt(
                    ((PassengerAircraft) aircraft).getMaximumAmountOfPassengers() - aircraft.getAmountOfStaff() ));
        } catch (Exception ignore) {
        }
        aircraft.setCurrentAmountOfFuel(aircraft.getMaximumAmountOfFuel());
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Aircraft createAircraft(Pair<Double, Double> coordinates, int maximumAmountOfPassengers,
                                   int currentAmountOfPassengers, int id, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute) {
        return null;
    }

    @Override
    public Aircraft createAircraft(Pair<Double, Double> coordinates, int id, int amountOfStaff, Airport lastVisitedAirport,
                                   Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        return null;
    }
}
