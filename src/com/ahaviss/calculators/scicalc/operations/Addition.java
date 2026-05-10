package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.calculators.scicalc.functions.MultiDoubleFunction;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

public class Addition {
    private static final MultiDoubleFunction function = (double... array) -> {
        double sum = 0;
        for (double number : array) {sum += number;}
        if (!Double.isFinite(sum)) {
            throw new CalculationException("Overflow.");
        }
        return sum;
    };
    private static void printHelp () {
        System.out.println("Addition");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void addition () {
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
                double sum = function.calculate(numbers);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.ADDITION, sum));
                System.out.printf("Result: %.2f%n", sum);
                ProjectUtils.checkDecimal(sum);
                HistoryManager.setPrev(sum);
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
