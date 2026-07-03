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

import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.MathContext;
import java.util.Locale;
import java.util.Random;
import java.math.BigDecimal;

import static com.ahaviss.generators.utils.GeneratorUtils.*;
public class TwoDDataGenerator {
    public static void main(String[] args) {
        String filePath = "src/test/resources/2Dgeocalcinput.csv";
        String[] shapes = {"cir", "rect", "sq", "trap", "tri"};
        Random rng = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int amountOfData;
            if (args != null && args.length == 2 && args[0].equals("-generateAllData")) amountOfData = Integer.parseInt(args[1]);
            else amountOfData = ProjectUtils.getValidInt("How many test cases would you like?", true);
            writer.println("inputA,inputB,inputC,shape,expectedResult");
            for (int i = 0; i < amountOfData; i++) {
                String shape = shapes[rng.nextInt(shapes.length)];
                BigDecimal a = BigDecimal.ZERO;
                BigDecimal b = BigDecimal.ZERO;
                BigDecimal c = BigDecimal.ZERO;
                BigDecimal expected = BigDecimal.ZERO;
                switch (shape) {
                    case "cir" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = PI.multiply(a).multiply(a);
                    }
                    case "sq" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.multiply(a);
                    }
                    case "rect" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a .multiply(b);
                    }
                    case "trap" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        c = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.add(b).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(c);
                    }
                    case "tri" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.multiply(b).divide(BigDecimal.TWO, MathContext.DECIMAL128);
                    }
                }
                writer.printf(Locale.CANADA, "%s,%s,%s,%s,%s%n", a.stripTrailingZeros().toPlainString(), b.stripTrailingZeros().toPlainString(), c.stripTrailingZeros().toPlainString(), shape, expected.stripTrailingZeros().toPlainString());
            }
            System.out.println("2D Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {e.printStackTrace();}
    }
}
