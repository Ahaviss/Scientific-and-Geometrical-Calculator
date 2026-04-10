package calculators.scicalc.operations;

import history.*;
import enums.*;
import utils.ProjectUtils;

public class Multiplication {
    public static void multiplication () {
        System.out.println("Type \"exit\" to exit the multiplication screen");
        while (true) {
            try {
                double product = 1;
                String tempNumbers = ProjectUtils.getValidString("Please enter all numbers followed by a space (\"4 5 6\")");
                if (tempNumbers.equalsIgnoreCase("exit")) return;
                double[] numbers = ProjectUtils.stringToDoubleArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length < 2) {
                    System.out.println("Please enter at least two numbers followed by a space (\" \")");
                    continue;
                }
                for (double number: numbers) {product *= number;}
                if (!Double.isFinite(product)) {
                    System.out.println("Overflow.");
                    break;
                }
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.MULTIPLICATION, product));
                System.out.printf("Result: %.2f%n", product);
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
