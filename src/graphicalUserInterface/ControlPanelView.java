package graphicalUserInterface;

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
import ports.Airport;
import vehicles.*;

import java.util.List;


public class ControlPanelView implements EventHandler {

    @FXML
    private ComboBox<String> entityComboBox;

    @FXML
    private ComboBox<String> airportComboBox;

    @FXML
    private ComboBox<String> vehicleComboBox;

    @FXML
    private final Button submitButton = new Button("Submit");

    private Scene scene;
    private final Stage controlPanelWindow;
    private BorderPane borderPane;
    private VBox VehiclePanel;
    private VBox specificationPanel;
    private VBox currentVehiclePanel;
    public ControlPanel controlPanel;
    private int numberOfAircrafts = 0;


    public ControlPanelView(Stage controlPanelWindow, ControlPanel controlPanel) {
        this.controlPanelWindow = controlPanelWindow;
        this.controlPanel = controlPanel;
        initializeEntityComboBox();
        initializeAirportComboBox();
        buildUI();
    }

    // JavaFX methods

    private void buildUI() {
        initializeAirportComboBox();
        initializeEntityComboBox();
        initializeVehicleComboBox();

        setButtonsOnAction();

        initializeVehiclePanel();
        TreeView<String> treeView = createTreeView();

        borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
        borderPane.setPadding(new Insets(25));

        scene = new Scene(borderPane, 800, 600);

        initializeStage();

        BorderPane.setAlignment(VehiclePanel, Pos.TOP_RIGHT);
        borderPane.setRight(VehiclePanel);
        BorderPane.setAlignment(treeView, Pos.TOP_LEFT);
        borderPane.setLeft(treeView);

        controlPanelWindow.show();
    }

    private void showSpecificationPanel(){}

    private void setButtonsOnAction(){
        submitButton.setDisable(true);
        submitButton.setOnAction(this);
        entityComboBox.setOnAction(this);
        airportComboBox.setOnAction(this);
    }

    private void initializeVehiclePanel(){
        VehiclePanel = new VBox();
        VehiclePanel.setPadding(new Insets(25));
        VehiclePanel.setAlignment(Pos.TOP_CENTER);
        VehiclePanel.getChildren().add(entityComboBox);
        VehiclePanel.getChildren().add(airportComboBox);
        VehiclePanel.getChildren().add(submitButton);

        currentVehiclePanel = new VBox();
        currentVehiclePanel.setPadding(new Insets(25));
        currentVehiclePanel.setAlignment(Pos.TOP_LEFT);
        currentVehiclePanel.getChildren().add(vehicleComboBox);
    }

    private void initializeStage(){
        controlPanelWindow.setTitle("Control Panel");
        controlPanelWindow.setMinHeight(400);
        controlPanelWindow.setMinWidth(440);
        controlPanelWindow.setScene(scene);
    }

    private void initializeEntityComboBox(){
        entityComboBox = new ComboBox<>();
        entityComboBox.getItems().addAll("Select Vehicle", "Civilian Aircraft", "Military Aircraft", "Passenger Ship", "Military Ship");
        entityComboBox.getSelectionModel().select("Select Vehicle");
        entityComboBox.setPrefSize(150, 20);
    }

    private void initializeAirportComboBox(){
        airportComboBox = new ComboBox<>();
        airportComboBox.getItems().addAll("Select Airport");
        addAirportsToComboBox();
        airportComboBox.getSelectionModel().select("Select Airport");
        airportComboBox.setPrefSize(150, 20);
    }

    private void initializeVehicleComboBox(){
        vehicleComboBox = new ComboBox<>();
        vehicleComboBox.getItems().add("Select Vehicle");
        addVehiclesToComboBox();
        vehicleComboBox.getSelectionModel().select("Select Vehicle");
        vehicleComboBox.setPrefSize(150, 20);
    }

    private <T> void initializeSpecificationPanel(T object){
        specificationPanel = new VBox();
        specificationPanel.setPadding(new Insets(25));
        if (object instanceof PassengerAircraft || object instanceof MilitaryAircraft){
            specificationPanel = new VBox();
            specificationPanel.setPadding(new Insets(25));
            Label label1 = createLabel(("Maximum amount of fuel: " + ((Aircraft) object).getMaximumAmountOfFuel()), "bg-1");
            Label label2 = createLabel(("Current amount of fuel: " + ((Aircraft) object).getCurrentAmountOfFuel()), "bg-1");
            Label label3 = createLabel(("Maximum amount of passengers: " + ((Aircraft) object).getMaximumAmountOfPassengers()), "bg-1");
            Label label4 = createLabel(("Current amount of passengers: " + ((Aircraft) object).getCurrentAmountOfPassengers()), "bg-1");
            Label label5 = createLabel(("Amount of staff: " + ((Aircraft) object).getAmountOfStaff()), "bg-1");
            Label label6 = createLabel(("Last visited airport: " + ((Aircraft) object).getLastVisitedAirports()), "bg-1");
            Label label7 = createLabel(("Next airport: " + ((Aircraft) object).getNextAirport()), "bg-1");
            Label label8 = createLabel(("Travel route: " + ((Aircraft) object).getTravelRoute()), "bg-1");
            Label label9 = createLabel(("Coordinates: " + ((Aircraft) object).getCoordinates()), "bg-1");
            Label label10 = createLabel(("Type of arms: " + ((Aircraft) object).getTypeOfArms()), "bg-1");
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3); specificationPanel.getChildren().add(label4);
            specificationPanel.getChildren().add(label5); specificationPanel.getChildren().add(label6);
            specificationPanel.getChildren().add(label7); specificationPanel.getChildren().add(label8);
            specificationPanel.getChildren().add(label9); specificationPanel.getChildren().add(label10);
        }
        if (object instanceof PassengerShip || object instanceof MilitaryShip){
            Label label1 = createLabel(("Firm name: " + ((Ship) object).getFirmName()), "bg-1");
            Label label2 = createLabel(("Coordinates: " + ((Ship) object).getCoordinates()), "bg-1");
            Label label3 = createLabel(("Maximum amount of passengers: " + ((Ship) object).getMaximumAmountOfPassengers()), "bg-1");
            Label label4 = createLabel(("Current amount of passengers: " + ((Ship) object).getCurrentAmountOfPassengers()), "bg-1");
        }
        BorderPane.setAlignment(specificationPanel, Pos.TOP_CENTER);
        borderPane.setCenter(specificationPanel);
    }

    private void addAirportsToComboBox(){
        List<Airport> listOfAirports = controlPanel.getListOfAirports();
        if (listOfAirports.isEmpty()){
            return;
        }
        for(Airport airport: listOfAirports){
            airportComboBox.getItems().add(airport.getName());
        }
    }

    private void addVehiclesToComboBox(){
        List<Aircraft> listOfAircrafts =  controlPanel.getListOfAircrafts();
        if (listOfAircrafts.isEmpty()){
            return;
        }
        for(Aircraft aircraft: listOfAircrafts){
           vehicleComboBox.getItems().add("Aircraft " + aircraft.getId());
        }

    }

    private TreeView<String> createTreeView(){
        TreeView<String> treeView;

        TreeItem<String> root, aircrafts, ships, airports;

        // Root
        root = new TreeItem<>();
        root.setExpanded(true);

        // Items
            // Aircrafts
        aircrafts = makeBranch(root, "Aircrafts");
        addBranchesToRoot(controlPanel.getListOfAircrafts(), aircrafts);
            // Ships
        ships = makeBranch(root, "Ships");
        addBranchesToRoot(controlPanel.getListOfShips(), ships);

        airports = makeBranch(root, "Airports");
        addBranchesToRoot(controlPanel.getListOfAirports(), airports);

        // Create tree
        treeView = new TreeView<>(root);
        treeView.setShowRoot(false);
        treeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue != null){
                String[] splittedName = newValue.getValue().split(" ");
                if (splittedName[0].equals("Aircraft")){
                    for (Aircraft item: controlPanel.getListOfAircrafts()){
                        if(splittedName[1].equals(Integer.toString(item.getId()))){
                            initializeSpecificationPanel(item);
                        }
                    }
                }
                else if (splittedName[0].equals("Ship")){
                    for (Ship item: controlPanel.getListOfShips()){
                        if(splittedName[1].equals(Integer.toString(item.getId()))){
                            initializeSpecificationPanel(item);
                        }
                    }
                }
            }
        });

        return treeView;
    }

    private <T> void addBranchesToRoot(List<T> items,TreeItem<String> root){
        for (T item: items) {
            makeBranch(root,item.toString());
        }
    }

    private TreeItem<String> makeBranch(TreeItem<String> parent, String title) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    private static Label createLabel(String text, String styleClass){
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

    @Override
    public void handle(Event event){
        if (event.getSource() == submitButton){
            entityComboBox.getSelectionModel().select("Select Vehicle");
            airportComboBox.getSelectionModel().select("Select Airport");
        }

        if (event.getSource() == entityComboBox){
            if (entityComboBox.getValue().equals("Select Vehicle") || airportComboBox.getValue().equals("Select Airport")){
                submitButton.setDisable(true);
            }else{
                submitButton.setDisable(false);
            }
        }

        if (event.getSource() == airportComboBox){
            if (entityComboBox.getValue().equals("Select Vehicle") || airportComboBox.getValue().equals("Select Airport")){
                submitButton.setDisable(true);
            }else{
                submitButton.setDisable(false);
            }
        }
    }
}