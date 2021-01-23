package vehicles;

import enumerates.States;
import javafx.concurrent.Task;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.SeaNode;
import other.TravelRoute;

public abstract class Vehicle extends Node implements Runnable {

    private int id;
    private States state;
    private int velocity;
    private TravelRoute travelRoute;
    private Node previousNode;
    private Node currentNode;
    private Node destinationNode;
    private Thread thread;

    public Vehicle(Pair<Double, Double> coordinates, int id, int velocity, TravelRoute travelRoute) {
        super(coordinates);
        this.id = id;
        this.state = States.traveling;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    public Vehicle(int id, int velocity, TravelRoute travelRoute) {
        super(travelRoute.findRandomCheckpoint().getCoordinates(), 1);
        this.id = id;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    public Vehicle(String name) {
        super(name);
    }

    public Vehicle() {
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread() {
        this.thread = new Thread(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public States getState() {
        return state;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setState(States state) {
        this.state = state;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Boolean areCoordinatesTheSame(Pair<Double, Double> pair1,
                                         Pair<Double, Double> pair2) {
        return pair1.getKey() == pair2.getKey() && pair1.getValue() == pair2.getValue();
    }

    public Node getNode(Pair<Double, Double> pair, TravelRoute travelRoute) {
        for (Node node : travelRoute.getCheckpoints()) {
            if (areCoordinatesTheSame(pair, node.getCoordinates())) {
                return node;
            }
        }
        return null;
    }

    public void updateNodes() {
    }

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

    public void move(double deltaT) throws InterruptedException {
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

    @Override
    public void run() {
        try {
            move(0.000001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
