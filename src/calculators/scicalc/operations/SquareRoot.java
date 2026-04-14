package calculators.scicalc.operations;

import history.*;
import enums.*;
import utils.ProjectUtils;

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
                double userInput;
                if (tempNumbers.equalsIgnoreCase("prev")) {userInput = HistoryManager.getPrev();}
                else {userInput =  Double.parseDouble(tempNumbers);}
                double root;
                if (!Double.isFinite(userInput)) {
                    System.out.println("Overflow.");
                    return;
                }
                if (userInput < 0) {
                    double absoluteValue = Math.abs(userInput);
                    root = Math.sqrt(absoluteValue);
                    System.out.printf("The square root of %.2f is %.2f i.\n", userInput, root);
                } else {
                    root = Math.sqrt(userInput);
                    System.out.printf("The square root of %.2f is %.2f.\n", userInput, root);
                }
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.SQUARE_ROOT, root));
                ProjectUtils.checkDecimal(root);
                HistoryManager.setPrev(root);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}