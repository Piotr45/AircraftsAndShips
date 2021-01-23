package graphicalUserInterface;

import com.sun.media.jfxmediaimpl.platform.Platform;
import enumerates.FirmNames;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.*;
import enumerates.typesOfArms;
import other.Node;

import java.util.List;

import static java.lang.Integer.parseInt;


public class ControlPanelView implements EventHandler {

    @FXML
    private ComboBox<String> chooseVehicleComboBox = new ComboBox<>();

    @FXML
    private ComboBox<String> airportComboBox = new ComboBox<>();

    @FXML
    private ComboBox<String> typeOfArmsComboBox = new ComboBox<>();

    @FXML
    private ComboBox<String> firmNamesComboBox = new ComboBox<>();

    @FXML
    private final Button submitButton = new Button("Submit");

    @FXML
    private TextField maximumAmountOfPassengersTextField = new TextField();

    @FXML
    private TextField amountOfStaffTextField = new TextField();

    @FXML
    private TextField currentOfPassengersTextField = new TextField();

    @FXML
    private TextField velocityTextField = new TextField();


    private Scene scene;
    private final Stage controlPanelWindow;
    private BorderPane borderPane;
    private VBox vehiclePanel;
    private VBox specificationPanel = new VBox();

    public Controller controller;

    private final int prefWidth = 200;
    private final int prefHeight = 20;

    private TreeItem<Node> treeRoot = new TreeItem<>(new Node("root"));
    private TreeItem<Node> aircrafts = new TreeItem<>(new Node("Aircrafts"));
    private TreeItem<Node> ships = new TreeItem<>(new Node("Ships"));
    private TreeItem<Node> airports = new TreeItem<>(new Node("Airports"));
    private TreeView<Node> treeView;

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

        chooseVehicleComboBox.setOnAction(this);

        controlPanelWindow.show();
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
        initializeVehiclePanel();
        initializeFirmNamesComboBox();
    }

    private void initializeFirmNamesComboBox() {
        firmNamesComboBox.getItems().add("Select firm name");
        for (FirmNames firmNames : FirmNames.values()) {
            firmNamesComboBox.getItems().add(firmNames.name());
        }
        firmNamesComboBox.getSelectionModel().select("Select firm name");
        firmNamesComboBox.setPrefSize(prefWidth, prefHeight);
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
        controlPanelWindow.setMinWidth(800);
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

    private <T> void createLabelsForAircraft(T object) {
        Pair<DoubleProperty, DoubleProperty> coordinates = ((Aircraft) object).getCoordinates();
        Label specificationLabel = createLabel(("Maximum amount of fuel: " + ((Aircraft) object).getMaximumAmountOfFuel()) + "\n" +
                ("Current amount of fuel: " + ((Aircraft) object).getCurrentAmountOfFuel()) + "\n" +
                ("Amount of staff: " + ((Aircraft) object).getAmountOfStaff()) + "\n" +
                ("Last visited airport: " + ((Aircraft) object).getLastVisitedAirport()) + "\n" +
                ("Next airport: " + ((Aircraft) object).getNextAirport()) + "\n" +
                ("Travel route: " + ((Aircraft) object).getTravelRoute()) + "\n" +
                ("Coordinates: " + "(" + coordinates.getKey().get() + ", " + coordinates.getValue().get()) + ")", "bg-1");
        specificationPanel.getChildren().add(specificationLabel);
    }

    private <T> void createLabelsForShip(T object) {
        Pair<DoubleProperty, DoubleProperty> coordinates = ((Ship) object).getCoordinates();
        Label specificationLabel = createLabel(("Firm name: " + ((PassengerShip) object).getFirmName()) + "\n" +
                ("Coordinates: " + "(" + coordinates.getKey().get() + ", " + coordinates.getValue().get()) + ")" + "\n" +
                ("Maximum amount of passengers: " + ((Ship) object).getMaximumAmountOfPassengers()) + "\n" +
                ("Current amount of passengers: " + ((Ship) object).getCurrentAmountOfPassengers()), "bg-1");
        specificationPanel.getChildren().add(specificationLabel);
    }

    private <T extends Node> void initializeSpecificationPanel(T object) {
        specificationPanel = new VBox();
        specificationPanel.setPadding(new Insets(25));
        if (object instanceof PassengerAircraft) {
            try {
                createLabelsForAircraft(object);
                Label passengersLabel = createLabel(("Maximum amount of passengers: " + ((PassengerAircraft) object).getMaximumAmountOfPassengers()) + "\n" +
                        ("(Current amount of passengers: " + ((PassengerAircraft) object).getCurrentAmountOfPassengers()), "bg-1");
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
            Pair<DoubleProperty, DoubleProperty> coordinates = ((Airport) object).getCoordinates();
//            Label airportLabel = createLabel(("Airport Name: " + ((Airport) object).getName()) + " Airport" + "\n" +
//                    ("Coordinates: " + "(" + coordinates.getKey().get() + ", " + coordinates.getValue().get()) + ")" + "\n" +
//                    ("Current serviced aircraft: " + ((Airport) object).getCurrentServicedAircraft()), "bg-1");
            Label airportLabel = createLabel(((Airport) object).getInfo(), "bg-1");
            specificationPanel.getChildren().add(airportLabel);
        }
        BorderPane.setAlignment(specificationPanel, Pos.CENTER);
        borderPane.setCenter(specificationPanel);
        specificationPanel.setMinSize(245, 350);
    }

    private void addAirportsToComboBox(boolean isCivil) {
        List<Airport> listOfAirports = controller.getListOfAirports();
        if (listOfAirports.isEmpty()) {
            return;
        }
        if (isCivil) {
            for (Airport airport : controller.getListOfAirports()) {
                if (airport instanceof CivilianAirport)
                    airportComboBox.getItems().add(airport.getName());
            }
        } else {
            for (Airport airport : controller.getListOfAirports()) {
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
        addBranchesToRoot(controller.getListOfAircrafts(), aircrafts);
        ships = makeBranch(treeRoot, ships);
        addBranchesToRoot(controller.getListOfShips(), ships);
        airports = makeBranch(treeRoot, airports);
        addBranchesToRoot(controller.getListOfAirports(), airports);
    }

    private void attachListenerToTreeView() {
        treeView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
//                newValue.getValue().getCoordinates().getKey().addListener(((observable1, oldValue1, newValue1) -> {
//                    initializeSpecificationPanel(newValue.getValue());
//                }));
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
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, amountOfStaffTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForMilitaryAircraft() {
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(3, amountOfStaffTextField);
        vehiclePanel.getChildren().add(4, submitButton);
    }

    private void setVehiclePanelForPassengerShip() {
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, firmNamesComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, submitButton);
    }

    private void setVehiclePanelForMilitaryShip() {
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(2, submitButton);
    }

    private <T> T getSelectedValue(ComboBox<T> comboBox) {
        return comboBox.getValue();
    }

    private Airport getAirport(String string) {
        for (Airport item : controller.getListOfAirports()) {
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
                Aircraft aircraft = selectedAirport.createAircraft((Pair<DoubleProperty, DoubleProperty>) selectedAirport.getCoordinates(),
                        Integer.parseInt(maximumAmountOfPassengersTextField.getText()),
                        new SimpleIntegerProperty(Integer.parseInt(currentOfPassengersTextField.getText())),
                        controller.assignId(), new SimpleIntegerProperty(100), new SimpleIntegerProperty(100),
                        Integer.parseInt(amountOfStaffTextField.getText()), selectedAirport, null, null);
                controller.addAircraftToListOfAircrafts(aircraft);
                makeBranch(aircrafts, new TreeItem<Node>(aircraft));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getSelectedValue(chooseVehicleComboBox).equals("Military Aircraft")) {
            // TODO naprawić błąd tworzenia pojazdu
            try {
                Aircraft aircraft = selectedAirport.createAircraft(selectedAirport.getCoordinates(),
                        controller.assignId(), new SimpleIntegerProperty(100), new SimpleIntegerProperty(100),
                        Integer.parseInt(amountOfStaffTextField.getText()), selectedAirport, null, null,
                        typesOfArms.valueOf(typeOfArmsComboBox.getValue()));
                controller.addAircraftToListOfAircrafts(aircraft);
                makeBranch(aircrafts, new TreeItem<Node>(aircraft));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createPassengerShipObject() {
        try {
            PassengerShip passengerShip = controller.createPassengerShip(maximumAmountOfPassengersTextField.getText(),
                    currentOfPassengersTextField.getText(),
                    firmNamesComboBox.getSelectionModel().getSelectedItem(), "10", "1");
            controller.addShipToListOfShips(passengerShip);
            makeBranch(ships, new TreeItem<Node>(passengerShip));
            Main.mapPanelView.drawShip(passengerShip);
            //TODO uruchom wątek
            //controller.getListOfThreads().get(controller.getListOfThreads().size()-1).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handle(Event event) {
        if (event.getSource() == submitButton) {
            if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft") || getSelectedValue(chooseVehicleComboBox).equals("Military Aircraft")) {
                createAircraftObject();
            }
            else {
                createPassengerShipObject();
            }
            resetVehiclePanel();
            chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
            System.out.println("Event: submitButton works!");
        }

        if (event.getSource() == chooseVehicleComboBox) {
            System.out.println("Event: chooseVehicleComboBox works! Current value equals: " + chooseVehicleComboBox.getValue() + "\n");
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