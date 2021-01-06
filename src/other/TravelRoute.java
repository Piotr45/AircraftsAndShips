package other;

import vehicles.Vehicle;

import java.util.LinkedList;
import java.util.List;

public class TravelRoute {

    private List<Node> checkpoints = new LinkedList<>();
    private Node currentCheckpoint;
    private Node nextCheckpoint;

    public TravelRoute() {
    }

    public List<Node> getCheckpoints() {
        return checkpoints;
    }

    public void addCheckpointToList(Node pair) {
        this.checkpoints.add(pair);
    }



//    private MyVector vectorStuff(Vehicle vehicle) {
//        getPositionOnRoute(vehicle);
//        //System.out.println(currentCheckpoint.getCoordinates() + " " + nextCheckpoint.getCoordinates());
//        Node newGoal = nextCheckpoint;
////        Node oldGoal = currentCheckpoint;
////        MyVector oldVector = new MyVector(vehicle.getCoordinates(), oldGoal.getCoordinates());
//        MyVector newVector = new MyVector(newGoal.getCoordinates(), vehicle.getCoordinates());
//        return newVector;
//    }

//    public MyVector findNormal(Vehicle vehicle, double deltaT) {
//        MyVector normal = new MyVector(vectorStuff(vehicle));
//        normal.normalise();
//        normal.setX(normal.getX() * vehicle.getVelocity() * deltaT);
//        normal.setY(normal.getY() * vehicle.getVelocity() * deltaT);
//        normal.recalculateMagnitude();
//        return normal;
//    }

    private boolean checkIfInRange(double bound, double x) {
        if (bound < 0) {
            return x > bound && x < (bound * (-1));
        }
        return x < bound && x > (bound * (-1));
    }




//    public void move(Vehicle vehicle, double deltaT) {
//        switch (vehicle.getState()) {
//            case 0:
//                MyVector normal = findNormal(vehicle, deltaT);
//                System.out.println(normal.getX() + " " + normal.getY());
//                vehicle.getCoordinates().getKey().set(vehicle.getCoordinates().getKey().get() + normal.getX());
//                vehicle.getCoordinates().getValue().set(vehicle.getCoordinates().getValue().get() + normal.getY());
//                vehicle.setState(2);
//                break;
//            case 1:
//                break;
//            case 2:
//                normal = findNormal(vehicle, deltaT);
//                System.out.println(normal.getX() + " " + normal.getY());
//                if (checkIfInRange(normal.getX(), nextCheckpoint.getCoordinates().getKey().get() - vehicle.getCoordinates().getKey().get()) &&
//                        checkIfInRange(normal.getY(), nextCheckpoint.getCoordinates().getValue().get() - vehicle.getCoordinates().getValue().get())) {
//
//                    vehicle.getCoordinates().getKey().set(nextCheckpoint.getCoordinates().getKey().get());
//                    vehicle.getCoordinates().getKey().set(nextCheckpoint.getCoordinates().getValue().get());
//                    System.out.println("Returns to state 0");
//                    vehicle.setState(0);
//                } else {
//                    vehicle.getCoordinates().getKey().set(vehicle.getCoordinates().getKey().get() + normal.getX());
//                    vehicle.getCoordinates().getValue().set(vehicle.getCoordinates().getValue().get() + normal.getY());
//                }
//                System.out.println(normal.getX() + " " + normal.getY());
//                break;
//        }
//    }
}
