package com.ahaviss.calculators.scicalc.functions;

import com.ahaviss.exceptions.CalculationException;

import java.math.BigDecimal;

@FunctionalInterface
public interface MultiBigDecimalFunction {
    BigDecimal calculate (BigDecimal... array) throws CalculationException;
}
