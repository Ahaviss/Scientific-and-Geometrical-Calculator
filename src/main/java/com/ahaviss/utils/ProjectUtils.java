package com.ahaviss.utils;

import com.ahaviss.history.HistoryManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class ProjectUtils {
    private static Scanner scanner = new Scanner(System.in);
    static {scanner.useLocale(Locale.CANADA);}
    public static int getValidInt (String prompt, boolean checkBelow0) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                int input = Integer.parseInt(scanner.nextLine().trim());
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
                String input = scanner.nextLine().trim();
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
    public static BigDecimal getValidBigDecimal (String prompt, boolean checkBelow0) {
        while (true) {
            try {
                //Prints the given prompt
                System.out.println(prompt);
                String tempInput = scanner.nextLine().trim();
                if (tempInput.equalsIgnoreCase("prev")) return HistoryManager.getPrev();
                BigDecimal input = new BigDecimal(tempInput);
                //Checks if the input is positive and hasn't overflowed
                if (checkBelow0) {
                    if (input.compareTo(BigDecimal.ZERO) < 0) {
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
    public static void checkDecimal (BigDecimal answer) {
        if (answer.stripTrailingZeros().scale() > 2) {
            preciseDecimal(answer);
        }
    }
    public static void preciseDecimal (BigDecimal answer) {
        while (true) {
            String option = getValidString("Would you like to have more than two decimal places? (y/n)");

            if (option.equalsIgnoreCase("n")) {
                return;
            } else if (option.equalsIgnoreCase("y")) {
                break;
            } else {
                System.out.println("Invalid Input, please try again");
            }
        }
        while (true) {
            String decimalOption = getValidString("Would you like a specific amount of decimal places or the entire number?\n(specific amount/entire number)");
            if (decimalOption.equalsIgnoreCase("specific decimal")) {
                int amountOfDecimals = getValidInt("How many decimals?", true);
                System.out.printf("The new value is %s\n", answer.setScale(amountOfDecimals, RoundingMode.HALF_EVEN).stripTrailingZeros().toPlainString());
                return;
            } else if (decimalOption.equalsIgnoreCase("entire number")) {
                System.out.printf("The new value is %s\n", answer.toPlainString());
                return;
            } else {
                System.out.println("Invalid Input, please try again");
            }
        }

    }
    public static BigDecimal[] stringToBigDecimalArray (String input, BigDecimal prev) {
        try {
            String[] numbers = input.split("\\s+");
            int length = numbers.length;
            if (numbers == null || length == 0) {
                System.out.println("Invalid input. Numbers are empty");
                return null;
            }
            BigDecimal[] numbersToReturn = new BigDecimal[length];
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
                numbersToReturn[i] = new BigDecimal(numbers[i]);
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
