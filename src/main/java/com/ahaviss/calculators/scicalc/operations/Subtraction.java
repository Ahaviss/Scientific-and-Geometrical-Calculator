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

import com.ahaviss.calculators.scicalc.functions.MultiBigDecimalFunction;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Subtraction {
    private static final MultiBigDecimalFunction function = array -> {
        BigDecimal difference = array[0];
        int length = array.length;
        for (int i = 1; i < length; i++) {difference = difference.subtract(array[i]);}
        return difference.stripTrailingZeros();
    };
    private static void printHelp () {
        System.out.println("Subtraction");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void subtraction () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter all numbers followed by a space (\"4 5 6\")");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                BigDecimal[] numbers = ProjectUtils.stringToBigDecimalArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length < 2) {
                    System.out.println("Please enter at least two numbers followed by a space (\" \")");
                    continue;
                }
                BigDecimal difference = function.calculate(numbers);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.SUBTRACTION, difference));
                System.out.printf("Result: %s%n", difference.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
                ProjectUtils.checkDecimal(difference);
                HistoryManager.setPrev(difference);
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
