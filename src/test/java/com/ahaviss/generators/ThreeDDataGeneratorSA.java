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
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.Random;
import static com.ahaviss.generators.utils.GeneratorUtils.getRandomBigDecimal;
import static com.ahaviss.generators.utils.GeneratorUtils.PI;
public class ThreeDDataGeneratorSA {
    public static void main(String[] args) {
        String filePath = "src/test/resources/3Dgeocalcinputsa.csv";
        String[] shapes = {"cube", "cyl", "pysq", "recp", "trip"};
        Random rng = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int amountOfData;
            if (args != null && args.length == 2 && args[0].equals("-generateAllData"))
                amountOfData = Integer.parseInt(args[1]);
            else amountOfData = ProjectUtils.getValidInt("How many test cases would you like?", true);
            writer.println("inputA,inputB,inputC,inputD,inputE,shape,expectedResult");
            for (int i = 0; i < amountOfData; i++) {
                String shape = shapes[rng.nextInt(shapes.length)];
                BigDecimal a = BigDecimal.ZERO;
                BigDecimal b = BigDecimal.ZERO;
                BigDecimal c = BigDecimal.ZERO;
                BigDecimal d = BigDecimal.ZERO;
                BigDecimal e = BigDecimal.ZERO;
                BigDecimal expected = BigDecimal.ZERO;
                switch (shape) {
                    case "cyl" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        BigDecimal lateral = BigDecimal.TWO.multiply(PI).multiply(a).multiply(b);
                        BigDecimal circles = BigDecimal.TWO.multiply(PI).multiply(a).multiply(a);
                        expected = lateral.add(circles);
                    }
                    case "cube" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = BigDecimal.valueOf(6).multiply(a).multiply(a);
                    }
                    case "pysq" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        BigDecimal triangles = a.multiply(b).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(4));
                        BigDecimal square = a.multiply(a);
                        expected = triangles.add(square);
                    }
                    case "recp" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        c = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        BigDecimal rect1 = a.multiply(b);
                        BigDecimal rect2 = a.multiply(c);
                        BigDecimal rect3 = b.multiply(c);
                        expected = rect1.add(rect2).add(rect3).multiply(BigDecimal.TWO);
                    }
                    case "trip" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        c = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        d = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        e = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        BigDecimal rectangles = b.add(d).add(e).multiply(c);
                        BigDecimal triangles = a.multiply(b);
                        expected = triangles.add(rectangles);
                    }
                }
                writer.printf(Locale.CANADA, "%s,%s,%s,%s,%s,%s,%s%n", a.stripTrailingZeros().toPlainString(), b.stripTrailingZeros().toPlainString(), c.stripTrailingZeros().toPlainString(), d.stripTrailingZeros().toPlainString(), e.stripTrailingZeros().toPlainString(), shape, expected.stripTrailingZeros().toPlainString());
            }
            System.out.println("3D Surface Area Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
