package com.ahaviss.testutils;

import com.ahaviss.utils.ProjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

public class TestUtils {
    private static final InputStream in = System.in;
    private static final PrintStream out = System.out;
    private static ByteArrayOutputStream capturedOutput;
    public static void prepareEnvironment(String input) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Field field = ProjectUtils.class.getDeclaredField("scanner");
            field.setAccessible(true);
            field.set(null, new Scanner(System.in));
            capturedOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(capturedOutput));
        }
        catch (Exception e) {e.printStackTrace();}
    }
    public static String getOutput() {
        if (capturedOutput == null) return "";
        return capturedOutput.toString();
    }
    public static void resetEnvironment() {
        System.setIn(in);
        System.setOut(out);
        capturedOutput = null;
    }
}
