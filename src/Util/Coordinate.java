package Util;

public class Coordinate {

    private int valueX;
    private int valueY;

    public Coordinate(int valueX, int valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
    }

    public int getValueX() {
        return valueX;
    }

    public int getValueY() {
        return valueY;
    }

    public void setValueX(int valueX) {
        this.valueX = valueX;
    }

    public void setValueY(int valueY) {
        this.valueY = valueY;
    }

    @Override
    public String toString(){
        return "X: " + this.getValueX() + " Y: " + this.getValueY();
    }
}
