package calculators.geocalc.shapes3D;

public class TrianglePrism extends Shape3D {
    private final double height;
    private final double base;
    private double side2 = 0;
    private double side3 = 0;
    private final double length;
    public TrianglePrism(double height, double base, double side2, double side3, double length) {
        this.height = height;
        this.base = base;
        this.length = length;
        this.side2 = side2;
        this.side3 = side3;
    }
    public TrianglePrism (double height, double base, double length) {
        this.height = height;
        this.base = base;
        this.length = length;
    }
    @Override
    public double volume() {
        return (((base * height) / 2) * length);
    }
    @Override
    public double surfaceArea () {
        return ((base * height) + ((base + side2 + side3) * length));
    }
}
