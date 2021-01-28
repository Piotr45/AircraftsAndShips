package other;

import javafx.beans.property.DoubleProperty;
import javafx.util.Pair;

/**
 * Class MyVector is used to describe vector.
 */
public class MyVector {
    private double x;
    private double y;
    private double magnitude;

    /**
     * Gets x coordinate of vector.
     * @return - returns x coordinate of vector.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y coordinate of vector.
     * @return - returns y coordinate of vector.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets magnitude of vector.
     * @return - return magnitude of vector.
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Sets value to x coordinate.
     * @param x - new value that will be assigned to x coordinate of vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets value to y coordinate.
     * @param y - new value that will be assigned to y coordinate of vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets new magnitude of vector.
     * @param magnitude - new magnitude value that will be assigned to vectors magnitude.
     */
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    /**
     * MyVector constructor with two params.
     * @param A - first pair of coordinates.
     * @param B - second pair of coordinates.
     */
    public MyVector(Pair<Double, Double> A, Pair<Double, Double> B) {
        this.x = B.getKey() - A.getKey();
        this.y = B.getValue() - A.getValue();
        recalculateMagnitude();
    }

    /**
     * MyVector constructor with one param. We are using it to copy vector passed as an argument.
     * @param vector - instance of MyVector class.
     */
    public MyVector(MyVector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.magnitude = vector.magnitude;
    }

    /**
     * This method calculates magnitude of vector.
     */
    public void recalculateMagnitude() {
        this.magnitude = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * This function multiplies vector coordinates by value passed to this function.
     * @param number - value used to multiply vector coordinates.
     */
    public void multiplicationNormalizedVector(double number) {
        this.setX(this.getX() * number);
        this.setY(this.getY() * number);
    }

    /**
     * This method normalise vector.
     */
    public void normalise() {
        x = x / magnitude;
        y = y / magnitude;
    }


}
