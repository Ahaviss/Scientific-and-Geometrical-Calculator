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
import com.ahaviss.enums.CalculatorType;
import com.ahaviss.enums.TypeOfCalculation;
import com.ahaviss.history.History;
import com.ahaviss.history.HistoryManager;
import com.ahaviss.utils.ProjectUtils;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Exponents {
    private static final MultiBigDecimalFunction function = array -> {
        BigDecimal base = array[0];
        BigDecimal exponent = array[1];
        return BigDecimalMath.pow(base, exponent, new MathContext(34, RoundingMode.HALF_EVEN)).stripTrailingZeros();
    };
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
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                BigDecimal[] numbers = ProjectUtils.stringToBigDecimalArray(tempNumbers, HistoryManager.getPrev());
                if (numbers == null) continue;
                if (numbers.length != 2) {
                    System.out.println("Please enter at least two numbers (base and exponent) followed by a space (\"4 5\")");
                    continue;
                }
                BigDecimal base = numbers[0];
                BigDecimal exponent = numbers[1];
                BigDecimal result = function.calculate(numbers);
                System.out.printf("%s to the power %s is %s%n", base.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), exponent.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), result.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
                ProjectUtils.checkDecimal(result);
                HistoryManager.setPrev(result);
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.EXPONENTS, result));
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
