package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.calculators.scicalc.functions.MultiDoubleFunction;
import com.ahaviss.enums.CalculatorType;
import com.ahaviss.enums.TypeOfCalculation;
import com.ahaviss.history.History;
import com.ahaviss.history.HistoryManager;
import com.ahaviss.utils.ProjectUtils;

public class Exponents {
    private static final MultiDoubleFunction function = (double... array) -> {
        double base = array[0];
        double exponent = array[1];
        double result = Math.pow(base, exponent);
        if (Double.isNaN(result)) {
            throw new CalculationException("Result is Not a Number.");
        } else if (!Double.isFinite(result)) {
            throw new CalculationException("Overflow.");
        }
        return result;
    };
    private static void printHelp () {
        System.out.println("Exponents");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void exponents () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter the base and power followed by a space (\"4 5\")");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                double[] numbers = ProjectUtils.stringToDoubleArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length != 2) {
                    System.out.println("Please enter at least two numbers (base and exponent) followed by a space (\"4 5\")");
                    continue;
                }
                double base = numbers[0];
                double exponent = numbers[1];
                double result = function.calculate(numbers);
                System.out.printf("%.2f to the power %.2f is %.2f%n", base, exponent, result);
                ProjectUtils.checkDecimal(result);
                HistoryManager.setPrev(result);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.EXPONENTS, result));
            }
            catch (CalculationException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
