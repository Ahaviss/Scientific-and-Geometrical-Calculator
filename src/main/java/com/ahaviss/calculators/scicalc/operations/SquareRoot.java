package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SquareRoot {
    private static void printHelp () {
        System.out.println("Square Root");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void squareRoot () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter a number to find the square root of.");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                BigDecimal userInput;
                if (tempNumbers.equalsIgnoreCase("prev")) {userInput = HistoryManager.getPrev();}
                else {userInput = new BigDecimal(tempNumbers);}
                BigDecimal root;
                if (userInput.compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal absoluteValue = userInput.abs();
                    root = absoluteValue.sqrt(new MathContext(34, RoundingMode.HALF_EVEN)).stripTrailingZeros();
                    System.out.printf("Result: %s i\n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
                } else {
                    root = userInput.sqrt(new MathContext(34, RoundingMode.HALF_EVEN)).stripTrailingZeros();
                    System.out.printf("Result: %s%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
                }
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.SQUARE_ROOT, root));
                ProjectUtils.checkDecimal(root);
                HistoryManager.setPrev(root);
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