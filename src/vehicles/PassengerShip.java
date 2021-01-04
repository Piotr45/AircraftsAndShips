package vehicles;

import enumerates.FirmNames;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;

public class PassengerShip extends Ship {

    private FirmNames firmName;

    public PassengerShip(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers, IntegerProperty currentAmountOfPassengers, FirmNames firmName, int speed) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers, speed);
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
