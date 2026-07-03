package com.ahaviss.calculators.geocalc.shapes3D;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class Cube extends Shape3D {
    private final BigDecimal edge;
    public Cube(BigDecimal edge) {
        this.edge = edge;
    }
    @Override
    public BigDecimal volume() {
        return BigDecimalMath.pow(edge, BigDecimal.valueOf(3), MathContext.DECIMAL128).stripTrailingZeros();
    }

    @Override
    public BigDecimal surfaceArea() {
        return edge.multiply(edge).multiply(BigDecimal.valueOf(6)).stripTrailingZeros();
    }
}
