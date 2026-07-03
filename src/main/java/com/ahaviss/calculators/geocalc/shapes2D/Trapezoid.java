package com.ahaviss.calculators.geocalc.shapes2D;

import java.math.BigDecimal;
import java.math.MathContext;

public class Trapezoid extends Shape2D {
    private final BigDecimal base1;
    private final BigDecimal base2;
    private final BigDecimal height;
    public Trapezoid (BigDecimal base1, BigDecimal base2, BigDecimal height) {
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
    }

    @Override
    public BigDecimal area() {return ((base1.add(base2)).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128)).multiply(height).stripTrailingZeros();}
}
