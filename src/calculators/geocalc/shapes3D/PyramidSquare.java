package calculators.geocalc.shapes3D;

public class PyramidSquare extends Shape3D {
    private final double edge;
    private final double height;
    public PyramidSquare(double edge, double height) {
        this.edge = edge;
        this.height = height;
    }

    @Override
    public double volume() {
        return ((Math.pow(edge, 2) * height) / 3);
    }
    @Override
    public double surfaceArea() {
        double base = Math.pow(edge, 2);
        return ((((edge * height) / 2) * 4) + base);
    }
}
