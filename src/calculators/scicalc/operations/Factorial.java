package calculators.scicalc.operations;

import history.*;
import utils.ProjectUtils;
import enums.*;
public class Factorial {
    private static void printHelp () {
        System.out.println("Addition");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void factorial () {
        System.out.println("Type \"exit\" to exit the factorial screen");
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter a whole, positive number.");
                if (tempNumbers.equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) printHelp();
                long userInput = Long.parseLong(tempNumbers);
                if (userInput >= 0) {
                    long result = 1;
                    for (long i = 1; i <= userInput; i++) {
                        if (result > Long.MAX_VALUE / i) {
                            System.out.println("Overflow.");
                            return;
                        }
                        result *= i;
                    }
                    System.out.printf("The result is %d.\n", result);
                    HistoryManager.prev = (double) result;
                    HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORIAL, (double) result));
                } else {
                    System.out.println("Error: Please enter a positive number.");
                }
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
