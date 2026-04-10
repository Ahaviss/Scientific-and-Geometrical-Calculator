package utils;

import java.util.Scanner;

public class ProjectUtils {
    private static final Scanner scanner = new Scanner(System.in);
    public static int getValidInt (String prompt, boolean checkBelow0) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                if (checkBelow0) {
                    if (input < 0) {
                        System.out.println("Number cannot be below 0.");
                        continue;
                    }
                }
                //Returns the input
                return input;
                //Catch invalid input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (Exception e) {
                System.out.printf("An unexpected error occurred: %s%n", e.getMessage());
            }
        }
    }
    public static String getValidString (String prompt) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                String input = scanner.nextLine();
                //Checks if the input is empty
                if (input == null || input.isEmpty()) {
                    System.out.println("Invalid input. Please enter a non-empty sentence/word.");
                    continue;
                }

                //Returns the input
                return input;
                //Catch invalid input
            } catch (Exception e) {
                System.out.printf("An unexpected error occurred: %s%n", e.getMessage());
            }
        }
    }
    public static double getValidDouble (String prompt, boolean checkBelow0) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                double input = Double.parseDouble(scanner.nextLine());
                //Checks if the input is positive and hasn't overflowed
                if (Double.isNaN(input) || !Double.isFinite(input)) {
                    System.out.println("Invalid input. Please enter a positive number.");
                    continue;
                }
                if (checkBelow0) {
                    if (input < 0) {
                        System.out.println("Number cannot be below 0.");
                        continue;
                    }
                }
                //Returns the input
                return input;
            }
            //Catch invalid input
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred: %s%n", e.getMessage());
            }
        }
    }
    public static void checkDecimal (double answer) {
        if (Math.abs(answer - Math.round(answer)) > 0.0000001) {
            preciseDecimal(answer);
        }
    }
    public static void preciseDecimal (double answer) {
        while (true) {
            String option = getValidString("Would you like to have a specific decimal amount? (y/n)");

            if (option.equalsIgnoreCase("n")) {
                return;
            } else if (option.equalsIgnoreCase("y")) {
                break;
            } else {
                System.out.println("Invalid Input, please try again");
            }
        }
        while (true) {
            String decimalOption = getValidString("Would you like a specific decimal or the entire number?");
            if (decimalOption.equalsIgnoreCase("specific decimal")) {
                int amountOfDecimals = getValidInt("How many decimals?", true);
                long amountToMultiply = (long) Math.pow(10, amountOfDecimals);
                double tempValue = Math.round(answer * amountToMultiply);
                double returnValue = tempValue / amountToMultiply;
                System.out.printf("The new value is %f.\n", returnValue);
                return;
            } else if (decimalOption.equalsIgnoreCase("entire number")) {
                System.out.printf("The new value is %f.\n", answer);
                return;
            } else {
                System.out.println("Invalid Input, please try again");
            }
        }

    }
    public static double[] stringToDoubleArray (String input, double prev) {
        try {
            String[] numbers = input.split("\\s+");
            int length = numbers.length;
            if (numbers == null || length == 0) {
                System.out.println("Invalid input. Numbers are empty");
                return null;
            }
            double[] numbersToReturn = new double[length];
            for (int i = 0; i < length; i++) {
                numbers[i] = numbers[i].trim();
                if (numbers[i] == null || numbers[i].isEmpty()) {
                    System.out.printf("\"%s\" is not a number. Please try again%n", numbers[i]);
                    return null;
                }
                if (numbers[i].equalsIgnoreCase("prev")) {
                    numbersToReturn[i] = prev;
                    continue;
                }
                numbersToReturn[i] = Double.parseDouble(numbers[i]);
            }
            return numbersToReturn;
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again");
            return null;
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }
    public static long getValidLong (String prompt) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                long input = Long.parseLong(scanner.nextLine());
                //Checks if the input is positive
                if (input < 0) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    continue;
                }
                //Returns the input
                return input;
                //Catch invalid input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (Exception e) {
                System.out.printf("An unexpected error occurred: %s%n", e.getMessage());
            }
        }
    }
}
