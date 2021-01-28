package other;

import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class TravelRoute contains list of checkpoints e.g. Airports.
 */
public class TravelRoute {

    private List<Node> checkpoints = new ArrayList<>();
    private Random random = new Random();
    private int id;

    /**
     * Empty constructor of this class.
     */
    public TravelRoute() {
    }

    /**
     * Sets unique ID of route.
     * @param id - unique int called identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets list of checkpoints.
     * @return - returns list of checkpoints.
     */
    public List<Node> getCheckpoints() {
        return checkpoints;
    }

    /**
     * Adds checkpoint to list.
     * @param node - Node object that we will add to list of checkpoints.
     */
    public void addCheckpointToList(Node node) {
        this.checkpoints.add(node);
    }

    /**
     * Method finds checkpoint that is not occupied. This function is used to respawn ships on sea.
     * @return - returns node.
     */
    public SeaNode findRandomCheckpoint(){
        SeaNode randomNode;
        do {
            randomNode = (SeaNode) checkpoints.get(random.nextInt(checkpoints.size()-1));
        }
        while (!randomNode.isNodeFree2());
        return randomNode;
    }

    /**
     * This method generates name of travel route.
     * @return - returns string
     */
    @Override
    public String toString() {
        return "Travel route " + Integer.toString(this.id);
    }
}
