package other;

import javafx.beans.property.DoubleProperty;
import javafx.util.Pair;

public class MyVector {
    private double x;
    private double y;
    private double magnitude;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public MyVector(Pair<Double, Double> A, Pair<Double, Double> B) {
        this.x = B.getKey() - A.getKey();
        this.y = B.getValue() - A.getValue();
        recalculateMagnitude();
    }

    public MyVector(MyVector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.magnitude = vector.magnitude;
    }

    public void recalculateMagnitude() {
        this.magnitude = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void multiplicationNormalizedVector(double number) {
        this.setX(this.getX() * number);
        this.setY(this.getY() * number);
    }

    public void normalise() {
        x = x / magnitude;
        y = y / magnitude;
    }


}
