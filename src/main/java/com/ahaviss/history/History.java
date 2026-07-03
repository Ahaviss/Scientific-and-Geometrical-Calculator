package com.ahaviss.history;
import com.ahaviss.enums.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class History {
    private final CalculatorType calculatorType;
    private final TypeOfCalculation typeOfCalculation;
    private final BigDecimal result;
    public History (CalculatorType calculatorType, TypeOfCalculation typeOfCalculation, BigDecimal result) {
        this.calculatorType = calculatorType;
        this.typeOfCalculation = typeOfCalculation;
        this.result = result;
    }
    public void printHistory () {
        System.out.printf("%s: %s: %s%n", calculatorType.getCalculatorType(), typeOfCalculation.getTypeOfCalculation(), result.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}
