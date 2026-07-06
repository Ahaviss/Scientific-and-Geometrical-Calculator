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

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.LongFunction;

public class Factorial {
    // Threshold to prevent thread creation overhead for small ranges
    private static final int PARALLEL_THRESHOLD = 10_000;

    // Share a single pool instance across calls
    private static final ForkJoinPool pool = ForkJoinPool.commonPool();

    private static final LongFunction<BigInteger> function = number -> {
        if (number > 600000) {
            String answer = ProjectUtils.getValidString(
                    "Factorials over 600000 can take seconds, minutes, hours, or more (depending on size)\nContinue? Y/N"
            );
            if (answer.equalsIgnoreCase("N")) {
                return null;
            }
            System.out.println("Proceeding (May take a while)...");
        }

        // Invoke the ForkJoin task using the pool
        return pool.invoke(new FactorialTask(1, number));
    };

    // ForkJoin RecursiveTask implementation
    private static class FactorialTask extends RecursiveTask<BigInteger> {
        private final long start;
        private final long end;

        FactorialTask(long start, long end) {
            // Ensure bounds are valid
            this.start = Math.max(1, start);
            this.end = end;
        }

        @Override
        protected BigInteger compute() {
            // Base cases
            if (start > end) return BigInteger.ONE;
            if (start == end) return BigInteger.valueOf(start);
            if (end - start == 1) return BigInteger.valueOf(start).multiply(BigInteger.valueOf(end));

            // Sequential fallback threshold to avoid excessive context switching
            if ((end - start) <= PARALLEL_THRESHOLD) {
                return binSplitFactorialSeq(start, end);
            }

            long mid = start + (end - start) / 2;

            FactorialTask leftTask = new FactorialTask(start, mid);
            FactorialTask rightTask = new FactorialTask(mid + 1, end);

            // Fork the left task asynchronously
            leftTask.fork();

            // Compute the right task in the current thread
            BigInteger rightResult = rightTask.compute();

            // Join the left task result
            BigInteger leftResult = leftTask.join();

            // Multiply balanced BigInteger results
            return leftResult.multiply(rightResult);
        }
    }

    // Fast sequential binary split fallback
    private static BigInteger binSplitFactorialSeq(long start, long end) {
        if (start > end) return BigInteger.ONE;
        if (start == end) return BigInteger.valueOf(start);
        if (end - start == 1) return BigInteger.valueOf(start).multiply(BigInteger.valueOf(end));

        long mid = start + (end - start) / 2;
        return binSplitFactorialSeq(start, mid).multiply(binSplitFactorialSeq(mid + 1, end));
    }
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
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(System.out), 1 << 20  // 1MB buffer
                    );
                    writer.write(result.toString());
                    writer.newLine();
                    writer.flush();
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
