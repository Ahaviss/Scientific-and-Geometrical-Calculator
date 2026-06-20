package com.ahaviss.exceptions;

public class CalculationException extends Exception  {
    public CalculationException(String message) {
        super("Calculation Error: " + message);
    }
}
