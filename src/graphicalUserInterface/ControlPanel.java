package graphicalUserInterface;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ports.Airport;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import vehicles.PassengerAircraft;
import vehicles.Ship;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ControlPanel {

    // Variables

    private List<Airport> listOfAirports = new ArrayList<Airport>();
    private List<Aircraft> listOfAircrafts = new ArrayList<Aircraft>();
    private List<Ship> listOfShips = new ArrayList<Ship>();

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

    public void addAircraftToListOfAircrafts(Aircraft aircraft){ listOfAircrafts.add(aircraft); }

    public void addShipToListOfShips(Ship ship){ listOfShips.add(ship); }

    public void removeAircraftFromListOfAircrafts(Aircraft aircraft){ listOfAircrafts.remove(aircraft); }

    public void removeShipFromListOfShips(Ship ship){ listOfShips.remove(ship); }

    public void addAirportToListOfAirports(Airport airport){
        listOfAirports.add(airport);
    }

    public void removeAirportFromListOfAirports(Airport airport){
        listOfAirports.remove(airport);
    }

}
