package com.ahaviss.calculators.geocalc.shapes2D;

import java.math.BigDecimal;
import java.math.MathContext;

public class Triangle extends Shape2D {
    private final BigDecimal base;
    private final BigDecimal height;
    public Triangle (BigDecimal base, BigDecimal height) {
        this.base = base;
        this.height = height;
    }
    @Override
    public BigDecimal area () {return (base.multiply(height)).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128).stripTrailingZeros();}
}
