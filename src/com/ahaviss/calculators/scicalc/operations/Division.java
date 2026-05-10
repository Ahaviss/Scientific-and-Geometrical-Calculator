package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.calculators.scicalc.functions.MultiDoubleFunction;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

public class Division {
    private static final MultiDoubleFunction function = (double... array) -> {
        double quotient = array[0];
        int length = array.length;
        for (int i = 1; i < length; i++) {
            double tempNumber = array[i];
            if (tempNumber == 0) {
                throw new CalculationException("Division by zero is not allowed.");
            }
            quotient /= array[i];
        }
        if (!Double.isFinite(quotient)) {
            throw new CalculationException("Overflow.");
        }
        return quotient;
    };
    private static void printHelp () {
        System.out.println("Division");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void division () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter all numbers followed by a space (\"4 5 6\")");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                double[] numbers = ProjectUtils.stringToDoubleArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length < 2) {
                    System.out.println("Please enter at least two numbers followed by a space (\" \")");
                    continue;
                }
                double quotient = function.calculate(numbers);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.DIVISION, quotient));
                System.out.printf("Result: %.2f%n", quotient);
                ProjectUtils.checkDecimal(quotient);
                HistoryManager.setPrev(quotient);
            }
            catch (CalculationException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Skipping current input...");
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
