package com.ahaviss.calculators.geocalc.shapes2D;

import java.math.BigDecimal;

public class Rectangle extends Shape2D {
    private final BigDecimal length;
    private final BigDecimal width;
    public Rectangle(BigDecimal length, BigDecimal width) {
        this.length = length;
        this.width = width;
    }
    @Override
    public BigDecimal area() {return length.multiply(width).stripTrailingZeros();}
}
