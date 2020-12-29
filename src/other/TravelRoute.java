package other;

import ports.Airport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TravelRoute {

    private List<Airport> checkpoints = new LinkedList<>();

    public TravelRoute(){}

    public List<Airport> getCheckpoints() {
        return checkpoints;
    }

    public void addCheckpointToList(Airport airport){
        checkpoints.add(airport);
    }

}
