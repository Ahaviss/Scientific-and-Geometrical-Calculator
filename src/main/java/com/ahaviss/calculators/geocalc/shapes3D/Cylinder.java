package com.ahaviss.calculators.geocalc.shapes3D;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;

import java.math.BigDecimal;
import java.math.MathContext;

public class Cylinder extends Shape3D {
    private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433");
    private final BigDecimal variable;
    private final RadiusOrDiameter radiusOrDiameter;
    private final BigDecimal height;
    public Cylinder (BigDecimal variable, RadiusOrDiameter radiusOrDiameter, BigDecimal height) {
        this.variable = variable;
        this.radiusOrDiameter = radiusOrDiameter;
        this.height = height;
    }
    @Override
    public BigDecimal volume() {
        BigDecimal radius = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable
                : variable.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

        return radius.pow(2)
                .multiply(PI)
                .multiply(height);
    }

    @Override
    public BigDecimal surfaceArea() {
        BigDecimal radius = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable
                : variable.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

        BigDecimal diameter = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable.multiply(BigDecimal.valueOf(2))
                : variable;

        BigDecimal topAndBottom = BigDecimal.valueOf(2)
                .multiply(radius.pow(2))
                .multiply(PI);
        BigDecimal lateral = height.multiply(PI).multiply(diameter);
        return topAndBottom.add(lateral).stripTrailingZeros();
    }
}