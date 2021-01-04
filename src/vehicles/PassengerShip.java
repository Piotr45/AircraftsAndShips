package vehicles;

import enumerates.FirmNames;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.Node;
import other.TravelRoute;

public class PassengerShip extends Ship {

    private FirmNames firmName;
    private TravelRoute travelRoute;

    public PassengerShip(Pair<IntegerProperty, IntegerProperty> coordinates, int id, int maximumAmountOfPassengers,
                         IntegerProperty currentAmountOfPassengers, FirmNames firmName, int speed, TravelRoute travelRoute) {
        super(coordinates, id, maximumAmountOfPassengers, currentAmountOfPassengers, speed);
        this.firmName = firmName;
        this.travelRoute = travelRoute;
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
    public void shuttle() {
        int i = 0;
        while (i < 30000){
            travelRoute.getCheckpoints().forEach(coordinates -> {
                this.setCoordinates(coordinates);
                System.out.println(this.getCoordinates());
            });
            i++;
        }
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
