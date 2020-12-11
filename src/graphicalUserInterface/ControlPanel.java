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

public class ControlPanel extends Application {

    @FXML
    private TextField test;

    @FXML
    private ComboBox<String> entityHandler;

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


    // JavaFX methods

    public void initialize(){
        entityHandler.getItems().addAll("Aircraft", "Ship");
        entityHandler.getSelectionModel().select("Aircraft");
    }

    public static BorderPane createControlPane(){
        entityHandler.getItems().addAll("Aircraft", "Ship");
        entityHandler.getSelectionModel().select("Aircraft");

        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
        borderPane.setPadding(new Insets(5));

        return borderPane;
    }

    private static Label createLabel(String text, String styleClass){
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }
}
