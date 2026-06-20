package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.calculators.scicalc.functions.LongFunction;
import com.ahaviss.history.*;
import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.enums.*;
public class Factorial {
    private static final LongFunction function = (long number) -> {
        long result = 1;
        for (long i = 1; i <= number; i++) {
            if (result > Long.MAX_VALUE / i) {
                throw new CalculationException("Factorial is too large");
            }
            result *= i;
        }
        return result;
    };
    private static void printHelp () {
        System.out.println("Factorial");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void factorial () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter a whole, positive number.");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                long userInput;
                if (tempNumbers.equalsIgnoreCase("prev")) {userInput = (long) HistoryManager.getPrev();}
                else {userInput = Long.parseLong(tempNumbers);}
                if (userInput >= 0) {
                    long result = function.calculate(userInput);
                    System.out.printf("Result: %d.%n", result);
                    HistoryManager.setPrev((double) result);
                    HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORIAL, (double) result));
                } else {
                    System.out.println("Error: Please enter a positive number.");
                }
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
