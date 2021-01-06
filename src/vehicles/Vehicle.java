package vehicles;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.TravelRoute;

public abstract class Vehicle extends Node {

    private int id;
    private int state;
    private int velocity;
    private TravelRoute travelRoute;
    private Node previousNode;

    public Vehicle(Pair<DoubleProperty, DoubleProperty> coordinates, int id, int velocity, TravelRoute travelRoute) {
        super(coordinates);
        this.id = id;
        this.state = 0;
        this.velocity = velocity;
        this.travelRoute = travelRoute;
    }

    public Vehicle(String name) {
        super(name);
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setState(int state) {
        this.state = state;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    private Boolean areCoordinatesTheSame(Pair<DoubleProperty, DoubleProperty> pair1,
                                          Pair<DoubleProperty, DoubleProperty> pair2) {
        return pair1.getKey().get() == pair2.getKey().get() && pair1.getValue().get() == pair2.getValue().get();
    }

    public Node getPositionOnRoute(Node oldCheckpoint) {
        for (Node node : getTravelRoute().getCheckpoints()) {
            if (areCoordinatesTheSame(this.getCoordinates(), node.getCoordinates())) {
//                System.out.println("\t\tIn if statement: 0");
                System.out.println(node);
                if (getTravelRoute().getCheckpoints().indexOf(node) == getTravelRoute().getCheckpoints().size() - 1) {
                    //System.out.println("\t\tIn if statement: 1");
                    return getTravelRoute().getCheckpoints().get(0);
                } else {
                    //System.out.println("\t\tIn if statement: 2");
                    Node nextCheckpoint = getTravelRoute().getCheckpoints().get(getTravelRoute().getCheckpoints().lastIndexOf(previousNode) + 1);
                    previousNode = node;
                    return nextCheckpoint;
                }
            }
        }
        return oldCheckpoint;
    }

    private void setCoordinates(double x, double y) {
        this.getCoordinates().getKey().set(x);
        this.getCoordinates().getValue().set(y);
    }

    public Boolean moveTo(Vehicle vehicle, double deltaT, Node nextCheckpoint) {
        //System.out.println("Next checkpoint:\t" + nextCheckpoint.getCoordinates());
        MyVector vector = new MyVector(vehicle.getCoordinates(), nextCheckpoint.getCoordinates());
        MyVector normalizedVector = new MyVector(vector);
        normalizedVector.normalise();
        normalizedVector.multiplicationNormalizedVector(this.getVelocity() * deltaT);
        normalizedVector.recalculateMagnitude();

        //System.out.println("Vehicle coords: " + this.getCoordinates());
        if (normalizedVector.getMagnitude() < vector.getMagnitude()) {
            //System.out.println("Return false");
            setCoordinates(this.getCoordinates().getKey().get() + normalizedVector.getX(),
                    this.getCoordinates().getValue().get() + normalizedVector.getY());
            //System.out.println("Vehicle coords after: " + this.getCoordinates());
            return false;
        } else {
            //System.out.println("Return true");
            setCoordinates(nextCheckpoint.getCoordinates().getKey().get(),
                    nextCheckpoint.getCoordinates().getValue().get());
            return true;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
