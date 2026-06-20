package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

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
                double a = 0;
                double b = 0;
                double expected = 0;

                // Handle operation bounds logically so you don't generate math errors
                switch (op) {
                    case "+" -> {
                        a = 1000 + (rng.nextDouble() * (1000000 - 1000));
                        b = 1000 + (rng.nextDouble() * (1000000 - 1000));
                        expected = a + b;
                    }
                    case "-" -> {
                        a = 1000 + (rng.nextDouble() * (1000000 - 1000));
                        b = 1000 + (rng.nextDouble() * (1000000 - 1000));
                        expected = a - b;
                    }
                    case "*" -> {
                        a = 10 + (rng.nextDouble() * (100 - 10));
                        b = 10 + (rng.nextDouble() * (100 - 10));
                        expected = a * b;
                    }
                    case "/" -> {
                        a = 1000 + (rng.nextDouble() * (100000 - 1000));
                        b = rng.nextInt(1000) + 1;
                        expected = a / b;
                    }
                    case "^" -> {
                        a = rng.nextInt(10) + 1;
                        b = rng.nextInt(10) + 1;
                        expected = Math.pow(a, b);
                    }
                    case "sqrt" -> {
                        a = 1 + (rng.nextDouble() * (10000 - 100));
                        b = 0;
                        expected = Math.sqrt(a);
                    }
                    case "!" -> {
                        a = rng.nextInt(20);
                        b = 0;
                        expected = calculateFactorial((long) a);
                    }
                }

                // 3. Print the raw calculated row directly into your CSV file
                writer.printf(Locale.CANADA, "%.4f,%.4f,%s,%.2f%n", a, b, op, expected);
            }
            System.out.println("Scientific Generator: Successfully generated " + amountOfData + " random cases in: " + filePath);

        } catch (IOException e) {e.printStackTrace();}
    }
    private static double calculateFactorial(long n) {
        double fact = 1;
        for (long i = 1; i <= n; i++) fact *= i;
        return fact;
    }
}
