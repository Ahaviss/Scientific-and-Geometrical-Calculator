package calculators.geocalc.shapes3D;

public class Cube extends Shape3D {
    private final double edge;
    public Cube(double edge) {
        this.edge = edge;
    }
    @Override
    public double volume() {
        return Math.pow(edge, 3);
    }

    @Override
    public double surfaceArea() {
        return (Math.pow(edge, 2) * 6);
    }
}
