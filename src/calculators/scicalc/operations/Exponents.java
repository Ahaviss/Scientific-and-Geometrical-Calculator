package calculators.scicalc.operations;

import enums.CalculatorType;
import enums.TypeOfCalculation;
import history.History;
import history.HistoryManager;
import utils.ProjectUtils;

public class Exponents {
    private static void printHelp () {
        System.out.println("Exponents");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void exponents () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter the base and power followed by a space (\"4 5\")");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) printHelp();
                double[] numbers = ProjectUtils.stringToDoubleArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length != 2) {
                    System.out.println("Please enter at least two numbers (base and exponent) followed by a space (\"4 5\")");
                    continue;
                }
                double base = numbers[0];
                double exponent = numbers[1];
                double result = Math.pow(base, exponent);
                if (Double.isNaN(result)) {
                    System.out.println("Result is Not a Number.");
                    continue;
                } else if (!Double.isFinite(result)) {
                    System.out.println("Overflow.");
                    continue;
                }
                System.out.printf("%.2f to the power %.2f is %.2f\n", base, exponent, result);
                ProjectUtils.checkDecimal(result);
                HistoryManager.setPrev(result);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.EXPONENTS, result));
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
