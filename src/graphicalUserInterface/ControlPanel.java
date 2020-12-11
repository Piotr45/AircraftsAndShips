package graphicalUserInterface;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ports.Airport;
import vehicles.Aircraft;
import vehicles.Ship;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ControlPanel {

    // Variables

    private List<Airport> listOfAirports;
    private List<Aircraft> listOfAircrafts;
    private List<Ship> listOfShips;

    // Getters and Setters

    public List<Airport> getListOfAirports() {
        return listOfAirports;
    }

    public List<Aircraft> getListOfAircrafts() {
        return listOfAircrafts;
    }

    public List<Ship> getListOfShips() {
        return listOfShips;
    }

    // Methods

    public void createAircraft(){}

    public void createShip(){}

    public void destroyAircraft(){}

    public void destroyShip(){}

    public void addAircraftToListOfAircrafts(){}

    public void addShipToListOfShips(){}

    public void removeAircraftFromListOfAircrafts(){}

    public void removeShipFromListOfShips(){}



}
