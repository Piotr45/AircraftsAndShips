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

    public PassengerShip(int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers,
                         FirmNames firmName, int speed, TravelRoute travelRoute) {
        super(id, maximumAmountOfPassengers, currentAmountOfPassengers, speed, travelRoute);
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
