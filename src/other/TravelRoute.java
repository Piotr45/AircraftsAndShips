package other;

import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TravelRoute {

    private List<Node> checkpoints = new ArrayList<>();
    private Random random = new Random();

    public TravelRoute() {
    }

    public List<Node> getCheckpoints() {
        return checkpoints;
    }

    public void addCheckpointToList(Node node) {
        this.checkpoints.add(node);
    }

    public SeaNode findRandomCheckpoint(){
        SeaNode randomNode;
        do {
            randomNode = (SeaNode) checkpoints.get(random.nextInt(checkpoints.size()-1));
        }
        while (!randomNode.isNodeFree());
        return randomNode;
    }

}
