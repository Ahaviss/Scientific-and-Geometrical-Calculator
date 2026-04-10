package calculators.geocalc.shapes2D;

public class Trapezoid extends Shape2D {
    private final double base1;
    private final double base2;
    private final double height;
    public Trapezoid (double base1, double base2, double height) {
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
    }

    @Override
    public double area() {return ((base1 + base2) / 2) * height;}
}
