package com.ahaviss.calculators.scicalc.operations;

import com.ahaviss.calculators.scicalc.functions.LongFunction;
import com.ahaviss.history.*;
import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.enums.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Factors {
    private static final LongFunction function = (long number) -> {
        final List<Long> factors = new ArrayList<>();
        long numberOfFactors = 0;
        for (long i = 1; i * i <= number; i++) {
            if (number % i == 0) {
                numberOfFactors++;
                factors.add(i);
                if (i != number / i) {
                    numberOfFactors++;
                    long factor = number / i;
                    factors.add(factor);
                }
            }
        }
        System.out.printf("This number has %d factors.\n", numberOfFactors);
        if (numberOfFactors == 2) {
            System.out.println("This number is a prime number.");
        } else if (numberOfFactors == 0) {
            System.out.println("This number has no factors.");
        } else if (numberOfFactors == 1) {
            System.out.println("This number nor prime nor composite.");
        } else if (numberOfFactors > 2) {
            System.out.println("This number is composite.");
        }
        while (true) {
            String option = ProjectUtils.getValidString("View Factors? Y/N");
            if (option.equalsIgnoreCase("Y")) {
                Collections.sort(factors);
                factors.forEach(System.out::println);
                break;
            }
            else if (option.equalsIgnoreCase("N")) break;
            else System.out.println("Invalid option.");
        }
        return numberOfFactors;
    };
    private static void printHelp () {
        System.out.println("Find Factors");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void factors () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Enter a number to find its factors:");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                long userInput;
                if (tempNumbers.trim().equalsIgnoreCase("prev")) {
                    userInput = (long) HistoryManager.getPrev();
                }
                else {
                    userInput = Long.parseLong(tempNumbers);
                }
                long numberOfFactors = function.calculate(userInput);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORS, numberOfFactors));
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
