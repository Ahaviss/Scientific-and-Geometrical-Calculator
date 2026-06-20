package com.ahaviss.calculators.scicalc.functions;

import com.ahaviss.exceptions.CalculationException;

@FunctionalInterface
public interface MultiDoubleFunction {
    double calculate (double... array) throws CalculationException;
}
