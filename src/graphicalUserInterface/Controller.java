package graphicalUserInterface;


import enumerates.FirmNames;
import enumerates.typesOfArms;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import other.Node;
import other.SeaNode;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In this class you can find lists with objects, functions that creates them and reads data form files with basic setup.
 */
public class Controller{

    private static List<Airport> listOfAirports = new ArrayList<Airport>();
    private static List<Aircraft> listOfAircrafts = new ArrayList<Aircraft>();
    private static List<Ship> listOfShips = new ArrayList<Ship>();
    private static List<Integer> listOfIds = new ArrayList<>();
    private static List<TravelRoute> listOfTravelRoutes = new ArrayList<>();
    private static List<TravelRoute> listOfSeaRoutes = new ArrayList<>();
    private static List<TravelRoute> listOfAirCivTravelRoutes = new ArrayList<>();
    private static List<TravelRoute> listOfAirMilTravelRoutes = new ArrayList<>();

    /**
     * Controller constructor.
     * In this constructor we are initializing all objects such as airports, travel routes and all entities.
     * @throws InterruptedException - thread related.
     */
    public Controller() throws InterruptedException {
        listOfIds.add(0);
        initializeAirports();
        initializeRoutes();
        initializeEntities();
    }

    /**
     * Gets list of sea routes.
     * @return - returns list of sea routes.
     */
    public List<TravelRoute> getListOfSeaRoutes() {
        return listOfSeaRoutes;
    }

    /**
     * Gets list of identifiers.
     * @return - returns identifiers of all objects.
     */
    public static List<Integer> getListOfIds() {
        return listOfIds;
    }

    /**
     * Gets list of all airports.
     * @return - returns list of all airports.
     */
    public static List<Airport> getListOfAirports() {
        return listOfAirports;
    }

    /**
     * Gets list of all aircraft's.
     * @return - returns list of aircraft's
     */
    public static List<Aircraft> getListOfAircrafts() {
        return listOfAircrafts;
    }

    /**
     * Gets list of ships.
     * @return - returns list of ships.
     */
    public static List<Ship> getListOfShips() {
        return listOfShips;
    }

    /**
     * Gets List of travel routes.
     * @return - returns list of all travel routes.
     */
    public static List<TravelRoute> getListOfTravelRoutes() {
        return listOfTravelRoutes;
    }

    /**
     * Gets list of air civilian travel routes.
     * @return - returns list of air civilian travel routes.
     */
    public static List<TravelRoute> getListOfAirCivTravelRoutes() { return listOfAirCivTravelRoutes; }

    /**
     * Gets list of air military travel routes.
     * @return - returns list of air military travel routes.
     */
    public static List<TravelRoute> getListOfAirMilTravelRoutes() {
        return listOfAirMilTravelRoutes;
    }

    /**
     * This method assigns unique identifier to object and adds it to list of Id's.
     * @return - returns id for new object.
     */
    public static int assignId() {
        int id = getListOfIds().get(listOfIds.size() - 1) + 1;
        listOfIds.add(id);
        return id;
    }

    /**
     * Adds ship to list of ship.
     * @param ship - new ship, that we want to add to list of ships.
     */
    public static void addShipToListOfShips(Ship ship) {
        listOfShips.add(ship);
    }

    /**
     * Removes Aircraft from list of aircraft's.
     * @param aircraft - aircraft that we want to remove.
     */
    public static void removeAircraftFromListOfAircrafts(Aircraft aircraft) {
        listOfAircrafts.remove(aircraft);
    }

    /**
     * Adds new airport to list of airports.
     * @param airport - airport, that we want to add to list of airports.
     */
    public void addAirportToListOfAirports(Airport airport) {
        listOfAirports.add(airport);
    }

    /**
     * This method adds new travel route to list of travel routes.
     * @param travelRoute - travel route that we want to add to list of travel routes.
     */
    public void addTravelRouteToListOfTravelRoutes(TravelRoute travelRoute) {
        listOfTravelRoutes.add(travelRoute);
    }

    /**
     * Gets list of civilian airport's.
     * @return - returns list of civilian airports.
     */
    public List<Airport> getListOfCivilianAirports() {
        List<Airport> listOfCivilianAirports = new ArrayList<>();
        for (Airport item : listOfAirports) {
            if (item instanceof CivilianAirport) {
                listOfCivilianAirports.add(item);
            }
        }
        return listOfCivilianAirports;
    }

    /**
     * Gets list of military airports.
     * @return
     */
    public List<Airport> getListOfMilitaryAirports() {
        List<Airport> listOfMilitaryAirports = new ArrayList<>();
        for (Airport item : listOfAirports) {
            if (item instanceof MilitaryAirport) {
                listOfMilitaryAirports.add(item);
            }
        }
        return listOfMilitaryAirports;
    }

    private void createCivilianAirport(String name, double x, double y) {
        Airport airport = new CivilianAirport(name, new Pair<>(x, y));
        addAirportToListOfAirports(airport);
    }

    private void createMilitaryAirport(String name, double x, double y) {
        Airport airport = new MilitaryAirport(name, new Pair<>(x, y));
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

    private int isThisNodeInMainTravelRouteReturnIndexOfThatNode(Pair<Double, Double> pair) throws NullPointerException, InterruptedException {
        try {
            List<Node> listOfNodes = getListOfTravelRoutes().get(1).getCheckpoints();

            SeaNode test = new SeaNode(pair);
            for (Node node : listOfNodes) {
                if (node.getCoordinates().getKey().equals(test.getCoordinates().getKey()) &&
                        node.getCoordinates().getValue().equals(test.getCoordinates().getValue())) {
                    return listOfNodes.indexOf(node);
                }
            }
        } catch (Exception ignore) {}
        return -1;
    }

    private void addSeaNodeToCheckpointList(TravelRoute travelRoute, String x, String y) throws InterruptedException {
        travelRoute.addCheckpointToList(new SeaNode(new Pair<>(Double.parseDouble(x), Double.parseDouble(y))));
    }

    private void addNewSeaRouteFromStringList(TravelRoute travelRoute, List<String> list) throws InterruptedException {
        for (int index = 1; index < list.size(); index += 2) {
            try {
                int x = isThisNodeInMainTravelRouteReturnIndexOfThatNode(new Pair<>(
                        Double.parseDouble(list.get(index)),Double.parseDouble(list.get(index + 1))));
                if (x != -1){
                    travelRoute.addCheckpointToList(getListOfTravelRoutes().get(1).getCheckpoints().get(x));
                } else {
                    addSeaNodeToCheckpointList(travelRoute, list.get(index), list.get(index + 1));
                }

            } catch (Exception e) {
                addSeaNodeToCheckpointList(travelRoute, list.get(index), list.get(index + 1));
            }

        }
        setConnections(travelRoute);
    }

    private void initializeRoutes() throws InterruptedException {
        int i = 0;
        List<List<String>> records = readCSVFile("src/routes.csv");
        for (List<String> list : records) {
            TravelRoute travelRoute = new TravelRoute();
            travelRoute.setId(i);
            if (list.get(0).equals("A")) {
                for (int index = 1; index < list.size(); index++) {
                    travelRoute.addCheckpointToList(returnAirportNode(list.get(index)));
                }
                if (travelRoute.getCheckpoints().get(0) instanceof CivilianAirport) {
                    listOfAirCivTravelRoutes.add(travelRoute);
                } else {
                    listOfAirMilTravelRoutes.add(travelRoute);
                }
            }
            if (list.get(0).equals("S")) {
                addNewSeaRouteFromStringList(travelRoute, list);
                listOfSeaRoutes.add(travelRoute);
            }
            addTravelRouteToListOfTravelRoutes(travelRoute);
            i++;
        }
    }

    private static Node returnAirportNode(String airportName) {
        for (Airport airport : getListOfAirports()) {
            if (airport.toString().equals(airportName + " Airport")) {
                return airport;
            }
            if (airport.toString().equals(airportName)) {
                return airport;
            }
        }
        return null;
    }

    private void initializeAirports() {
        List<List<String>> records = readCSVFile("src/airports.csv");
        for (List<String> list : records) {
            if (list.get(0).equals("C")) {
                createCivilianAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }
            if (list.get(0).equals("M")) {
                createMilitaryAirport(list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
            }

        }
    }

    /**
     * This method creates new passenger aircraft.
     * We are passing list of arguments. This list should contain:
     * - name of airport,
     * - maximum amount of passengers,
     * - current amount of passengers,
     * - number of travel route, that we are choosing.
     * After creating passenger aircraft, we are going to create thread and start it.
     * @param attributes - list of parameters.
     * @return - returns new aircraft.
     */
    public static PassengerAircraft createPassengerAircraft(List<String> attributes) {
        CivilianAirport airport = (CivilianAirport) returnAirportNode(attributes.get(1));
        try {
            assert airport != null;
            PassengerAircraft aircraft = (PassengerAircraft) airport.createAircraft(airport.getCoordinates(), Integer.parseInt(attributes.get(2)),
                    Integer.parseInt(attributes.get(3)), assignId(), Integer.parseInt(attributes.get(4)),
                    null, null, listOfTravelRoutes.get(Integer.parseInt(attributes.get(5))));
            listOfAircrafts.add(aircraft);
            aircraft.setThread();
            aircraft.getThread().start();
            return aircraft;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method creates new military aircraft.
     * We are passing list of arguments. This list should contain:
     * - name of airport,
     * - amount of staff,
     * - number of travel route, that we are choosing,
     * - type of arming.
     * After creating military aircraft, we are going to create thread and start it.
     * @param attributes - list of parameters.
     * @return - returns new aircraft.
     */
    public static MilitaryAircraft createMilitaryAircraft(List<String> attributes) {
        MilitaryAirport airport = (MilitaryAirport) returnAirportNode(attributes.get(1));
        try {
            assert airport != null;
            MilitaryAircraft aircraft = (MilitaryAircraft) airport.createAircraft(airport.getCoordinates(), assignId(),
                    Integer.parseInt(attributes.get(2)), null, null, getListOfTravelRoutes().get(
                            Integer.parseInt(attributes.get(3))), typesOfArms.valueOf(attributes.get(4)));
            listOfAircrafts.add(aircraft);
            aircraft.setThread();
            aircraft.getThread().start();
            return aircraft;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }

    /**
     * This method creates new passenger ship.
     * After creating passenger ship, we are going to create thread and start it.
     * @param maximumAmountOfPassengers - maximum amount of passengers, that can be on ship during voyage.
     * @param currentAmountOfPassengers - current amount of passengers, that are on ship during voyage.
     * @param firmName - name of firm, that created this ship.
     * @param velocity - velocity of ship.
     * @param travelRoute - number of travel route from list of travel routes.
     * @return - returns passenger ship.
     */
    public static PassengerShip createPassengerShip(String maximumAmountOfPassengers, String currentAmountOfPassengers,
                                             String firmName, String velocity, String travelRoute){
        PassengerShip passengerShip = new PassengerShip(assignId(), Integer.parseInt(maximumAmountOfPassengers),
                new SimpleIntegerProperty(Integer.parseInt(currentAmountOfPassengers)),
                FirmNames.valueOf(firmName), Integer.parseInt(velocity), getListOfTravelRoutes().get(Integer.parseInt(travelRoute)));
        addShipToListOfShips(passengerShip);
        passengerShip.setThread();
        passengerShip.getThread().start();
        return passengerShip;
    }
    /**
     * This method creates new military ship.
     * We are passing list of arguments. This list should contain:
     * - velocity,
     * - type of arming,
     * - number of travel route, that we are choosing.
     * After creating military ship, we are going to create thread and start it.
     * @param attributes - list of parameters.
     * @return - returns new military ship.
     */
    public static MilitaryShip createMilitaryShip(List<String> attributes) {
        MilitaryShip ship = new MilitaryShip(assignId(), Integer.parseInt(attributes.get(1)),
                typesOfArms.valueOf(attributes.get(2)) ,getListOfTravelRoutes().get(Integer.parseInt(attributes.get(3))));
        addShipToListOfShips(ship);
        ship.setThread();
        ship.getThread().start();
        return ship;
    }

    private void initializeEntities() throws InterruptedException {
        List<List<String>> records = readCSVFile("src/entities.csv");
        for (List<String> list : records) {
            if (list.get(0).equals("PS")) {
                createPassengerShip(list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            }
            if (list.get(0).equals("PA")) {
                createPassengerAircraft(list);
            }
            if (list.get(0).equals("MS")) {
                createMilitaryShip(list);
            }
            if (list.get(0).equals("MA")) {
                createMilitaryAircraft(list);
            }
        }
    }

    private Boolean connectionExists(Node node, TravelRoute travelRoute, int index1) {
        for (SeaNode seaNode : ((SeaNode) node).getConnections()) {
            if (seaNode.equals(travelRoute.getCheckpoints().get(index1))){
                return true;
            }
        }
        return false;
    }

    private void addConnection(Node seaNode, TravelRoute travelRoute, int index) {
        if (!connectionExists(seaNode, travelRoute, index)) {
            ((SeaNode) seaNode).addConnection((SeaNode) travelRoute.getCheckpoints().get(index));
        }
    }

    private void setConnections(TravelRoute travelRoute) {
        for (Node seaNode : travelRoute.getCheckpoints()) {
            int index = travelRoute.getCheckpoints().indexOf(seaNode);

            if (index == 0) {
                addConnection(seaNode, travelRoute, travelRoute.getCheckpoints().size() - 1);
                addConnection(seaNode, travelRoute, index + 1);
            } else if (index == travelRoute.getCheckpoints().size() - 1) {
                addConnection(seaNode, travelRoute, index - 1);
                addConnection(seaNode, travelRoute,  0);
            } else {
                addConnection(seaNode, travelRoute, index - 1);
                addConnection(seaNode, travelRoute, index + 1);
            }
        }
    }

}
