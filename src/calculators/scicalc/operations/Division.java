package calculators.scicalc.operations;

import history.*;
import enums.*;
import utils.ProjectUtils;

public class Division {
    private static void printHelp () {
        System.out.println("Addition");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void division () {
        System.out.println("Type \"exit\" to exit the division screen");
        while (true) {
            try {
                double quotient;
                String tempNumbers = ProjectUtils.getValidString("Please enter all numbers followed by a space (\"4 5 6\")");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) printHelp();
                double[] numbers = ProjectUtils.stringToDoubleArray(tempNumbers, HistoryManager.prev);
                if (numbers == null) continue;
                if (numbers.length < 2) {
                    System.out.println("Please enter at least two numbers followed by a space (\" \")");
                    continue;
                }
                quotient = numbers[0];
                int length = numbers.length;
                for (int i = 1; i < length; i++) {
                    double tempNumber =  numbers[i];
                    if (tempNumber == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        continue;
                    }
                    quotient /= numbers[i];
                }
                if (!Double.isFinite(quotient)) {
                    System.out.println("Overflow.");
                    break;
                }
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.DIVISION, quotient));
                System.out.printf("Result: %.2f%n", quotient);
                ProjectUtils.checkDecimal(quotient);
                HistoryManager.prev = quotient;
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
