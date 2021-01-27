package vehicles;

import com.sun.javafx.geom.Vec2d;
import enumerates.FirmNames;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.MyVector;
import other.Node;
import other.TravelRoute;

import java.util.Vector;

public class PassengerShip extends Ship {

    private FirmNames firmName;
    private int maximumAmountOfPassengers;
    private IntegerProperty currentAmountOfPassengers = new SimpleIntegerProperty(this, "currentAmountOfPassengers");

    public PassengerShip(int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers,
                         FirmNames firmName, int speed, TravelRoute travelRoute) {
        super(id, speed, travelRoute);
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
        this.currentAmountOfPassengers = currentAmountOfPassengers;
        this.firmName = firmName;
    }

    public PassengerShip() {
        super();
    }

    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers.get();
    }

    public IntegerProperty currentAmountOfPassengersProperty() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers.set(currentAmountOfPassengers);
    }

    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
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
                "Firm name: " + firmName + "\n" +
                "Maximum amount of passengers: " + maximumAmountOfPassengers + "\n" +
                "Current amount of passengers: " + currentAmountOfPassengers;
    }

    @Override
    public String toString() {
        return this.getFirmName() + " Ship " + this.getId();
    }
}
