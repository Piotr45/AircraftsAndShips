package vehicles;

import enumerates.States;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
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
        //System.out.println(currentNode.getCoordinates() + " " + destinationNode.getCoordinates());
    }

    public Ship() {
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

    public void shuttle() {
    }

    private void newDestinationBasedOnCurrent(){
        int index = this.getTravelRoute().getCheckpoints().indexOf(this.currentNode) + 1;
        if (index == this.getTravelRoute().getCheckpoints().size() - 1) {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(0);
        } else {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(index);
        }
    }

    @Override
    public void updateNodes() {
        if (this.areCoordinatesTheSame(this.getCoordinates(), destinationNode.getCoordinates())) {
            previousNode = (SeaNode) getNode(currentNode.getCoordinates(), this.getTravelRoute());
            currentNode = (SeaNode) getNode(destinationNode.getCoordinates(), this.getTravelRoute());
            newDestinationBasedOnCurrent();
        }
    }

    @Override
    public void move(double deltaT){
        try {
            System.out.println(this.getState());
            System.out.println("Previous:\t" + previousNode.getCoordinates() + "\n" +
                    "Current:\t" + currentNode.getCoordinates() + "\n" + "Destination:\t" +
                    destinationNode.getCoordinates() + "\nOur Position:\t" +this.getCoordinates());
        } catch (Exception e) {
            System.out.println("Current:\t" + currentNode.getCoordinates() + "\nDestination:\t" + destinationNode.getCoordinates() + "\nOur Position:\t" +this.getCoordinates());
        }
        System.out.println();
        switch (this.getState()) {
            case traveling:
                if (moveTo(this, deltaT, destinationNode)){
                    this.setState(States.waiting);
                }
                break;
            case waiting:
                if (destinationNode.isNodeFree()){
                    setCoordinates(destinationNode.getCoordinates().getKey().get(),
                            destinationNode.getCoordinates().getValue().get());
                    updateNodes();
                    setState(States.arriving);
                } else {
                    //System.out.println("Failed");
                }
                break;
            case arriving:
                currentNode.occupy();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                currentNode.freeNode();
                setState(States.traveling);
                break;
        }
    }

    @Override
    public void run() {
        while(true) {
            move(0.0005);
        }
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
