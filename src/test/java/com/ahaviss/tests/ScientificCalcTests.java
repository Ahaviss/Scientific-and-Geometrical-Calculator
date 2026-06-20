package com.ahaviss.tests;

import com.ahaviss.testutils.TestUtils;
import com.ahaviss.calculators.scicalc.operations.*;
import com.ahaviss.utils.ProjectUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
@Timeout(5)
class ScientificCalcTests {
    @BeforeAll
    static void preStart() {
        try {
            Class<?> warmup1 = ProjectUtils.class;
            Class<?> warmup2 = TestUtils.class;
            TestUtils.prepareEnvironment("0\n0\nn\nexit\n");
            java.lang.reflect.Field field = com.ahaviss.utils.ProjectUtils.class.getDeclaredField("scanner");
            field.setAccessible(true);
            assertThat("warmup").isNotNull();
            TestUtils.resetEnvironment();
        } catch (Exception _) {}
    }

    @AfterEach
    void tearDown() {
        TestUtils.resetEnvironment();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/scicalcinput.csv", numLinesToSkip = 1)
    @DisplayName("Test All Operations")
    void testAllOperations(double input1, double input2, String operator, double expected) {
        String simulatedInput;
        switch (operator) {
            case "!" ->
                // Factorials MUST be clean integers without trailing decimals (e.g. "3")
                    simulatedInput = String.format(Locale.CANADA, "%.0f\nn\nexit\n", input1);

            case "sqrt" ->
                // Square roots want standard floating point layout
                    simulatedInput = String.format(Locale.CANADA, "%f\nn\nexit\n", input1);
            case "-", "^", "*", "+", "/" ->
                    simulatedInput = String.format(Locale.CANADA, "%f %f\nn\nexit\n", input1, input2);

            default ->
                    throw new IllegalArgumentException("Unknown operator " + operator);
        }
        TestUtils.prepareEnvironment(simulatedInput);
        switch (operator) {
            case "+" -> Addition.addition();
            case "-" -> Subtraction.subtraction();
            case "*" -> Multiplication.multiplication();
            case "/" -> Division.division();
            case "^" -> Exponents.exponents();
            case "sqrt" -> SquareRoot.squareRoot();
            case "!" -> Factorial.factorial();
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        String actualOutput = TestUtils.getOutput();
        if (operator.equals("^")) {
            String expectedText;
            expectedText = String.format(Locale.CANADA, "is %.2f", expected);
            assertThat(actualOutput).as("Exponent assertion failed").contains(expectedText);
        }
        else if (operator.equals("!")) {
            String expectedText = String.format(Locale.CANADA, "%.0f", expected);
            assertThat(actualOutput).as("Factorial assertion failed").contains(expectedText);
        }
        else {
            int resultIndex = actualOutput.indexOf("Result: ") + 8;
            int lineEndIndex = actualOutput.indexOf("\n", resultIndex);
            String resultLine = actualOutput.substring(resultIndex, lineEndIndex).trim();
            double actualResult = Double.parseDouble(resultLine);
            assertThat(actualResult)
                    .as("Test failed or rounding error for: " + operator)
                    .isCloseTo(expected, within(0.02));
        }
    }
}
