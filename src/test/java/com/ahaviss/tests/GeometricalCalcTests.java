package com.ahaviss.tests;

import com.ahaviss.testutils.TestUtils;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;
import com.ahaviss.calculators.geocalc.shapes2D.*;
import com.ahaviss.calculators.geocalc.shapes3D.*;
import com.ahaviss.utils.ProjectUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class GeometricalCalcTests {
    @BeforeAll
    static void preStart() {
        try {
            Class<?> warmup1 = ProjectUtils.class;
            Class<?> warmup2 = TestUtils.class;
            TestUtils.prepareEnvironment("0\n0\nn\nexit\n");
            java.lang.reflect.Field field = com.ahaviss.utils.ProjectUtils.class.getDeclaredField("scanner");
            field.setAccessible(true);
            assertThat("warmup").isNotNull();
        } catch (Exception _) {}
    }
    @Nested
    class TwoD {
        private Shape2D shape;
        @AfterEach
        void tearDown() {shape = null;}
        @ParameterizedTest
        @CsvFileSource(resources = "/2Dgeocalcinput.csv", numLinesToSkip = 1, maxCharsPerColumn = 50000)
        @DisplayName("Test All 2D Operations")
        void testAll2DOperations(BigDecimal input1, BigDecimal input2, BigDecimal input3, String shape, BigDecimal expected) {
            switch (shape) {
                case "sq" -> this.shape = new Square(input1);
                case "cir" -> this.shape = new Circle(input1, RadiusOrDiameter.RADIUS);
                case "rect" -> this.shape = new Rectangle(input1, input2);
                case "tri" -> this.shape = new Triangle(input1, input2);
                case "trap" -> this.shape = new Trapezoid(input1, input2, input3);
                default -> throw new IllegalArgumentException("Unknown operator " + shape);
            }
            assertThat(this.shape.area()).as("Test failed or for: " + this.shape.getClass().getSimpleName()).isEqualTo(expected.toPlainString());
        }
    }
    @Nested
    class ThreeD {
        private Shape3D shape;
        @AfterEach
        void tearDown() {shape = null;}
        @ParameterizedTest
        @CsvFileSource(resources = "/3Dgeocalcinputvolume.csv", numLinesToSkip = 1,maxCharsPerColumn = 50000)
        @DisplayName("Test 3D Volume Operations")
        void test3DVolumeOperations (BigDecimal input1, BigDecimal input2, BigDecimal input3, String shape, BigDecimal expectedResult) {
            switch (shape) {
                case "cyl" -> this.shape = new Cylinder(input1, RadiusOrDiameter.RADIUS, input2);
                case "cube" -> this.shape = new Cube(input1);
                case "pysq" -> this.shape = new PyramidSquare(input1, input2);
                case "recp" -> this.shape = new RecPrism(input1, input2, input3);
                case "trip" -> this.shape = new TrianglePrism(input1, input2, input3);
                default -> throw new IllegalArgumentException("Unknown operator: " + shape);
            }
            assertThat(this.shape.volume()).as("Test failed for: " + this.shape.getClass().getSimpleName()).isEqualTo(expectedResult.toPlainString());
        }
        @ParameterizedTest
        @CsvFileSource(resources = "/3Dgeocalcinputsa.csv", numLinesToSkip = 1)
        @DisplayName("Test 3D Surface Area Operations")
        void test3DSurfaceAreaOperations (BigDecimal input1, BigDecimal input2, BigDecimal input3, BigDecimal input4, BigDecimal input5, String shape, BigDecimal expectedResult) {
            switch (shape) {
                case "cyl" -> this.shape = new Cylinder(input1, RadiusOrDiameter.RADIUS, input2);
                case "cube" -> this.shape = new Cube(input1);
                case "pysq" -> this.shape = new PyramidSquare(input1, input2);
                case "recp" -> this.shape = new RecPrism(input1, input2, input3);
                case "trip" -> this.shape = new TrianglePrism(input1, input2, input4, input5, input3);
                default -> throw new IllegalArgumentException("Unknown operator: " + shape);
            }
            assertThat(this.shape.surfaceArea()).as("Test failed or rounding error for: " + this.shape.getClass().getSimpleName()).isEqualTo(expectedResult.toPlainString());
        }
    }
}
