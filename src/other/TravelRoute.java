package other;

import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import ports.Airport;
import vehicles.PassengerAircraft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TravelRoute {

    private List<Pair<IntegerProperty, IntegerProperty>> checkpoints = new LinkedList<>();

    public TravelRoute(){}

    public List<Pair<IntegerProperty, IntegerProperty>> getCheckpoints() {
        return checkpoints;
    }

    public void addCheckpointToList(Pair<IntegerProperty, IntegerProperty> pair){
        checkpoints.add(pair);
    }

}
