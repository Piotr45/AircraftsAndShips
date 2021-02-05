package vehicles;

import enumerates.States;
import other.SeaNode;
import other.TravelRoute;

/**
 * This class describes ship object.
 */
public abstract class Ship extends Vehicle {

    private SeaNode previousNode;
    private SeaNode currentNode;
    private SeaNode destinationNode;

    /**
     * Ship class constructor with three parameters.
     * @param id - unique identifier of vehicle.
     * @param velocity - velocity of ship.
     * @param travelRoute - travel route of ship.
     */
    public Ship(int id, int velocity, TravelRoute travelRoute) {
        super(id, velocity, travelRoute);
        this.setState(States.traveling);
        this.currentNode = (SeaNode) getNode(getCoordinates(), travelRoute);
        newDestinationBasedOnCurrent();
    }

    /**
     * Empty constructor.
     */
    public Ship() {
    }

    /**
     * Gets current node.
     * @return - returns current node.
     */
    public SeaNode getCurrentNode() {
        return currentNode;
    }

    /**
     * This method finds destination based on current position of ship.
     */
    @Override
    public void newDestinationBasedOnCurrent() {
        int index = this.getTravelRoute().getCheckpoints().indexOf(this.currentNode) + 1;
        if (index == this.getTravelRoute().getCheckpoints().size()) {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(0);
        } else {
            this.destinationNode = (SeaNode) this.getTravelRoute().getCheckpoints().get(index);
        }
    }

    /**
     * Updates nodes - sets previous and current node, then finds new destination based on current position of ship.
     */
    @Override
    public void updateNodes() {
        previousNode = (SeaNode) getNode(currentNode.getCoordinates(), this.getTravelRoute());
        currentNode = (SeaNode) getNode(destinationNode.getCoordinates(), this.getTravelRoute());
        newDestinationBasedOnCurrent();
    }

    /**
     * Moves object.
     * @param deltaT - delta time.
     * @throws InterruptedException - can throw this error when we are trying to sleep thread.
     */
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
                    Thread.sleep(150);
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
        };
    }

    /**
     * Runs thread.
     */
    @Override
    public void run() {
            super.run();
    }

    /**
     * Adds velocity of ship to basic information about this object.
     * @return - returns information about ship as string.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Velocity of ship: " + getVelocity();
    }

    /**
     * Converts object name to string.
     * @return - returns string "Ship " + id of this object.
     */
    @Override
    public String toString() {
        return "Ship " + this.getId();
    }
}
