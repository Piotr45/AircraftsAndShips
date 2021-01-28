package other;

import javafx.beans.property.DoubleProperty;
import javafx.util.Pair;
import vehicles.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * SeaNode class represents node that we will use to create points on sea.
 */
public class SeaNode extends Node {

    private Semaphore semaphore = new Semaphore(1);
    private List<SeaNode> connections = new ArrayList<>();

    /**
     * Sea node class constructor with one param.
     * @param coordinates - original coordinates of an node.
     * @throws InterruptedException - this constructor may throw InterruptedException.
     */
    public SeaNode(Pair<Double, Double> coordinates) throws InterruptedException {
        super(coordinates);
    }

    /**
     * This method tries to acquire semaphore built in this class.
     * @return - returns true if semaphore have permits, else return false.
     */
    public Boolean isNodeFree() {
        if (this.semaphore.tryAcquire()) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if semaphore have one permit available.
     * @return - returns true if semaphore have one permit, else return false.
     */
    public Boolean isNodeFree2() {
        if (this.semaphore.availablePermits() == 1) {
            return true;
        }
        return false;
    }

    /**
     * Releases semaphore.
     */
    public void freeNode() {
        this.semaphore.release();
    }

    /**
     * Thread sleeps two seconds and after that releases semaphore.
     */
    public void occupy() {
        try {
            //this.semaphore.acquire(1);
            Thread.sleep(2000);
            //System.out.println("Time is over");
            freeNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets semaphore.
     * @return - returns semaphore.
     */
    public Semaphore getSemaphore() {
        return semaphore;
    }

    /**
     * Gets connections of an node.
     * @return - returns list of SeaNodes connected to this node.
     */
    public List<SeaNode> getConnections() {
        return connections;
    }

    /**
     * Adds node to list of connected nodes.
     * @param seaNode - SeaNode object that will be added to connections.
     */
    public void addConnection(SeaNode seaNode) {
        connections.add(seaNode);
    }

//    public void assignPermits(int number) throws InterruptedException {
//        semaphore = new Semaphore(number);
//    }
}
