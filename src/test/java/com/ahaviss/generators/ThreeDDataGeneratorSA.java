package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

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
                double a = 0;
                double b = 0;
                double c = 0;
                double d = 0;
                double e = 0;
                double expected = 0;
                switch (shape) {
                    case "cyl" -> {
                        a = 10 + (rng.nextDouble() * (50 - 10));
                        b = 10 + (rng.nextDouble() * (50 - 10));
                        expected = (2 * Math.PI * a * b) + (2 * Math.PI * a * a);
                    }
                    case "cube" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        expected = 6 * a * a;
                    }
                    case "pysq" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        expected = (a * b / 2.0 * 4.0) + (a * a);
                    }
                    case "recp" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        c = 10 + (rng.nextDouble() * (100 - 10));
                        expected = 2 * ((a * b) + (a * c) + (b * c));
                    }
                    case "trip" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        c = 10 + (rng.nextDouble() * (100 - 10));
                        d = 10 + (rng.nextDouble() * (100 - 10));
                        e = 10 + (rng.nextDouble() * (100 - 10));
                        expected = (a * b) + ((b + d + e) * c);
                    }
                }
                writer.printf(Locale.CANADA, "%f,%f,%f,%f,%f,%s,%f%n", a, b, c, d, e, shape, expected);
            }
            System.out.println("3D Surface Area Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
