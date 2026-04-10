package calculators.geocalc.shapes3D;

public class RecPrism extends Shape3D {
    private final double width;
    private final double length;
    private final double height;
    public RecPrism(double width, double length, double height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    @Override
    public double volume() {
        return (width * length * height);
    }

    @Override
    public double surfaceArea() {
        return 2 * ((width * length) + (width * height) + (length * height));
    }
}
