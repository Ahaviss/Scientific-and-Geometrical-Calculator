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
package com.ahaviss.generators;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Random;
import static com.ahaviss.generators.utils.GeneratorUtils.getRandomBigDecimal;
public class SciCalcDataGenerator {
    public static void main(String[] args) {
        String filePath = "src/test/resources/scicalcinput.csv";
        String[] operators = {"+", "-", "*", "/", "^", "sqrt", "!"};
        Random rng = new Random();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int amountOfData;
            if (args != null && args.length == 2 && args[0].equals("-generateAllData")) amountOfData = Integer.parseInt(args[1]);
            else amountOfData = ProjectUtils.getValidInt("How many test cases would you like?", true);
            writer.println("inputA,inputB,operator,expectedResult");
            for (int i = 0; i < amountOfData; i++) {
                String op = operators[rng.nextInt(operators.length)];
                BigDecimal a = BigDecimal.ZERO;
                BigDecimal b = BigDecimal.ZERO;
                BigDecimal expected = BigDecimal.ZERO;

                // Handle operation bounds logically so you don't generate math errors
                switch (op) {
                    case "+" -> {
                        a = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        b = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        expected = a.add(b);
                    }
                    case "-" -> {
                        a = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        b = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        expected = a.subtract(b);
                    }
                    case "*" -> {
                        a = getRandomBigDecimal(new BigDecimal("10000.00"), new BigDecimal("100000000000.00"), 4);
                        b = getRandomBigDecimal(new BigDecimal("10000.00"), new BigDecimal("100000000000.00"), 4);
                        expected = a.multiply(b);
                    }
                    case "/" -> {
                        a = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        b = getRandomBigDecimal(new BigDecimal("100000.00"), new BigDecimal("10000000000000.00"), 4);
                        expected = a.divide(b, MathContext.DECIMAL128);
                    }
                    case "^" -> {
                        a = getRandomBigDecimal(new BigDecimal("100.00"), new BigDecimal("10000.00"), 4);
                        b = getRandomBigDecimal(new BigDecimal("100.00"), new BigDecimal("10000.00"), 4);
                        expected = BigDecimalMath.pow(a, b, MathContext.DECIMAL128);
                    }
                    case "sqrt" -> {
                        a = getRandomBigDecimal(new BigDecimal("10000.00"), new BigDecimal("100000000.00"), 4);
                        expected = a.sqrt(MathContext.DECIMAL128);
                    }
                    case "!" -> {
                        a = new BigDecimal(rng.nextInt(1000, 10000));
                        expected = new BigDecimal(calculateFactorial(a.longValue()));
                    }
                }

                // 3. Print the raw calculated row directly into your CSV file
                writer.printf(Locale.CANADA, "%s,%s,%s,%s%n", a.stripTrailingZeros().toPlainString(), b.stripTrailingZeros().toPlainString(), op, expected.stripTrailingZeros().setScale(2, RoundingMode.HALF_EVEN).toPlainString());
            }
            System.out.println("Scientific Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {e.printStackTrace();}
    }
    private static BigInteger calculateFactorial(long n) {
        BigInteger fact = BigInteger.ONE;
        for (long i = 1; i <= n; i++) fact = fact.multiply(BigInteger.valueOf(i));
        return fact;
    }
}
