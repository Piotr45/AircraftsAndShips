package vehicles;

import enumerates.typesOfArms;
import javafx.util.Pair;

public abstract class Ship {

    // Variables

    private int id;
    private Pair<Integer, Integer> coordinates;
    private int maximumAmountOfPassengers;
    private int currentAmountOfPassengers;
    private String firmName; // name firm changed to firmName
    private int speed;
    private typesOfArms typeOfArms;

    public Ship(int id, Pair<Integer, Integer> coordinates, int maximumAmountOfPassengers, int currentAmountOfPassengers, String firmName, int speed) {
        setId(id);
        setCoordinates(coordinates);
        setMaximumAmountOfPassengers(maximumAmountOfPassengers);
        setCurrentAmountOfPassengers(currentAmountOfPassengers);
        setFirmName(firmName);
        setSpeed(speed);
    }

    public Ship() {
        setId(-1);
        setCoordinates(null);
        setMaximumAmountOfPassengers(0);
        setCurrentAmountOfPassengers(0);
        setFirmName(null);
        setSpeed(0);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public int getMaximumAmountOfPassengers() {
        return maximumAmountOfPassengers;
    }

    public void setMaximumAmountOfPassengers(int maximumAmountOfPassengers) {
        this.maximumAmountOfPassengers = maximumAmountOfPassengers;
    }

    public int getCurrentAmountOfPassengers() {
        return currentAmountOfPassengers;
    }

    public void setCurrentAmountOfPassengers(int currentAmountOfPassengers) {
        this.currentAmountOfPassengers = currentAmountOfPassengers;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public typesOfArms getTypeOfArms() {
        return typeOfArms;
    }

    public void setTypeOfArms(typesOfArms typeOfArms) {
        this.typeOfArms = typeOfArms;
    }

    // Methods

    public void shuttle(){}
}
