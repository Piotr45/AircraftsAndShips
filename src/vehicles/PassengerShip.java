package vehicles;

import com.sun.javafx.geom.Vec2d;
import enumerates.FirmNames;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.TravelRoute;

import java.util.Vector;

public class PassengerShip extends Ship {

    private FirmNames firmName;

    public PassengerShip(Pair<DoubleProperty, DoubleProperty> coordinates, int id, int maximumAmountOfPassengers,
                         IntegerProperty currentAmountOfPassengers, FirmNames firmName, int speed, TravelRoute travelRoute) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers, speed, travelRoute);
        this.firmName = firmName;
    }

    public PassengerShip() {
        super();
    }

    public FirmNames getFirmName() {
        return firmName;
    }

    public void setFirmName(FirmNames firmName) {
        this.firmName = firmName;
    }




//    @Override
//    public void shuttle() {
//        System.out.println("X: " + this.getCoordinates().getKey().get() + " Y: " + this.getCoordinates().getValue().get());
//
//        MyVector normal = getTravelRoute().findNormal(this);
//        this.getCoordinates().getKey().set(this.getCoordinates().getKey().get() + (int) normal.getX());
//        this.getCoordinates().getValue().set(this.getCoordinates().getValue().get() + (int) normal.getY());
//
//        System.out.println("X: " + this.getCoordinates().getKey().get() + " Y: " + this.getCoordinates().getValue().get());
//    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Firm name: " + firmName + "\n";
    }

    @Override
    public String toString() {
        return this.getFirmName() + " Ship " + this.getId();
    }
}
