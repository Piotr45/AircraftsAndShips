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
import javafx.util.Pair;
import ports.Airport;
import ports.CivilianAirport;
import ports.MilitaryAirport;
import vehicles.*;
import enumerates.typesOfArms;

import java.util.List;


public class ControlPanelView implements EventHandler {

    @FXML
    private ComboBox<String> chooseVehicleComboBox;

    @FXML
    private ComboBox<String> airportComboBox;

    @FXML
    private ComboBox<String> typeOfArmsComboBox = new ComboBox<>();

    @FXML
    private final Button submitButton = new Button("Submit");

    @FXML
    private TextField maximumAmountOfFuelTextField = new TextField("Maximum amount of fuel");

    @FXML
    private TextField maximumAmountOfPassengersTextField = new TextField("Maximum amount of passengers");

    @FXML
    private TextField amountOfStaffTextField = new TextField("Amount of staff");

    private Scene scene;
    private final Stage controlPanelWindow;
    private BorderPane borderPane;
    private VBox vehiclePanel;
    private VBox specificationPanel;
    public ControlPanel controlPanel;
    private final int numberOfAircrafts = 0;
    private final int prefWidth = 200;
    private final int prefHeight = 20;

    public ControlPanelView(Stage controlPanelWindow, ControlPanel controlPanel) {
        this.controlPanelWindow = controlPanelWindow;
        this.controlPanel = controlPanel;
        buildUI();
    }

    // JavaFX methods

    private void buildUI() {
        //initializeAirportComboBox();
        initializeEntityComboBox();
        initializeTypeOfArmsComboBox();

        setButtonsOnAction();

        initializeVehiclePanel();
        TreeView<String> treeView = createTreeView();

        borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-0");
        borderPane.setPadding(new Insets(25));

        scene = new Scene(borderPane, 800, 600);

        initializeStage();

        BorderPane.setAlignment(vehiclePanel, Pos.TOP_RIGHT);
        borderPane.setRight(vehiclePanel);
        BorderPane.setAlignment(treeView, Pos.TOP_LEFT);
        borderPane.setLeft(treeView);

        controlPanelWindow.show();
    }

    private void setButtonsOnAction(){
        //submitButton.setDisable(true);
        //submitButton.setOnAction(this);
        chooseVehicleComboBox.setOnAction(this);
        //airportComboBox.setOnAction(this);
    }

    private void initializeVehiclePanel(){
        vehiclePanel = new VBox();
        vehiclePanel.setPadding(new Insets(25));
        vehiclePanel.setAlignment(Pos.TOP_CENTER);
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
        chooseVehicleComboBox = new ComboBox<>();
        chooseVehicleComboBox.getItems().addAll("Select Vehicle", "Passenger Aircraft", "Military Aircraft", "Passenger Ship", "Military Ship");
        chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
        chooseVehicleComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void initializeAirportComboBox(){
        airportComboBox = new ComboBox<>();
        airportComboBox.getItems().addAll("Select Airport");
        addAirportsToComboBox();
        airportComboBox.getSelectionModel().select("Select Airport");
        airportComboBox.setPrefSize(prefWidth, prefHeight);
    }

    private void resetVehiclePanel(){
        vehiclePanel.getChildren().clear();
        vehiclePanel.getChildren().add(0, chooseVehicleComboBox);
        chooseVehicleComboBox.getSelectionModel().select("Select Vehicle");
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
            label1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label10.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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
            label1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); label4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3); specificationPanel.getChildren().add(label4);
        }
        if (object instanceof CivilianAirport || object instanceof MilitaryAirport){
            Pair<Integer, Integer> coordinates = ((Airport) object).getCoordinates();
            Label label1 = createLabel(("Airport Name: " + ((Airport) object).getName()) + " Airport", "bg-1");
            Label label2 = createLabel(("Coordinates: " + "(" + coordinates.getKey() + ", " + coordinates.getValue()) + ")", "bg-1");
            Label label3 = createLabel(("Current serviced aircraft: " + ((Airport) object).getCurrentServicedAircraft()), "bg-1");
            label1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            specificationPanel.getChildren().add(label1); specificationPanel.getChildren().add(label2);
            specificationPanel.getChildren().add(label3);
        }
        BorderPane.setAlignment(specificationPanel, Pos.TOP_CENTER);
        borderPane.setCenter(specificationPanel);
        specificationPanel.setMinSize(245, 350);
    }

    private void addAirportsToComboBox(){
        List<Airport> listOfAirports = controlPanel.getListOfAirports();
        if (listOfAirports.isEmpty()){
            return;
        }
        for(Airport airport: controlPanel.getListOfAirports()){
            airportComboBox.getItems().add(airport.getName());
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
                try {
                if (splittedName[1].equals("Airport")) {
                        for (Airport item : controlPanel.getListOfAirports()) {
                            if (splittedName[0].equals(item.getName())) {
                                initializeSpecificationPanel(item);
                            }
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("SplittedName nie posiada indeksu 1");
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

    private void setVehiclePanelForPassengerAircraft(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, maximumAmountOfPassengersTextField);
        vehiclePanel.getChildren().add(3, amountOfStaffTextField);
        vehiclePanel.getChildren().add(4, maximumAmountOfFuelTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForMilitaryAircraft(){
        resetVehiclePanel();
        vehiclePanel.getChildren().add(1, airportComboBox);
        vehiclePanel.getChildren().add(2, typeOfArmsComboBox);
        vehiclePanel.getChildren().add(3, amountOfStaffTextField);
        vehiclePanel.getChildren().add(4, maximumAmountOfFuelTextField);
        vehiclePanel.getChildren().add(5, submitButton);
        submitButton.setOnAction(this);
    }

    private void setVehiclePanelForPassengerShip(){
        resetVehiclePanel();
    }

    private void setVehiclePanelForMilitaryShip(){
        resetVehiclePanel();
    }

    private boolean checkIfUserCanSubmit(){
//        try{
//            if (chooseVehicleComboBox.getValue().equals("Passenger Aircraft")){
//                Aircraft newAircraft = new PassengerAircraft()
//            }
//        }case (){}
        return false;
    }

    @Override
    public void handle(Event event){
        if (event.getSource() == submitButton){
            resetVehiclePanel();
        }

        if (event.getSource() == chooseVehicleComboBox){
            initializeAirportComboBox();
            if (chooseVehicleComboBox.getValue().equals("Passenger Aircraft")){
                setVehiclePanelForPassengerAircraft();
            }
            if (chooseVehicleComboBox.getValue().equals("Military Aircraft")){
                setVehiclePanelForMilitaryAircraft();
            }
            if (chooseVehicleComboBox.getValue().equals("Passenger Ship")){
                setVehiclePanelForPassengerShip();
            }
            if (chooseVehicleComboBox.getValue().equals("Military Ship")){
                setVehiclePanelForMilitaryShip();
            }
            if (chooseVehicleComboBox.getValue().equals("Select Vehicle")){
                //resetVehiclePanel();
            }
        }


//        if (event.getSource() == chooseVehicleComboBox){
//            submitButton.setDisable(chooseVehicleComboBox.getValue().equals("Select Vehicle") || airportComboBox.getValue().equals("Select Airport"));
//        }

//        if (event.getSource() == airportComboBox){
//            submitButton.setDisable(chooseVehicleComboBox.getValue().equals("Select Vehicle") || airportComboBox.getValue().equals("Select Airport"));
//        }
    }
}