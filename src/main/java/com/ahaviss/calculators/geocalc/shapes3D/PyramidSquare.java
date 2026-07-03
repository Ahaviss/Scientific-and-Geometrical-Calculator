package com.ahaviss.calculators.geocalc.shapes3D;

import java.math.BigDecimal;
import java.math.MathContext;

public class PyramidSquare extends Shape3D {
    private final BigDecimal edge;
    private final BigDecimal height;
    public PyramidSquare(BigDecimal edge, BigDecimal height) {
        this.edge = edge;
        this.height = height;
    }

    @Override
    public BigDecimal volume() {
        return (edge.multiply(edge).multiply(height).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128)).stripTrailingZeros();
    }
    @Override
    public BigDecimal surfaceArea() {
        BigDecimal base = edge.multiply(edge);
        return (edge.multiply(height).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(4)).add(base)).stripTrailingZeros();
    }
}
