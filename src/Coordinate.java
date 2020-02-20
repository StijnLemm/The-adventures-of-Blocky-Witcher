public class Coordinate {

    private double valueX;
    private double valueY;

    public Coordinate(double valueX, double valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
    }

    public double getValueX() {
        return valueX;
    }

    public double getValueY() {
        return valueY;
    }

    public void setValueX(double valueX) {
        this.valueX = valueX;
    }

    public void setValueY(double valueY) {
        this.valueY = valueY;
    }

    @Override
    public String toString(){
        return "X: " + this.getValueX() + " Y: " + this.getValueY();
    }
}
