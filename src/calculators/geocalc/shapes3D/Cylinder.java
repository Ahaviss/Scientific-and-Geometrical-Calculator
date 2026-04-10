package calculators.geocalc.shapes3D;
import calculators.geocalc.enums.RadiusOrDiameter;
public class Cylinder extends Shape3D {
    private final double variable;
    private final RadiusOrDiameter radiusOrDiameter;
    private final double height;
    public Cylinder (double variable, RadiusOrDiameter radiusOrDiameter, double height) {
        this.variable = variable;
        this.radiusOrDiameter = radiusOrDiameter;
        this.height = height;
    }
    @Override
    public double volume() {
        return ((Math.pow((radiusOrDiameter == RadiusOrDiameter.RADIUS ? variable : (variable / 2)), 2) * Math.PI) * height);
    }

    @Override
    public double surfaceArea() {
        return ((2 * (Math.pow(radiusOrDiameter == RadiusOrDiameter.DIAMETER ? variable : (variable / 2), 2) * Math.PI)) + (height * (Math.PI) * (radiusOrDiameter == RadiusOrDiameter.RADIUS ? (variable * 2) : variable)));
    }
}