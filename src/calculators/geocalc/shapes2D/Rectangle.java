package calculators.geocalc.shapes2D;

public class Rectangle extends Shape2D {
    private final double length;
    private final double width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    public double area() {return length * width;}
}
