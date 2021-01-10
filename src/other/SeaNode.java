package other;

import javafx.beans.property.DoubleProperty;
import javafx.util.Pair;
import vehicles.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SeaNode extends Node{

    private Semaphore semaphore = new Semaphore(1000);
    private List<SeaNode> connections = new ArrayList<>();
    private List<Ship> queue = new ArrayList<>();

    public SeaNode(Pair<DoubleProperty, DoubleProperty> coordinates) throws InterruptedException {
        super(coordinates);
    }

    public Boolean isNodeFree() {
        if (this.semaphore.tryAcquire()) {
            return true;
        }
        return false;
    }

    public void freeNode() {
        //System.out.println("\t\t" + this.getCoordinates());
        this.semaphore.release();
    }

    public void occupy()  {
        try {
            //System.out.println("\t\t" + this.getCoordinates());
            this.semaphore.acquire(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public List<SeaNode> getConnections() {
        return connections;
    }

    public void addConnection(SeaNode seaNode) {
        connections.add(seaNode);
    }

    public void addShipToQueue(Ship ship) {
        queue.add(ship);
    }

    public void removeShipFromQueue(Ship ship) {
        queue.remove(ship);
    }

    public void assignPermits(int number) throws InterruptedException {
        semaphore.acquire(number);
    }
}
