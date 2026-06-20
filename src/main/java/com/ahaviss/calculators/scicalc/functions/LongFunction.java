package com.ahaviss.calculators.scicalc.functions;

import com.ahaviss.exceptions.CalculationException;

@FunctionalInterface
public interface LongFunction {
    long calculate (long number) throws CalculationException;
}
