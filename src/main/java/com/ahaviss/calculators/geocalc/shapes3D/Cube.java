package com.ahaviss.calculators.geocalc.shapes3D;

public class Cube extends Shape3D {
    private final double edge;
    public Cube(double edge) {
        this.edge = edge;
    }
    @Override
    public double volume() {
        return edge * edge * edge;
    }

    @Override
    public double surfaceArea() {
        return edge * edge * 6;
    }
}
