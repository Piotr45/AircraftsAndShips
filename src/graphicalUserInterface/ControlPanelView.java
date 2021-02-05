package graphicalUserInterface;

import enumerates.FirmNames;
import enumerates.States;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import other.TravelRoute;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.*;
import enumerates.typesOfArms;
import other.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * ControlPanelView is a class responsible for graphical user interface of controller.
 */
public class ControlPanelView implements EventHandler {

    private ComboBox<String> chooseVehicleComboBox = new ComboBox<>();

    private ComboBox<String> airportComboBox = new ComboBox<>();

    private ComboBox<String> typeOfArmsComboBox = new ComboBox<>();

    private ComboBox<String> firmNamesComboBox = new ComboBox<>();

    private ComboBox<String> seaTravelRouteComboBox = new ComboBox<>();

    private ComboBox<String> airTravelRouteComboBox = new ComboBox<>();

    private final Button submitButton = new Button("Submit");
    private final Button addVehicleButton = new Button("Add vehicle");
    private final Button removeVehicleButton = new Button("Remove vehicle");
    private final Button emergencyButton = new Button("Call breakdown");

    private TextField maximumAmountOfPassengersTextField = new TextField();

    private TextField amountOfStaffTextField = new TextField();

    private TextField currentOfPassengersTextField = new TextField();

    private TextField velocityTextField = new TextField();


    private Scene scene;
    private final Stage controlPanelWindow;
    private static BorderPane borderPane;
    private VBox vehiclePanel;
    private static VBox specificationPanel = new VBox();
    private HBox options = new HBox();

    /**
     * Controller.
     */
    public Controller controller;

    private final int prefWidth = 200;
    private final int prefHeight = 20;

    private TreeItem<Node> treeRoot = new TreeItem<>(new Node("root"));
    private TreeItem<Node> aircrafts = new TreeItem<>(new Node("Aircrafts"));
    private TreeItem<Node> ships = new TreeItem<>(new Node("Ships"));
    private TreeItem<Node> airports = new TreeItem<>(new Node("Airports"));
    private static TreeView<Node> treeView;

    /**
     * ControlPanelView constructor with two parameters.
     * @param controlPanelWindow - stage of control panel graphical user interface.
     * @param controller - controller that we will pass as argument to manage data.
     */
    public ControlPanelView(Stage controlPanelWindow, Controller controller) {
        this.controlPanelWindow = controlPanelWindow;
        this.controller = controller;
        buildUI();
    }

    private void buildUI() {
        setPromptTexts();
        initializeBorderPane();
        initializeComboBoxes();

        scene = new Scene(borderPane, 800, 600);

        initializeStage();
        initializeTreeView();
        initializeOptions();


        setButtonsOnAction();

        controlPanelWindow.show();
    }

    /**
     * This method refreshes specification panel of selected item form tree view.
     */
    public static void refreshSpecificationPanel() {
        try {
            if (treeView.getSelectionModel().getSelectedItem().getValue() != null) {
                initializeSpecificationPanel(treeView.getSelectionModel().getSelectedItem().getValue());
            }
        }catch (NullPointerException ignore) {
        }
    }

    private void setButtonsOnAction() {
        chooseVehicleComboBox.setOnAction(this);
        addVehicleButton.setOnAction(this);
        removeVehicleButton.setOnAction(this);
        emergencyButton.setOnAction(this);
    }

    private void setPromptTexts() {
        amountOfStaffTextField.clear();
        maximumAmountOfPassengersTextField.clear();
        currentOfPassengersTextField.clear();
        velocityTextField.clear();

        amountOfStaffTextField.setPromptText("Amount of staff");
        maximumAmountOfPassengersTextField.setPromptText("Maximum amount of passengers");
        currentOfPassengersTextField.setPromptText("Current amount of passengers");
        velocityTextField.setPromptText("Velocity");
    }

    private void initializeOptions() {
        this.options.getChildren().addAll(this.addVehicleButton, this.removeVehicleButton, this.emergencyButton);

        options.setAlignment(Pos.BASELINE_LEFT);
        BorderPane.setAlignment(options, Pos.BASELINE_LEFT);
        borderPane.setTop(options);
    }

    private void initializeTreeView() {
        treeView = createTreeView();
        BorderPane.setAlignment(treeView, Pos.CENTER_LEFT);
        borderPane.setLeft(treeView);
    }

    private void initializeBorderPane() {
        borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-0");
        borderPane.setPadding(new Insets(25));
    }

    private void initializeComboBoxes() {
        initializeTypeOfArmsComboBox();
        initializeEntityComboBox();
        initializeFirmNamesComboBox();
        initializeSeaTravelRouteComboBox();
    }

    private void initializeFirmNamesComboBox() {
        firmNamesComboBox.getItems().add("Select firm name");
        for (FirmNames firmNames : FirmNames.values()) {
            firmNamesComboBox.getItems().add(firmNames.name());
        }
        firmNamesComboBox.getSelectionModel().select("Select firm name");
        firmNamesComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeSeaTravelRouteComboBox() {
        seaTravelRouteComboBox.getItems().add("Select travel route");
        for (TravelRoute travelRoute : controller.getListOfSeaRoutes()) {
            seaTravelRouteComboBox.getItems().add(travelRoute.toString());
        }
        seaTravelRouteComboBox.getSelectionModel().select("Select travel route");
        seaTravelRouteComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeAirTravelRouteComboBox(boolean isMil) {
        airTravelRouteComboBox.getItems().clear();
        airTravelRouteComboBox.getItems().add("Select travel route");
        if (isMil) {
            for (TravelRoute travelRoute : Controller.getListOfAirMilTravelRoutes()) {
                airTravelRouteComboBox.getItems().add(travelRoute.toString());
            }
        } else {
            for (TravelRoute travelRoute : Controller.getListOfAirCivTravelRoutes()) {
                airTravelRouteComboBox.getItems().add(travelRoute.toString());
            }
        }
        airTravelRouteComboBox.getSelectionModel().select("Select travel route");
        airTravelRouteComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeVehiclePanel() {
        vehiclePanel = new VBox();
        vehiclePanel.setPadding(new Insets(25));
        vehiclePanel.setAlignment(Pos.CENTER);
        vehiclePanel.getChildren().add(0, chooseVehicleComboBox);
        BorderPane.setAlignment(vehiclePanel, Pos.TOP_RIGHT);
        borderPane.setRight(vehiclePanel);
    }

    private void initializeStage() {
        controlPanelWindow.setTitle("Control Panel");
        controlPanelWindow.setMinHeight(500);
        controlPanelWindow.setMinWidth(825);
        controlPanelWindow.setScene(scene);
    }

    private void initializeTypeOfArmsComboBox() {
        typeOfArmsComboBox.getItems().add("Select weapon");
        for (typesOfArms item : typesOfArms.values()) {
            if (!item.equals(typesOfArms.NONE)) {
                typeOfArmsComboBox.getItems().add(item.name());
            }
        }
        typeOfArmsComboBox.setPrefSize(prefWidth, prefHeight);
        typeOfArmsComboBox.getSelectionModel().select("Select weapon");
    }

    private void initializeEntityComboBox() {
        chooseVehicleComboBox.getItems().addAll("Select Vehicle", "Passenger Aircraft", "Military Aircraft", "Passenger Ship", "Military Ship");
        chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
        chooseVehicleComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeAirportComboBox(boolean isCivil) {
        airportComboBox.getItems().clear();
        airportComboBox.getItems().add("Select Airport");
        addAirportsToComboBox(isCivil);
        airportComboBox.getSelectionModel().select("Select Airport");
        airportComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void resetVehiclePanel() {
        vehiclePanel.getChildren().clear();
        vehiclePanel.getChildren().add(0, chooseVehicleComboBox);
        setPromptTexts();
    }

    private static <T> void createLabelsForAircraft(T object) {
        Pair<Double, Double> coordinates = ((Aircraft) object).getCoordinates();
        Label specificationLabel = createLabel(("Maximum amount of fuel: " + ((Aircraft) object).getMaximumAmountOfFuel()) + "\n" +
                ("Current amount of fuel: " + (int) ((Aircraft) object).getCurrentAmountOfFuel()) + "%" + "\n" +
                ("Amount of staff: " + ((Aircraft) object).getAmountOfStaff()) + "\n" +
                ("Last visited airport: " + ((Aircraft) object).getCurrentAirport()) + "\n" +
                ("Next airport: " + ((Aircraft) object).getNextAirport()) + "\n" +
                ("Coordinates: " + "(" + ((int) coordinates.getKey().doubleValue()) + ", " + ((int) coordinates.getValue().doubleValue())) + ")", "bg-1");
        specificationPanel.getChildren().add(specificationLabel);
    }

    private static <T> void createLabelsForShip(T object) {
        Pair<Double, Double> coordinates = ((Ship) object).getCoordinates();
        Label specificationLabel = createLabel("Coordinates: " + "(" +
                ((int) coordinates.getKey().doubleValue()) + ", " +
                        ((int) coordinates.getValue().doubleValue()) + ")", "bg-1");
        specificationPanel.getChildren().add(specificationLabel);
    }

    private static <T extends Node> void initializeSpecificationPanel(T object) {
        specificationPanel = new VBox();
        specificationPanel.setPadding(new Insets(25));
        if (object instanceof PassengerAircraft) {
            try {
                createLabelsForAircraft(object);
                Label passengersLabel = createLabel(("Maximum amount of passengers: " + ((PassengerAircraft) object).getMaximumAmountOfPassengers()) + "\n" +
                        ("Current amount of passengers: " + ((PassengerAircraft) object).getCurrentAmountOfPassengers()), "bg-1");
                specificationPanel.getChildren().add(passengersLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (object instanceof MilitaryAircraft) {
            try {
                createLabelsForAircraft(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label typeOfArmsLabel = createLabel(("Type of arms: " + ((MilitaryAircraft) object).getTypeOfArms()), "bg-1");
            specificationPanel.getChildren().add(typeOfArmsLabel);
        } else if (object instanceof PassengerShip) {
            try {
                createLabelsForShip(object);
                Label label = createLabel(("Firm name: " + ((PassengerShip) object).getFirmName() + "\n" +
                        "Maximum amount of passengers: " + ((PassengerShip) object).getMaximumAmountOfPassengers()) + "\n" +
                        ("Current amount of passengers: " + ((PassengerShip) object).getCurrentAmountOfPassengers()), "bg-1");
                specificationPanel.getChildren().add(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (object instanceof MilitaryShip) {
            try {
                createLabelsForShip(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label label5 = createLabel(("Type of arms: " + ((MilitaryShip) object).getTypeOfArms()), "bg-1");
            specificationPanel.getChildren().add(label5);
        } else if (object instanceof CivilianAirport || object instanceof MilitaryAirport) {
            Label airportLabel = createLabel(((Airport) object).getInfo(), "bg-1");
            specificationPanel.getChildren().add(airportLabel);
        }
        specificationPanel.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(specificationPanel, Pos.CENTER);
        borderPane.setCenter(specificationPanel);
        specificationPanel.setMinSize(290, 350);
    }

    private void addAirportsToComboBox(boolean isCivil) {
        List<Airport> listOfAirports = Controller.getListOfAirports();
        if (listOfAirports.isEmpty()) {
            return;
        }
        if (isCivil) {
            for (Airport airport : Controller.getListOfAirports()) {
                if (airport instanceof CivilianAirport)
                    airportComboBox.getItems().add(airport.getName());
            }
        } else {
            for (Airport airport : Controller.getListOfAirports()) {
                if (airport instanceof MilitaryAirport)
                    airportComboBox.getItems().add(airport.getName());
            }
        }

    }

    private TreeView<Node> createTreeView() {
        treeRoot.setExpanded(true);

        createMainBranches();

        treeView = new TreeView<>(treeRoot);
        treeView.setShowRoot(false);

        attachListenerToTreeView();

        return treeView;
    }

    private void createMainBranches() {
        aircrafts = makeBranch(treeRoot, aircrafts);
        addBranchesToRoot(Controller.getListOfAircrafts(), aircrafts);
        ships = makeBranch(treeRoot, ships);
        addBranchesToRoot(controller.getListOfShips(), ships);
        airports = makeBranch(treeRoot, airports);
        addBranchesToRoot(Controller.getListOfAirports(), airports);
    }

    private void attachListenerToTreeView() {
        treeView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                initializeSpecificationPanel(newValue.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    private <T> TreeItem<T> createTreeItem(T object) {
        TreeItem<T> treeItem = new TreeItem<>(object);
        return treeItem;
    }

    private <T> void addBranchesToRoot(List<T> items, TreeItem<Node> root) {
        for (T item : items) {
            TreeItem<Node> treeItem = (TreeItem<Node>) createTreeItem(item);
            makeBranch(root, treeItem);
        }
    }

    private <T1, T2 extends T1> TreeItem<T2> makeBranch(TreeItem<T1> parent, TreeItem<T2> item) {
        item.setExpanded(true);
        parent.getChildren().add((TreeItem<T1>) item);
        return item;
    }

    private static Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

    private void setVehiclePanelForPassengerAircraft() {
        resetVehiclePanel();
        initializeAirTravelRouteComboBox(false);
        vehiclePanel.getChildren().add(1, airTravelRouteComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, amountOfStaffTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForMilitaryAircraft() {
        resetVehiclePanel();
        initializeAirTravelRouteComboBox(true);
        vehiclePanel.getChildren().add(1, airTravelRouteComboBox);
        vehiclePanel.getChildren().add(2, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(3, amountOfStaffTextField);
        vehiclePanel.getChildren().add(4, velocityTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForPassengerShip() {
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, firmNamesComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, velocityTextField);
        vehiclePanel.getChildren().add(5, seaTravelRouteComboBox);
        vehiclePanel.getChildren().add(6, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForMilitaryShip() {
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(2, velocityTextField);
        vehiclePanel.getChildren().add(3, seaTravelRouteComboBox);
        vehiclePanel.getChildren().add(4, submitButton);
        submitButton.setOnAction(this);
    }

    private <T> T getSelectedValue(ComboBox<T> comboBox) {
        return comboBox.getValue();
    }

    private Airport getAirport(String string) {
        for (Airport item : Controller.getListOfAirports()) {
            if (item.getName().equals(string)) {
                return item;
            }
        }
        return null;
    }

    private void createAircraftObject() {
        Airport selectedAirport = getAirport(getSelectedValue(airportComboBox));
        if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft")) {
            try {
                List<String> list = new ArrayList<>();
                list.add("Nic");
                list.add(Controller.getListOfTravelRoutes().get(Integer.parseInt(
                        airTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2])).getCheckpoints().get(0).getName());
                list.add(maximumAmountOfPassengersTextField.getText());
                list.add(currentOfPassengersTextField.getText());
                list.add(amountOfStaffTextField.getText());
                list.add(airTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2]);
                makeBranch(aircrafts, new TreeItem<Node>(Controller.createPassengerAircraft(list)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (getSelectedValue(chooseVehicleComboBox).equals("Military Aircraft")) {
            try {
                assert selectedAirport != null;
                List<String> list = new ArrayList<>();
                list.add(0,"Nic");
                list.add(1, Controller.getListOfTravelRoutes().get(Integer.parseInt(
                        airTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2])).getCheckpoints().get(0).getName());
                list.add(2, velocityTextField.getText());
                list.add(3, airTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2]);
                list.add(4, typeOfArmsComboBox.getValue());
                MilitaryAircraft test = Controller.createMilitaryAircraft(list);
                makeBranch(aircrafts, new TreeItem<Node>(test));
                Main.mapPanelView.drawAircraft(test);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createShipObject() {
        if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft")) {
            try {
                PassengerShip passengerShip = Controller.createPassengerShip(maximumAmountOfPassengersTextField.getText(), currentOfPassengersTextField.getText(),
                        firmNamesComboBox.getSelectionModel().getSelectedItem(), velocityTextField.getText(),
                        seaTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2]);
                Controller.addShipToListOfShips(passengerShip);
                makeBranch(ships, new TreeItem<Node>(passengerShip));
                Main.mapPanelView.drawShip(passengerShip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            List<String> list = new ArrayList<>();
            list.add("Nic");
            list.add(velocityTextField.getText());
            list.add(typeOfArmsComboBox.getValue());
            list.add(seaTravelRouteComboBox.getSelectionModel().getSelectedItem().split(" ")[2]);
            MilitaryShip militaryShip = Controller.createMilitaryShip(list);
        }
    }

    private Boolean velocityErrorBox() {
        if (Integer.parseInt(velocityTextField.getText()) < 0) {
            AlertBox.displayAlertBoxWindow("Velocity error", "Velocity can't be negative!");
            return true;
        }
        else if (Integer.parseInt(velocityTextField.getText()) == 0) {
            AlertBox.displayAlertBoxWindow("Velocity error", "Velocity can't be zero!");
            return true;
        }
        else if (Integer.parseInt(velocityTextField.getText()) > 200) {
            AlertBox.displayAlertBoxWindow("Velocity error", "Velocity can't be higher than 200!");
            return true;
        }
        else if (velocityTextField.getText().equals("")) {
            AlertBox.displayAlertBoxWindow("Velocity error", "Velocity can't be null!");
            return true;
        }
        return false;
    }

    private Boolean travelRouteErrorBox(int i) {
        if (airTravelRouteComboBox.getSelectionModel().getSelectedItem().equals("Select travel route") && i == 1) {
            AlertBox.displayAlertBoxWindow("Travel route error", "Travel route is not selected");
            return true;
        }
        else if (seaTravelRouteComboBox.getSelectionModel().getSelectedItem().equals("Select travel route") && i == 0) {
            AlertBox.displayAlertBoxWindow("Travel route error", "Travel route is not selected");
            return true;
        }
        return false;
    }

    private Boolean peopleErrorBox() {
        if (maximumAmountOfPassengersTextField.getText().equals("") || currentOfPassengersTextField.getText().equals("")) {
            AlertBox.displayAlertBoxWindow("Text error", "TextBox can't be null!");
            return true;
        }
        else if (Integer.parseInt(maximumAmountOfPassengersTextField.getText()) < 0){
            AlertBox.displayAlertBoxWindow("Passengers error", "Number of passengers can't be lower than zero!");
            return true;
        }
        else if (Integer.parseInt(currentOfPassengersTextField.getText()) < 0) {
            AlertBox.displayAlertBoxWindow("Passengers error", "Number of passengers can't be lower than zero!");
            return true;
        }
        else if (Integer.parseInt(currentOfPassengersTextField.getText()) > Integer.parseInt(maximumAmountOfPassengersTextField.getText())) {
            AlertBox.displayAlertBoxWindow("Passengers error", "Number of passengers can't higher than maximum!");
            return true;
        }
        else if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft")) {
            if (amountOfStaffTextField.getText().equals("")) {
                AlertBox.displayAlertBoxWindow("Text error", "TextBox can't be null!");
                return true;
            }
            if (Integer.parseInt(amountOfStaffTextField.getText()) < 0) {
                AlertBox.displayAlertBoxWindow("Staff error", "Number of staff can't be lower than zero!");
                return true;
            }
            else if (Integer.parseInt(amountOfStaffTextField.getText()) > 23 ) {
                AlertBox.displayAlertBoxWindow("Staff error", "Number of staff can't be higher than 23!");
                return true;
            }
        }
        return false;
    }

    private Boolean militaryError() {
        if (typeOfArmsComboBox.getSelectionModel().getSelectedItem().equals("Select weapon")) {
            AlertBox.displayAlertBoxWindow("Weapon error", "You have to choose weapon!");
            return true;
        }
        return false;
    }

    private Boolean passengerShipError(){
        if (firmNamesComboBox.getSelectionModel().getSelectedItem().equals("Select firm")) {
            AlertBox.displayAlertBoxWindow("Firm name error", "You have to choose firm name!");
            return true;
        }
        return false;
    }

    private void handleEntity() {
        if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft") || getSelectedValue(chooseVehicleComboBox).equals("Military Aircraft")) {
            if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft")) {
                if (!peopleErrorBox() && !travelRouteErrorBox(1)){
                    createAircraftObject();
                }
            }
            else {
                if (!militaryError() && !travelRouteErrorBox(1)) {
                    createAircraftObject();
                }
            }
        } else {
            if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Ship")) {
                if (!travelRouteErrorBox(0) && !velocityErrorBox() && !peopleErrorBox() && !passengerShipError()){
                    createShipObject();
                }
            }
            else {
                if (!militaryError() && !velocityErrorBox() && !travelRouteErrorBox(0)) {
                    createShipObject();
                }
            }
        }
    }

    /**
     * This method menages events that occur on control panel.
     * @param event - event to manage.
     */
    @Override
    public void handle(Event event) {
        if (event.getSource() == submitButton) {
            handleEntity();
            resetVehiclePanel();
            chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
        }

        if (event.getSource() == addVehicleButton) {
            initializeVehiclePanel();
        }

        if (event.getSource() == emergencyButton) {
            try {
                Aircraft aircraft = ((Aircraft) treeView.getSelectionModel().getSelectedItem().getValue());
                aircraft.emergencyLanding();
                treeView.getSelectionModel().getSelectedItem().getParent().getChildren().remove(treeView.getSelectionModel().getSelectedItem());
            } catch (Exception ignore) {
            }
        }

        if (event.getSource() == removeVehicleButton) {
            try {
                Aircraft aircraft = ((Aircraft) treeView.getSelectionModel().getSelectedItem().getValue());
                aircraft.getThread().stop();
                if (aircraft.getState().equals(States.arriving)) {
                    aircraft.getCurrentAirport().freeNode();
                }
                Controller.getListOfAircrafts().remove(aircraft);
            } catch (Exception ignore) { }
            try {
                Ship ship = ((Ship) treeView.getSelectionModel().getSelectedItem().getValue());
                ship.getThread().stop();
                if (ship.getState().equals(States.arriving)) {
                    ship.getCurrentNode().freeNode();
                }
                Controller.getListOfShips().remove(ship);
            } catch (Exception ignore) { }
            treeView.getSelectionModel().getSelectedItem().getParent().getChildren().remove(treeView.getSelectionModel().getSelectedItem());
        }

        if (event.getSource() == chooseVehicleComboBox) {
            if (chooseVehicleComboBox.getValue().equals("Passenger Aircraft")) {
                initializeAirportComboBox(true);
                setVehiclePanelForPassengerAircraft();
                chooseVehicleComboBox.getSelectionModel().select("Passenger Aircraft");
            }
            if (chooseVehicleComboBox.getValue().equals("Military Aircraft")) {
                initializeAirportComboBox(false);
                setVehiclePanelForMilitaryAircraft();
                chooseVehicleComboBox.getSelectionModel().select("Military Aircraft");
            }
            if (chooseVehicleComboBox.getValue().equals("Passenger Ship")) {
                setVehiclePanelForPassengerShip();
                chooseVehicleComboBox.getSelectionModel().select("Passenger Ship");
            }
            if (chooseVehicleComboBox.getValue().equals("Military Ship")) {
                setVehiclePanelForMilitaryShip();
                chooseVehicleComboBox.getSelectionModel().select("Military Ship");
            }
            if (chooseVehicleComboBox.getValue().equals("Select Vehicle")) {
                resetVehiclePanel();
                chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
            }
        }
    }
}