package vehicles;

import enumerates.States;
import graphicalUserInterface.Main;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.TravelRoute;

import java.util.Random;

/**
 * Class vehicle represents a vehicle.
 */
public abstract class Vehicle extends Node implements Runnable {

    private int id;
    private States state;
    private int velocity;
    private TravelRoute travelRoute;
    private Node previousNode;
    private Node currentNode;
    private Node destinationNode;
    private Thread thread;

    /**
     * Vehicle class constructor with four params.
     * @param coordinates - original coordinates of vehicle.
     * @param id - unique identifier of vehicle.
     * @param velocity - velocity of an vehicle.
     * @param travelRoute - travel route of an vehicle.
     */
    public Vehicle(Pair<Double, Double> coordinates, int id, int velocity, TravelRoute travelRoute) {
        super(coordinates);
        this.id = id;
        this.state = States.arriving;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    /**
     * Vehicle class constructor with three parameters.
     * @param id - unique identifier of vehicle.
     * @param velocity - velocity of an vehicle.
     * @param travelRoute - travel route of an vehicle.
     */
    public Vehicle(int id, int velocity, TravelRoute travelRoute) {
        super(travelRoute.findRandomCheckpoint().getCoordinates(), 1);
        this.id = id;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    /**
     * Empty constructor.
     */
    public Vehicle() {
    }

    /**
     * Set new travel route.
     * @param travelRoute - travel route object.
     */
    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }

    /**
     * Gets thread.
     * @return - returns thread.
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Sets thread.
     */
    public void setThread() {
        this.thread = new Thread(Main.threadGroup, this);
    }

    /**
     * Gets identifier of an object.
     * @return - returns id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets identifier.
     * @param id - unique identifier of vehicle.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets state of an object.
     * @return - returns state of this object.
     */
    public States getState() {
        return state;
    }

    /**
     * Gets velocity of object.
     * @return - returns velocity of vehicle.
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Sets new movement state.
     * @param state - one of movements states form class States.
     */
    public void setState(States state) {
        this.state = state;
    }

    /**
     * Gets travel route of an object.
     * @return - returns travel route.
     */
    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    /**
     * Compares two pairs of coordinates.
     * @param pair1 - first pair of coordinates.
     * @param pair2 - second pair of coordinates.
     * @return - returns true if pairs match, else false
     */
    public Boolean areCoordinatesTheSame(Pair<Double, Double> pair1,
                                         Pair<Double, Double> pair2) {
        return pair1.getKey() == pair2.getKey() && pair1.getValue() == pair2.getValue();
    }

    /**
     * This method searches checkpoint list in travel route and if passed coordinates belongs to node in this list
     * return that node.
     * @param pair - pair of two doubles, that we want to check.
     * @param travelRoute - travel route, that we want to iterate.
     * @return - returns node if we found node with this coordinates, else return null.
     */
    public Node getNode(Pair<Double, Double> pair, TravelRoute travelRoute) {
        for (Node node : travelRoute.getCheckpoints()) {
            if (areCoordinatesTheSame(pair, node.getCoordinates())) {
                return node;
            }
        }
        return null;
    }

    /**
     * Updates nodes, implemented later.
     */
    public void updateNodes() {
    }

    /**
     * Finds new destination. Implemented later.
     */
    public void newDestinationBasedOnCurrent() {

    }

    /**
     * This method moves vehicle in its destination.
     * @param vehicle - vehicle that we are moving.
     * @param deltaT - delta time.
     * @param nextCheckpoint - destination.
     * @return - returns true if vehicle is not near destination, else false.
     */
    public Boolean moveTo(Vehicle vehicle, double deltaT, Node nextCheckpoint) {
        MyVector vector = new MyVector(vehicle.getCoordinates(), nextCheckpoint.getCoordinates());
        MyVector normalizedVector = new MyVector(vector);
        normalizedVector.normalise();
        normalizedVector.multiplicationNormalizedVector(this.getVelocity() * deltaT);
        normalizedVector.recalculateMagnitude();

        if (normalizedVector.getMagnitude() + 2 < vector.getMagnitude()) {
            this.setCoordinates(new Pair<>(this.getCoordinates().getKey() + normalizedVector.getX(),
                    this.getCoordinates().getValue() + normalizedVector.getY()));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Function that operates on states of vehicle.
     * @param deltaT - delta time.
     * @throws InterruptedException - throws InterruptedException.
     */
    public synchronized void move(double deltaT) throws InterruptedException {
        switch (this.state) {
            case traveling: {
                if (moveTo(this, deltaT, getNode(getCoordinates(), getTravelRoute()))) {
                    setState(States.waiting);
                }
            }
            case waiting: {
//                if (){
//                    setState(States.arriving);
//                }
            }
            case arriving: {
                setState(States.traveling);
            }
        }
    }

    /**
     * Runs thread.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(100) * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                move(0.0000001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
