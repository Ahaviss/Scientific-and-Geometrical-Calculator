package com.ahaviss.calculators.geocalc.shapes2D;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;
public class Circle extends Shape2D {
    private final double variable;
    private final RadiusOrDiameter radiusOrDiameter;
    public Circle (double variable, RadiusOrDiameter radiusOrDiameter) {
        this.radiusOrDiameter = radiusOrDiameter;
        this.variable = variable;
    }
    @Override
    public double area () {return radiusOrDiameter == RadiusOrDiameter.RADIUS ? (Math.pow(variable, 2) * Math.PI) : (Math.pow((variable / 2), 2) * Math.PI);}
}
