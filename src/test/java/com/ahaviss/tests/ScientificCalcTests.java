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
package com.ahaviss.tests;

import com.ahaviss.testutils.TestUtils;
import com.ahaviss.calculators.scicalc.operations.*;
import com.ahaviss.utils.ProjectUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;
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
    @CsvFileSource(resources = "/scicalcinput.csv", numLinesToSkip = 1, maxCharsPerColumn = 50000)
    @DisplayName("Test All Operations")
    void testAllOperations(BigDecimal input1, BigDecimal input2, String operator, BigDecimal expected) {
        String simulatedInput;
        switch (operator) {
            case "!" ->
                // Factorials MUST be clean integers without trailing decimals (e.g. "3")
                    simulatedInput = String.format(Locale.CANADA, "%s\nn\nexit\n", input1.longValue());

            case "sqrt" -> simulatedInput = String.format(Locale.CANADA, "1\n%s\nn\nexit\n", input1.toPlainString());
            case "cbrt" -> simulatedInput = String.format(Locale.CANADA, "2\n%s\nn\nexit\n", input1.toPlainString());
            case "customrt" -> simulatedInput = String.format(Locale.CANADA, "3\n%s %s\nn\nexit\n", input2.toPlainString(), input1.toPlainString());
            case "-", "^", "*", "+", "/" ->
                    simulatedInput = String.format(Locale.CANADA, "%s %s\nn\nexit\n", input1.toPlainString(), input2.toPlainString());

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
            case "sqrt", "cbrt", "customrt" -> Root.root();
            case "!" -> Factorial.factorial();
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        String actualOutput = TestUtils.getOutput();
        if (operator.equals("^")) {
            String expectedText;
            expectedText = String.format(Locale.CANADA, "is %s", expected.toPlainString());
            assertThat(actualOutput).as("Exponent assertion failed").contains(expectedText);
        }
        else if (operator.equals("!")) {
            String expectedText = String.format(Locale.CANADA, "%s", expected.toBigInteger());
            assertThat(actualOutput).as("Factorial assertion failed").contains(expectedText);
        }
        else {
            int resultIndex = actualOutput.indexOf("Result: ") + 8;
            int lineEndIndex = actualOutput.indexOf("\n", resultIndex);
            String resultLine = actualOutput.substring(resultIndex, lineEndIndex).trim();
            BigDecimal actualResult = new BigDecimal(resultLine);
            assertThat(actualResult)
                    .as("Test failed or rounding error for: " + operator)
                    .isEqualTo(expected);
        }
    }
}
