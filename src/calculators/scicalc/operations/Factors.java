package calculators.scicalc.operations;

import history.*;
import utils.ProjectUtils;
import enums.*;
public class Factors {
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
                long userInput = Long.parseLong(tempNumbers);
                if (userInput > Integer.MAX_VALUE) {
                    System.out.println("Overflow.");
                    return;
                }
                int numberOfFactors = 0;
                for (long i = 1; i <= userInput; i++) {
                    if (userInput % i == 0) {
                        System.out.printf("%d is a factor of %d.\n", i, userInput);
                        numberOfFactors++;
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
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORS, numberOfFactors));
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
