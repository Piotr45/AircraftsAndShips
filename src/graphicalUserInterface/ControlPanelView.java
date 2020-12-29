package graphicalUserInterface;

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
import other.Entity;

import java.util.Arrays;
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
    private ComboBox<String> firmNamesComboBox= new ComboBox<>();

    @FXML
    private final Button submitButton = new Button("Submit");

    @FXML
    private TextField maximumAmountOfPassengersTextField = new TextField();

    @FXML
    private TextField amountOfStaffTextField = new TextField();

    @FXML
    private TextField currentOfPassengersTextField = new TextField();


    private Scene scene;
    private final Stage controlPanelWindow;
    private BorderPane borderPane;
    private VBox vehiclePanel;
    private VBox specificationPanel = new VBox();

    public ControlPanel controlPanel;
    private int numberOfAircrafts = 0;

    private final int prefWidth = 200;
    private final int prefHeight = 20;

    //private TreeItem<Vehicle> root, aircrafts, ships, airports;
    private TreeItem<Entity> treeRoot = new TreeItem<>(new Entity("root"));
    private TreeItem<Entity> aircrafts = new TreeItem<>(new Entity("Aircrafts"));
    private TreeItem<Entity> ships = new TreeItem<>(new Entity("Ships"));
    private TreeItem<Entity> airports = new TreeItem<>(new Entity("Airports"));
    private TreeView<Entity> treeView;

    public ControlPanelView(Stage controlPanelWindow, ControlPanel controlPanel) {
        this.controlPanelWindow = controlPanelWindow;
        this.controlPanel = controlPanel;
        buildUI();
    }

    // JavaFX methods

    private void buildUI() {
        setPromptTexts();

        initializeEntityComboBox();
        initializeTypeOfArmsComboBox();

        initializeVehiclePanel();
        treeView = createTreeView();

        borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-0");
        borderPane.setPadding(new Insets(25));

        scene = new Scene(borderPane, 800, 600);

        initializeStage();

        chooseVehicleComboBox.setOnAction(this);

        BorderPane.setAlignment(vehiclePanel, Pos.TOP_RIGHT);
        borderPane.setRight(vehiclePanel);
        BorderPane.setAlignment(treeView, Pos.CENTER_LEFT);
        borderPane.setLeft(treeView);

        controlPanelWindow.show();
    }

    private void setPromptTexts(){
        amountOfStaffTextField.clear();
        maximumAmountOfPassengersTextField.clear();
        currentOfPassengersTextField.clear();

        amountOfStaffTextField.setPromptText("Amount of staff");
        maximumAmountOfPassengersTextField.setPromptText("Maximum amount of passengers");
        currentOfPassengersTextField.setPromptText("Current amount of passengers");
    }

    private void initializeVehiclePanel(){
        vehiclePanel = new VBox();
        vehiclePanel.setPadding(new Insets(25));
        vehiclePanel.setAlignment(Pos.CENTER);
        vehiclePanel.getChildren().add(0, chooseVehicleComboBox);
    }

    private void initializeStage(){
        controlPanelWindow.setTitle("Control Panel");
        controlPanelWindow.setMinHeight(500);
        controlPanelWindow.setMinWidth(800);
        controlPanelWindow.setScene(scene);
    }

    private void initializeTypeOfArmsComboBox(){
        typeOfArmsComboBox.getItems().add("Select weapon");
        for (typesOfArms item: typesOfArms.values()){
            if (!item.equals(typesOfArms.NONE)){
                typeOfArmsComboBox.getItems().add(item.name());
            }
        }
        typeOfArmsComboBox.setPrefSize(prefWidth,prefHeight);
        typeOfArmsComboBox.getSelectionModel().select("Select weapon");
    }

    private void initializeEntityComboBox(){
        chooseVehicleComboBox.getItems().addAll("Select Vehicle", "Passenger Aircraft", "Military Aircraft", "Passenger Ship", "Military Ship");
        chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
        chooseVehicleComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeAirportComboBox(boolean isCivil){
        airportComboBox.getItems().clear();
        airportComboBox.getItems().add("Select Airport");
        addAirportsToComboBox(isCivil);
        airportComboBox.getSelectionModel().select("Select Airport");
        airportComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void resetVehiclePanel(){
        vehiclePanel.getChildren().clear();
        vehiclePanel.getChildren().add(0, chooseVehicleComboBox);
        setPromptTexts();
        //chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
    }

    private <T> void initializeSpecificationPanel(T object){
        specificationPanel = new VBox();
        specificationPanel.setPadding(new Insets(25));
        if (object instanceof PassengerAircraft || object instanceof MilitaryAircraft){
            specificationPanel = new VBox();
            specificationPanel.setPadding(new Insets(25));
            Label label1 = createLabel(("Maximum amount of fuel: " + ((Aircraft) object).getMaximumAmountOfFuel()), "bg-1");
            Label label2 = createLabel(("Current amount of fuel: " + ((Aircraft) object).getCurrentAmountOfFuel()), "bg-1");
            Label label3 = createLabel(("Maximum amount of passengers: " + ((PassengerAircraft) object).getMaximumAmountOfPassengers()), "bg-1");
            Label label4 = createLabel(("Current amount of passengers: " + ((PassengerAircraft) object).getCurrentAmountOfPassengers()), "bg-1");
            Label label5 = createLabel(("Amount of staff: " + ((Aircraft) object).getAmountOfStaff()), "bg-1");
            Label label6 = createLabel(("Last visited airport: " + ((Aircraft) object).getLastVisitedAirport()), "bg-1");
            Label label7 = createLabel(("Next airport: " + ((Aircraft) object).getNextAirport()), "bg-1");
            Label label8 = createLabel(("Travel route: " + ((Aircraft) object).getTravelRoute()), "bg-1");
            Label label9 = createLabel(("Coordinates: " + ((Aircraft) object).getCoordinates()), "bg-1");
            //Label label10 = createLabel(("Type of arms: " + ((MilitaryAircraft) object).getTypeOfArms()), "bg-1");
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3); specificationPanel.getChildren().add(label4);
            specificationPanel.getChildren().add(label5); specificationPanel.getChildren().add(label6);
            specificationPanel.getChildren().add(label7); specificationPanel.getChildren().add(label8);
            specificationPanel.getChildren().add(label9); //specificationPanel.getChildren().add(label10);
        }
        if (object instanceof PassengerShip || object instanceof MilitaryShip){
            Label label1 = createLabel(("Firm name: " + ((Ship) object).getFirmName()), "bg-1");
            Label label2 = createLabel(("Coordinates: " + ((Ship) object).getCoordinates()), "bg-1");
            Label label3 = createLabel(("Maximum amount of passengers: " + ((Ship) object).getMaximumAmountOfPassengers()), "bg-1");
            Label label4 = createLabel(("Current amount of passengers: " + ((Ship) object).getCurrentAmountOfPassengers()), "bg-1");
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3); specificationPanel.getChildren().add(label4);
        }
        if (object instanceof CivilianAirport || object instanceof MilitaryAirport){
            Pair<IntegerProperty, IntegerProperty> coordinates = ((Airport) object).getCoordinates();
            Label label1 = createLabel(("Airport Name: " + ((Airport) object).getName()) + " Airport", "bg-1");
            Label label2 = createLabel(("Coordinates: " + "(" + coordinates.getKey().get() + ", " + coordinates.getValue().get()) + ")", "bg-1");
            Label label3 = createLabel(("Current serviced aircraft: " + ((Airport) object).getCurrentServicedAircraft()), "bg-1");
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3);
        }
        BorderPane.setAlignment(specificationPanel, Pos.CENTER);
        borderPane.setCenter(specificationPanel);
        specificationPanel.setMinSize(245, 350);
    }

    private void addAirportsToComboBox(boolean isCivil){
        List<Airport> listOfAirports = controlPanel.getListOfAirports();
        if (listOfAirports.isEmpty()){
            return;
        }
        if (isCivil){
            for(Airport airport: controlPanel.getListOfAirports()){
                if (airport instanceof CivilianAirport)
                    airportComboBox.getItems().add(airport.getName());
            }
        }
        else{
            for(Airport airport: controlPanel.getListOfAirports()){
                if (airport instanceof MilitaryAirport)
                    airportComboBox.getItems().add(airport.getName());
            }
        }

    }

    private TreeView<Entity> createTreeView(){
        // Root
        treeRoot.setExpanded(true);

        aircrafts = makeBranch(treeRoot, aircrafts);
        addBranchesToRoot(controlPanel.getListOfAircrafts(), aircrafts);

            // Ships
        ships = makeBranch(treeRoot, ships);
        addBranchesToRoot(controlPanel.getListOfShips(), ships);

            // Airports
        airports = makeBranch(treeRoot, airports);
        addBranchesToRoot(controlPanel.getListOfAirports(), airports);

        // Create tree
        treeView = new TreeView<>(treeRoot);
        treeView.setShowRoot(false);

        treeView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println(newValue.getValue());
                try {
                    initializeSpecificationPanel(newValue.getValue());
                }catch (Exception e){
                    e.printStackTrace();
                }
        }));

        return treeView;
    }

    private <T> TreeItem<T> createTreeItem(T object){
        TreeItem<T> treeItem = new TreeItem<>(object);
        return treeItem;
    }

    private <T> void addBranchesToRoot(List<T> items, TreeItem<Entity> root){
        for (T item: items) {
            TreeItem<Entity> treeItem = (TreeItem<Entity>) createTreeItem(item);
            makeBranch(root, treeItem);
        }
    }

    private <T1, T2 extends T1> TreeItem<T2> makeBranch(TreeItem<T1> parent, TreeItem<T2> item) {
        item.setExpanded(true);
        parent.getChildren().add((TreeItem<T1>) item);
        return item;
    }

    private static Label createLabel(String text, String styleClass){
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

    private void setVehiclePanelForPassengerAircraft(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, amountOfStaffTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForMilitaryAircraft(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(3, amountOfStaffTextField);
        vehiclePanel.getChildren().add(4, submitButton);
    }

    private void setVehiclePanelForPassengerShip(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, firmNamesComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, currentOfPassengersTextField);
        vehiclePanel.getChildren().add(4, submitButton);
    }

    private void setVehiclePanelForMilitaryShip(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(2, submitButton);
    }

    private <T> T getSelectedValue(ComboBox<T> comboBox){
        return comboBox.getValue();
    }

    private Airport getAirport(String string){
        for (Airport item: controlPanel.getListOfAirports()){
            if (item.getName().equals(string)){
                return item;
            }
        }
        return null;
    }

    private void temp(){
        if (getSelectedValue(chooseVehicleComboBox).equals("Passenger Aircraft")){
            Airport selectedAirport = getAirport(getSelectedValue(airportComboBox));
            try {
                Aircraft aircraft = selectedAirport.createAircraft(selectedAirport.getCoordinates(),
                        Integer.parseInt(maximumAmountOfPassengersTextField.getText()),
                        new SimpleIntegerProperty(Integer.parseInt(currentOfPassengersTextField.getText())),
                        controlPanel.addId(), new SimpleIntegerProperty(100), new SimpleIntegerProperty(100),
                        Integer.parseInt(amountOfStaffTextField.getText()), selectedAirport, null, null);

                controlPanel.addAircraftToListOfAircrafts(aircraft);
                makeBranch( aircrafts, new TreeItem<Entity>(aircraft));
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public void handle(Event event){
        if (event.getSource() == submitButton){
            temp();
            System.out.println("Event: submitButton works!");
            resetVehiclePanel();
            chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
        }

        if (event.getSource() == chooseVehicleComboBox){
            System.out.println("Event: chooseVehicleComboBox works!");
            if (chooseVehicleComboBox.getValue().equals("Passenger Aircraft")){
                initializeAirportComboBox(true);
                setVehiclePanelForPassengerAircraft();
                chooseVehicleComboBox.getSelectionModel().select("Passenger Aircraft");
            }
            if (chooseVehicleComboBox.getValue().equals("Military Aircraft")){
                initializeAirportComboBox(false);
                setVehiclePanelForMilitaryAircraft();
                chooseVehicleComboBox.getSelectionModel().select("Military Aircraft");
            }
            if (chooseVehicleComboBox.getValue().equals("Passenger Ship")){
                setVehiclePanelForPassengerShip();
                chooseVehicleComboBox.getSelectionModel().select("Passenger Ship");
            }
            if (chooseVehicleComboBox.getValue().equals("Military Ship")){
                setVehiclePanelForMilitaryShip();
                chooseVehicleComboBox.getSelectionModel().select("Military Ship");
            }
            if (chooseVehicleComboBox.getValue().equals("Select Vehicle")){
                resetVehiclePanel();
                chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
            }
        }

    }
}