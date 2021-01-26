package vehicles;

import enumerates.States;
import graphicalUserInterface.Main;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.util.Pair;
import other.Node;
import other.SeaNode;
import other.TravelRoute;

public abstract class Ship extends Vehicle {

    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");
    private SeaNode previousNode;
    private SeaNode currentNode;
    private SeaNode destinationNode;

    public Ship(int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers,
                int velocity, TravelRoute travelRoute) {
        super(id, velocity, travelRoute);
        this.setState(States.traveling);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
        this.currentNode = (SeaNode) getNode(getCoordinates(), travelRoute);
        newDestinationBasedOnCurrent();
    }

    public Ship() {
    }

    public SeaNode getCurrentNode() {
        return currentNode;
    }

    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers.get();
    }

    public IntegerProperty currentAmountOfPassengersProperty() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers.set(currentAmountOfPassengers);
    }

    @Override
    public void newDestinationBasedOnCurrent() {
        int index = this.getTravelRoute().getCheckpoints().indexOf(this.currentNode) + 1;
        if (index == this.getTravelRoute().getCheckpoints().size()) {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(0);
        } else {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(index);
        }
    }

    @Override
    public void updateNodes() {
        previousNode = (SeaNode) getNode(currentNode.getCoordinates(), this.getTravelRoute());
        currentNode = (SeaNode) getNode(destinationNode.getCoordinates(), this.getTravelRoute());
        newDestinationBasedOnCurrent();
    }

    @Override
    public synchronized void move(double deltaT) throws InterruptedException {
        switch (this.getState()) {
            case traveling:
                if (moveTo(this, deltaT, destinationNode)) {
                    this.setState(States.waiting);
                }
                break;
            case waiting:
                if (destinationNode.isNodeFree()) {
                    updateNodes();
                    setState(States.arriving);
                } else {
                    //Thread.sleep(250);
                    System.out.println("Failed");
                }
                break;
            case arriving:
                if (currentNode.getConnections().size() > 2) {
                    currentNode.occupy();
                }
                currentNode.freeNode();
                setState(States.traveling);
                break;
        }
        //this.wait();
    }

    @Override
    public void run() {
            super.run();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers + "\n" +
                "Velocity of ship: " + getVelocity();
    }

    @Override
    public String toString() {
        return "Ship " + this.getId();
    }
}
