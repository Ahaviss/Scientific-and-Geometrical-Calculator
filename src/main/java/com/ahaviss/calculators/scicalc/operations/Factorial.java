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

import com.ahaviss.history.*;
import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.enums.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

public class Factorial {
    private static final LongFunction<BigInteger> function = number -> {
        if (number > 600000) {
            String answer = ProjectUtils.getValidString("Factorials over 600000 can take seconds, minutes, hours, or more (depending on size)\nContinue? Y/N");
            if (answer.equalsIgnoreCase("N")) {return null;}
            System.out.println("Proceeding (May take a while)...");
        }
        return LongStream.rangeClosed(1, number)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    };
    private static void printHelp () {
        System.out.println("Factorial");
        System.out.println("\"exit\": exits the current operation.");
        System.out.println("\"prev\": fetches the previous operation result from both calculators (e.g \"prev 5\").");
        System.out.println("\"help\": prints commands and current operation");
    }
    public static void factorial () {
        printHelp();
        while (true) {
            try {
                String tempNumbers = ProjectUtils.getValidString("Please enter a whole, positive number.");
                if (tempNumbers.trim().equalsIgnoreCase("exit")) return;
                if (tempNumbers.trim().equalsIgnoreCase("help")) {printHelp(); continue;}
                long userInput;
                if (tempNumbers.equalsIgnoreCase("prev")) {userInput = HistoryManager.getPrev().longValue();}
                else {userInput = Long.parseLong(tempNumbers);}
                if (userInput >= 0) {
                    BigInteger result = function.apply(userInput);
                    if (result == null) continue;
                    System.out.printf("Result: %d.%n", result);
                    BigDecimal bigDecimal = new BigDecimal(result);
                    HistoryManager.setPrev(bigDecimal);
                    HistoryManager.addHistory(new History(CalculatorType.SCIENTIFIC, TypeOfCalculation.FACTORIAL, bigDecimal));
                } else {
                    System.out.println("Error: Please enter a positive number.");
                }
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
