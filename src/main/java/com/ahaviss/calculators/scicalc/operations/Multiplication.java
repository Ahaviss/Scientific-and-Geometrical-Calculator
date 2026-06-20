package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.calculators.scicalc.functions.MultiDoubleFunction;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

public class Multiplication {
    private static final MultiDoubleFunction function = (double... array) -> {
        double product = 1;
        for (double number : array) {product *= number;}
        if (!Double.isFinite(product)) {
            throw new CalculationException("Overflow.");
        }
        return product;
    };
    private static void printHelp () {
        System.out.println("Multiplication");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void multiplication () {
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
                double product = function.calculate(numbers);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.MULTIPLICATION, product));
                System.out.printf("Result: %.2f%n", product);
                ProjectUtils.checkDecimal(product);
                HistoryManager.setPrev(product);
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
