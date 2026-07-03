package com.ahaviss.calculators.geocalc.shapes2D;

import java.math.BigDecimal;

public class Square extends Shape2D {
    private final BigDecimal sideLength;
    public Square (BigDecimal sideLength) {
        this.sideLength = sideLength;
    }
    @Override
    public BigDecimal area () {return sideLength.multiply(sideLength).stripTrailingZeros();}
}
