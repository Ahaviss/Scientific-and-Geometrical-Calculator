package com.ahaviss.calculators.geocalc.shapes3D;

import java.math.BigDecimal;

public class RecPrism extends Shape3D {
    private final BigDecimal width;
    private final BigDecimal length;
    private final BigDecimal height;
    public RecPrism(BigDecimal width, BigDecimal length, BigDecimal height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    @Override
    public BigDecimal volume() {
        return (width.multiply(length).multiply(height)).stripTrailingZeros();
    }

    @Override
    public BigDecimal surfaceArea() {
        BigDecimal first = width.multiply(length);
        BigDecimal second = height.multiply(width);
        BigDecimal third = length.multiply(height);
        return BigDecimal.valueOf(2).multiply(first.add(second).add(third)).stripTrailingZeros();
    }
}
