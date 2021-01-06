package graphicalUserInterface;


import enumerates.FirmNames;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.Node;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.Aircraft;
import vehicles.PassengerShip;
import vehicles.Ship;
import vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

public class Controller {

    private List<Airport> listOfAirports = new ArrayList<Airport>();
    private List<Aircraft> listOfAircrafts = new ArrayList<Aircraft>();
    private List<Ship> listOfShips = new ArrayList<Ship>();
    private List<Integer> listOfIds = new ArrayList<>();
    private List<TravelRoute> listOfTravelRoutes = new ArrayList<>();
    public List<Thread> listOfThreads = new ArrayList<>();

    public Controller() throws InterruptedException {
        listOfIds.add(0);
        initializeAirports();
        initializeRoutes();
        initializeEntities();
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

    public List<Thread> getListOfThreads() {
        return listOfThreads;
    }

    public int assignId() {
        int id = getListOfIds().get(listOfIds.size() - 1) + 1;
        listOfIds.add(id);
        return id;
    }

    public void createAircraft() {
    }

    public PassengerShip createPassengerShip(String x, String y, String maximumAmountOfPassengers, String currentAmountOfPassengers, String firmName, String velocity, String travelRoute) throws InterruptedException {
        PassengerShip passengerShip = new PassengerShip(
                new Pair<>(new SimpleDoubleProperty(Integer.parseInt(x)), new SimpleDoubleProperty(Integer.parseInt(y))),
                assignId(), Integer.parseInt(maximumAmountOfPassengers), new SimpleIntegerProperty(Integer.parseInt(currentAmountOfPassengers)),
                FirmNames.valueOf(firmName), Integer.parseInt(velocity), getListOfTravelRoutes().get(Integer.parseInt(travelRoute)));
        addShipToListOfShips(passengerShip);
        Thread thread = new Thread(new MyThread(passengerShip));
        addThreadToListOfThreads(thread);
        return passengerShip;
    }

    public void destroyAircraft() {
    }

    public void destroyShip() {
    }

    public void addAircraftToListOfAircrafts(Aircraft aircraft) {
        listOfAircrafts.add(aircraft);
    }

    public void addShipToListOfShips(Ship ship) {
        listOfShips.add(ship);
    }

    public void removeAircraftFromListOfAircrafts(Aircraft aircraft) {
        listOfAircrafts.remove(aircraft);
    }

    public void removeShipFromListOfShips(Ship ship) {
        listOfShips.remove(ship);
    }

    public void addAirportToListOfAirports(Airport airport) {
        listOfAirports.add(airport);
    }

    public void removeAirportFromListOfAirports(Airport airport) {
        listOfAirports.remove(airport);
    }

    public void addTravelRouteToListOfTravelRoutes(TravelRoute travelRoute) {
        listOfTravelRoutes.add(travelRoute);
    }

    private void addThreadToListOfThreads(Thread thread) {
        listOfThreads.add(thread);
    }

    public List<Airport> getListOfCivilianAirports() {
        List<Airport> listOfCivilianAirports = new ArrayList<>();
        for (Airport item : listOfAirports) {
            if (item instanceof CivilianAirport) {
                listOfCivilianAirports.add(item);
            }
        }
        return listOfCivilianAirports;
    }

    public List<Airport> getListOfMilitaryAirports() {
        List<Airport> listOfMilitaryAirports = new ArrayList<>();
        for (Airport item : listOfAirports) {
            if (item instanceof MilitaryAirport) {
                listOfMilitaryAirports.add(item);
            }
        }
        return listOfMilitaryAirports;
    }

    private void createCivilianAirport(String name, int x, int y) {
        Airport airport = new CivilianAirport(name, new Pair<>(new SimpleDoubleProperty(x), new SimpleDoubleProperty(y)));
        addAirportToListOfAirports(airport);
    }

    private void createMilitaryAirport(String name, int x, int y) {
        Airport airport = new MilitaryAirport(name, new Pair<>(new SimpleDoubleProperty(x), new SimpleDoubleProperty(y)));
        addAirportToListOfAirports(airport);
    }

    private List<List<String>> readCSVFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

    private void initializeRoutes() {
        List<List<String>> records = readCSVFile("resources/routes.csv");
        for (List<String> list : records) {
            TravelRoute travelRoute = new TravelRoute();
            if (list.get(0).equals("A")) {
                for (int index = 1; index < list.size(); index++) {
                    travelRoute.addCheckpointToList(returnAirportNode(list.get(index)));
                }
            }
            if (list.get(0).equals("S")) {
                for (int index = 1; index < list.size(); index += 2) {
                        travelRoute.addCheckpointToList(
                                new Node(new Pair<>(new SimpleDoubleProperty(Integer.parseInt(list.get(index))),
                                        new SimpleDoubleProperty(Integer.parseInt(list.get(index + 1))))));
                }
            }
            addTravelRouteToListOfTravelRoutes(travelRoute);
        }
    }

    private Node returnAirportNode(String airportName) {
        for (Airport airport : getListOfAirports()) {
            if (airport.toString().equals(airportName + " Airport")) {
                return airport;
            }
        }
        return null;
    }

    private void initializeAirports() {
        List<List<String>> records = readCSVFile("resources/airports.csv");
        for (List<String> list : records) {
            if (list.get(0).equals("C")) {
                createCivilianAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }
            if (list.get(0).equals("M")) {
                createMilitaryAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }

        }
    }

    private void initializeEntities() throws InterruptedException {
        List<List<String>> records = readCSVFile("resources/entities.csv");
        for (List<String> list : records) {
            if (list.get(0).equals("PS")) {
                createPassengerShip(list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
            }
        }
    }


    private static class MyThread implements Runnable {

        private Vehicle vehicle;

        public MyThread(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        public void run() {
            AnimationTimer animationTimer = new AnimationTimer() {
                final double deltaT = 0.03;
                Node nextCheckpoint = new Node();

                @Override
                public void handle(long now) {
                    nextCheckpoint = vehicle.getPositionOnRoute(nextCheckpoint);
                    vehicle.moveTo(vehicle, deltaT, nextCheckpoint);
                }
            };
            animationTimer.start();

//            if (vehicle instanceof Ship) {
//                ((Ship) vehicle).shuttle();
//            }
        }
    }


}
