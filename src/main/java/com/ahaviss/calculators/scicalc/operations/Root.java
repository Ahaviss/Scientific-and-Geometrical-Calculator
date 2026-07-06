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

import ch.obermuhlner.math.big.BigDecimalMath;
import com.ahaviss.calculators.scicalc.functions.MultiBigDecimalFunction;
import com.ahaviss.exceptions.CalculationException;
import com.ahaviss.history.*;
import com.ahaviss.enums.*;
import com.ahaviss.utils.ProjectUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;

public class Root {
    private static final Function<BigDecimal, BigDecimal> squareRoot = base -> base.sqrt(MathContext.DECIMAL128);
    private static final Function<BigDecimal, BigDecimal> cubeRoot = base -> BigDecimalMath.root(base, BigDecimal.valueOf(3), MathContext.DECIMAL128);
    private static final MultiBigDecimalFunction customRootFunction = array -> {
        int indexComparison = array[0].compareTo(BigDecimal.ZERO);
        if (indexComparison == 0) {throw new CalculationException("Invalid root index: Result is Infinity/Undefined.");}
        if (indexComparison < 0) {throw new CalculationException("Invalid root index: Root index cannot be less than 1.");}
        if (array[1].compareTo(BigDecimal.ZERO) == 0) {throw new CalculationException("Invalid root base: Result is Undefined");}
        return BigDecimalMath.root(array[1], array[0],  MathContext.DECIMAL128);
    };
    private static void printHelp () {
        System.out.println("Roots");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    private static BigDecimal customRootMethod (String tempNumbers) throws CalculationException {
        BigDecimal root;
        BigDecimal[] numbers = ProjectUtils.stringToBigDecimalArray(tempNumbers, HistoryManager.getPrev());
        if (numbers == null) return null;
        if (numbers.length != 2) {
            System.out.println("Please enter at two numbers (index and base) followed by a space (\"4 5\")");
            return null;
        }
        try {
            root = customRootFunction.calculate(numbers).stripTrailingZeros();
            System.out.printf("Result: %s%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
        }
        catch (ArithmeticException e) {
            numbers[1] = numbers[1].abs();
            root = customRootFunction.calculate(numbers).stripTrailingZeros();
            System.out.printf("Result: %s i%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
        }
        return root;
    }
    private static BigDecimal squareRootMethod (BigDecimal number) {
        BigDecimal root;
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal absoluteValue = number.abs();
            root = squareRoot.apply(absoluteValue).stripTrailingZeros();
            System.out.printf("Result: %s i%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
        } else {
            root = squareRoot.apply(number).stripTrailingZeros();
            System.out.printf("Result: %s%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
        }
        return root;
    }
    private static BigDecimal cubeRootMethod (BigDecimal number) {
        BigDecimal root = cubeRoot.apply(number);
        System.out.printf("Result: %s%n", root.setScale(2, RoundingMode.HALF_EVEN).toPlainString());
        return root;
    }
    public static void root() {
        printHelp();
        int typeOfRootCalculation = ProjectUtils.getValidInt("1 (Square root), 2 (Cube root), 3 (Custom root)", true);
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString(typeOfRootCalculation == 3 ? "Please enter the index of the root (2 for sqrt, 3 for cbrt) and base followed by a space (\"2 4\")" : "Please enter a number to find the root of");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                BigDecimal root;
                if (typeOfRootCalculation == 3) {
                    root = customRootMethod(tempNumbers);
                    if (root == null) continue;
                }
                else {
                    BigDecimal userInput;
                    if (tempNumbers.equalsIgnoreCase("prev")) {
                        userInput = HistoryManager.getPrev();
                    } else {
                        userInput = new BigDecimal(tempNumbers);
                    }
                    if (typeOfRootCalculation == 1) root = squareRootMethod(userInput);
                    else if (typeOfRootCalculation == 2) root = cubeRootMethod(userInput);
                    else {
                        System.out.println("Invalid input. Please try again.");
                        continue;
                    }
                }
                HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.ROOT, root));
                ProjectUtils.checkDecimal(root);
                HistoryManager.setPrev(root);
            }
            catch (CalculationException e) {
                System.out.println(e.getMessage());
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