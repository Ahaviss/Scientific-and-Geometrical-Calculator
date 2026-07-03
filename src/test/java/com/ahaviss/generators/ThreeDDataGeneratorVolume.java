package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.Random;

import static com.ahaviss.generators.utils.GeneratorUtils.*;

public class ThreeDDataGeneratorVolume {
    public static void main(String[] args) {
        String filePath = "src/test/resources/3Dgeocalcinputvolume.csv";
        String[] shapes = {"cube", "cyl", "pysq", "recp", "trip"};
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
                    case "cyl" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = PI.multiply(a).multiply(a).multiply(b);
                    }
                    case "cube" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.pow(3, MathContext.DECIMAL128);
                    }
                    case "pysq" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.multiply(a).multiply(b).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128);
                    }
                    case "recp" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        c = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.multiply(b).multiply(c);
                    }
                    case "trip" -> {
                        a = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        b = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        c = getRandomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000000), 4);
                        expected = a.multiply(b).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(c);
                    }
                }
                writer.printf(Locale.CANADA, "%s,%s,%s,%s,%s%n", a.stripTrailingZeros().toPlainString(), b.stripTrailingZeros().toPlainString(), c.stripTrailingZeros().toPlainString(), shape, expected.stripTrailingZeros().toPlainString());
            }
            System.out.println("3D Volume Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {e.printStackTrace();}
    }
}
