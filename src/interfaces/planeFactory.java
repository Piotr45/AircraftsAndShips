package interfaces;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import enumerates.typesOfArms;
import vehicles.Aircraft;

public interface planeFactory {

    Aircraft createAircraft(Pair<DoubleProperty, DoubleProperty> coordinates, int id, IntegerProperty maximumAmountOfFuel, IntegerProperty currentAmountOfFuel, int amountOfStaff, Airport lastVisitedAirport, Airport nextAirport, TravelRoute travelRoute, typesOfArms typeOfArms);

}
