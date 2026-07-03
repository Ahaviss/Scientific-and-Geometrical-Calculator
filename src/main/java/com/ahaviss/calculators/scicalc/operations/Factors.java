/*
 * Copyright [2026] [Ahaviss]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ahaviss.calculators.scicalc.operations;

import java.math.BigDecimal;
import java.util.function.LongFunction;
import com.ahaviss.history.*;
import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.enums.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Factors {
    private static final LongFunction<Long> function = number -> {
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
        System.out.printf("This number has %d factors.%n", numberOfFactors);
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
                    userInput = HistoryManager.getPrev().longValue();
                }
                else {
                    userInput = Long.parseLong(tempNumbers);
                }
                long numberOfFactors = function.apply(userInput);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORS, new BigDecimal(numberOfFactors)));
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
