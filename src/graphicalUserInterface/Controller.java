package graphicalUserInterface;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.Aircraft;
import vehicles.MilitaryAircraft;
import vehicles.PassengerAircraft;
import vehicles.Ship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Controller {

    private List<Airport> listOfAirports = new ArrayList<Airport>();
    private List<Aircraft> listOfAircrafts = new ArrayList<Aircraft>();
    private List<Ship> listOfShips = new ArrayList<Ship>();
    private List<Integer> listOfIds = new ArrayList<>();
    private List<TravelRoute> listOfTravelRoutes = new ArrayList<>();

    public Controller() {
        initializeAirports();
    }

    public List<Integer> getListOfIds() {
        return listOfIds;
    }

    public List<Airport> getListOfAirports() {
        return listOfAirports;
    }

    public List<Aircraft> getListOfAircrafts() {
        return listOfAircrafts;
    }

    public List<Ship> getListOfShips() {
        return listOfShips;
    }

    public List<TravelRoute> getListOfTravelRoutes() {
        return listOfTravelRoutes;
    }

    public int addId(){
        int id = getListOfIds().get(listOfIds.size() - 1) + 1;
        listOfIds.add(id);
        return id;
    }

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

    public void addTravelRouteToListOfTravelRoutes(TravelRoute travelRoute){
        listOfTravelRoutes.add(travelRoute);
    }

    public List<Airport> getListOfCivilianAirports(){
        List<Airport> listOfCivilianAirports = new ArrayList<>();
        for (Airport item: listOfAirports){
            if (item instanceof CivilianAirport){
                listOfCivilianAirports.add(item);
            }
        }
        return listOfCivilianAirports;
    }
    public List<Airport> getListOfMilitaryAirports(){
        List<Airport> listOfMilitaryAirports = new ArrayList<>();
        for (Airport item: listOfAirports){
            if (item instanceof MilitaryAirport){
                listOfMilitaryAirports.add(item);
            }
        }
        return listOfMilitaryAirports;
    }

    private void createCivilianAirport(String name, int x, int y){
        Airport airport = new CivilianAirport(name, new Pair<>(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y)));
        addAirportToListOfAirports(airport);
    }

    private void createMilitaryAirport(String name, int x, int y){
        Airport airport = new MilitaryAirport(name, new Pair<>(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y)));
        addAirportToListOfAirports(airport);
    }

    private List<List<String>> readAirportList(){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/airports.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private void initializeAirports(){
        List<List<String>> records = readAirportList();
        for (List<String> list : records){
            if (list.get(0).equals("C")) {
                createCivilianAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }
            if (list.get(0).equals("M")) {
                createMilitaryAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }
        }
    }
}
