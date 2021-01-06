package vehicles;

import enumerates.typesOfArms;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import other.TravelRoute;
import ports.Airport;
import javafx.util.Pair;
import java.util.List;

public class MilitaryAircraft extends Aircraft{

    private typesOfArms typeOfArms;

    public MilitaryAircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel,
                            IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport,
                            Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms) {
        super(coordinates, id, maximumAmountOfFuel, currentAmountOfFuel, amountOfStaff, lastVisitedAirport, nextAirport, travelRoute);
        this.typeOfArms = typeOfArms;
    }

    public MilitaryAircraft() {
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Type of arm: " + typeOfArms.toString();
    }

    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    public void setTypeOfArms(typesOfArms typeOfArms) {
        this.typeOfArms = typeOfArms;
    }

    @Override
    public String toString() {
        return "MilitaryAircraft " + this.getId();
    }
}
