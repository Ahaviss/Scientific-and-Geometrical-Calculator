package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

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
                double a = 0;
                double b = 0;
                double c = 0;
                double expected = 0;
                switch (shape) {
                    case "cyl" -> {
                        a = 10 + (rng.nextDouble() * (50 - 10));
                        b = 10 + (rng.nextDouble() * (50 - 10));
                        expected = Math.PI * a * a * b;
                    }
                    case "cube" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        expected = a * a * a;
                    }
                    case "pysq" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        expected = (a * a * b) / 3.0;
                    }
                    case "recp" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        c = 10 + (rng.nextDouble() * (100 - 10));
                        expected = a * b * c;
                    }
                    case "trip" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        c = 10 + (rng.nextDouble() * (100 - 10));
                        expected = a * b / 2 * c;
                    }
                }
                writer.printf(Locale.CANADA, "%f,%f,%f,%s,%f%n", a, b, c, shape, expected);
            }
            System.out.println("3D Volume Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {e.printStackTrace();}
    }
}
