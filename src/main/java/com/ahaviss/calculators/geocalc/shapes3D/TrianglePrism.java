package com.ahaviss.calculators.geocalc.shapes3D;

import java.math.BigDecimal;
import java.math.MathContext;

public class TrianglePrism extends Shape3D {
    private final BigDecimal height;
    private final BigDecimal base;
    private BigDecimal side2 = BigDecimal.ZERO;
    private BigDecimal side3 = BigDecimal.ZERO;
    private final BigDecimal length;
    public TrianglePrism(BigDecimal height, BigDecimal base, BigDecimal side2, BigDecimal side3, BigDecimal length) {
        this.height = height;
        this.base = base;
        this.length = length;
        this.side2 = side2;
        this.side3 = side3;
    }
    public TrianglePrism (BigDecimal height, BigDecimal base, BigDecimal length) {
        this.height = height;
        this.base = base;
        this.length = length;
    }
    @Override
    public BigDecimal volume() {
        return (base.multiply(height).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(length)).stripTrailingZeros();
    }
    @Override
    public BigDecimal surfaceArea () {
        BigDecimal sidesAdded = base.add(side2).add(side3);
        BigDecimal triangles = base.multiply(height);
        return (triangles.add(sidesAdded.multiply(length))).stripTrailingZeros();
    }
}
