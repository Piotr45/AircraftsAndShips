package vehicles;

import enumerates.States;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.concurrent.Task;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.SeaNode;
import other.TravelRoute;

public abstract class Vehicle extends Node implements Runnable{

    private int id;
    private States state;
    private int velocity;
    private TravelRoute travelRoute;
    private Node previousNode;
    private Node currentNode;
    private Node destinationNode;
    private Thread thread;

    public Vehicle(Pair<DoubleProperty, DoubleProperty> coordinates, int id, int velocity, TravelRoute travelRoute) {
        super(coordinates);
        this.id = id;
        this.state = States.traveling;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    public Vehicle(int id, int velocity, TravelRoute travelRoute) {
        super(travelRoute.getCheckpoints().get(0).getCoordinates(), 1);
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

    public Boolean areCoordinatesTheSame(Pair<DoubleProperty, DoubleProperty> pair1,
                                         Pair<DoubleProperty, DoubleProperty> pair2) {
        return pair1.getKey().get() == pair2.getKey().get() && pair1.getValue().get() == pair2.getValue().get();
    }

    public Node getNode(Pair<DoubleProperty, DoubleProperty> pair, TravelRoute travelRoute) {
        for (Node node : travelRoute.getCheckpoints()) {
            if (areCoordinatesTheSame(pair, node.getCoordinates())) {
                return node;
            }
        }
        return null;
    }

    public void updateNodes() {
    }

    public void setCoordinates(double x, double y) {
        this.getCoordinates().getKey().set(x);
        this.getCoordinates().getValue().set(y);
    }

    public Boolean moveTo(Vehicle vehicle, double deltaT, Node nextCheckpoint) {
        MyVector vector = new MyVector(vehicle.getCoordinates(), nextCheckpoint.getCoordinates());
        MyVector normalizedVector = new MyVector(vector);
        normalizedVector.normalise();
        normalizedVector.multiplicationNormalizedVector(this.getVelocity() * deltaT);
        normalizedVector.recalculateMagnitude();

        if (normalizedVector.getMagnitude() + 2 < vector.getMagnitude()) {
            setCoordinates(this.getCoordinates().getKey().get() + normalizedVector.getX(),
                    this.getCoordinates().getValue().get() + normalizedVector.getY());
            return false;
        } else {
            return true;
        }
    }

    public void move(double deltaT) throws InterruptedException {
        switch (this.state) {
            case traveling: {
                if (moveTo(this, deltaT, getNode(getCoordinates(), getTravelRoute()))){
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
