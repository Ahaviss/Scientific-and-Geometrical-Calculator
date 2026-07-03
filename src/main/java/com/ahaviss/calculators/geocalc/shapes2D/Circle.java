package com.ahaviss.calculators.geocalc.shapes2D;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;

import java.math.BigDecimal;
import java.math.MathContext;

public class Circle extends Shape2D {
    private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433");
    private final BigDecimal variable;
    private final RadiusOrDiameter radiusOrDiameter;
    public Circle (BigDecimal variable, RadiusOrDiameter radiusOrDiameter) {
        this.radiusOrDiameter = radiusOrDiameter;
        this.variable = variable;
    }
    @Override
    public BigDecimal area() {
        BigDecimal radius;
        if (radiusOrDiameter == RadiusOrDiameter.RADIUS) {
            radius = variable;
        } else {
            MathContext mc = MathContext.DECIMAL128;
            radius = variable.divide(BigDecimal.valueOf(2), mc);
        }
        return radius.multiply(radius).multiply(PI).stripTrailingZeros();
    }
}
