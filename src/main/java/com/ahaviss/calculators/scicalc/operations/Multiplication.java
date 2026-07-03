package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.calculators.scicalc.functions.MultiBigDecimalFunction;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Multiplication {
    private static final MultiBigDecimalFunction function = array -> {
        BigDecimal product = BigDecimal.ONE;
        for (BigDecimal number : array) {product = product.multiply(number);}
        return product.stripTrailingZeros();
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
                BigDecimal[] numbers = ProjectUtils.stringToBigDecimalArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length < 2) {
                    System.out.println("Please enter at least two numbers followed by a space (\" \")");
                    continue;
                }
                BigDecimal product = function.calculate(numbers);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.MULTIPLICATION, product));
                System.out.printf("Result: %s%n", product.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
                ProjectUtils.checkDecimal(product);
                HistoryManager.setPrev(product);
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
