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
package com.ahaviss.calculators.geocalc.logic;
import com.ahaviss.calculators.geocalc.enums.*;
import com.ahaviss.calculators.geocalc.shapes2D.*;
import com.ahaviss.history.HistoryManager;
import com.ahaviss.utils.ProjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShapeLogic2D {
    public static void getCircleArea (String metric) {
        while (true) {
            try {
                String radiusOrDiameter = ProjectUtils.getValidString("Do you have the radius or diameter?");
                if (radiusOrDiameter.equalsIgnoreCase("radius")) {
                    BigDecimal radius = ProjectUtils.getValidBigDecimal("Please enter the radius of the circle", true);
                    Shape2D circle = new Circle(radius, RadiusOrDiameter.RADIUS);
                    BigDecimal circleArea = circle.area();
                    System.out.printf("Area of the circle: ~%s %s²%n", circleArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                    ProjectUtils.checkDecimal(circleArea);
                    HistoryManager.setPrev(circleArea);
                    break;
                } else if (radiusOrDiameter.equalsIgnoreCase("diameter")) {
                    BigDecimal diameter = ProjectUtils.getValidBigDecimal("Please enter the diameter of the circle", true);
                    Shape2D circle = new Circle(diameter, RadiusOrDiameter.DIAMETER);
                    BigDecimal circleArea = circle.area();
                    System.out.printf("Area of the circle: ~%s %s²%n", circleArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                    ProjectUtils.checkDecimal(circleArea);
                    HistoryManager.setPrev(circleArea);
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again");
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
    public static void getRectangleArea (String metric) {
        while (true) {
            try {
                BigDecimal length = ProjectUtils.getValidBigDecimal("Please enter the length of the rectangle", true);
                BigDecimal width = ProjectUtils.getValidBigDecimal("Please enter the width of the rectangle", true);
                Shape2D rectangle = new Rectangle(length, width);
                BigDecimal rectangleArea = rectangle.area();
                System.out.printf("Area of the rectangle: %s %s²%n", rectangleArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(rectangleArea);
                HistoryManager.setPrev(rectangleArea);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again");
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
    public static void getSquareArea (String metric) {
        while (true) {
            try {
                BigDecimal sideLength = ProjectUtils.getValidBigDecimal("Please enter the side length of the square", true);
                Shape2D square = new Square(sideLength);
                BigDecimal squareArea = square.area();
                System.out.printf("Area of the square: %s %s²%n", squareArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(squareArea);
                HistoryManager.setPrev(squareArea);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again");
            } catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
    public static void getTriangleArea (String metric) {
        while (true) {
            try {
                BigDecimal base = ProjectUtils.getValidBigDecimal("Please enter the base of the triangle", true);
                BigDecimal height = ProjectUtils.getValidBigDecimal("Please enter height of the triangle", true);
                Shape2D triangle = new Triangle(base, height);
                BigDecimal triangleArea = triangle.area();
                System.out.printf("Area of the triangle: %s %s²%n", triangleArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(triangleArea);
                HistoryManager.setPrev(triangleArea);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again");
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
    public static void getTrapezoidArea (String metric) {
        while (true) {
            try {
                BigDecimal base1 = ProjectUtils.getValidBigDecimal("Please enter base 1 of the trapezoid", true);
                BigDecimal base2 = ProjectUtils.getValidBigDecimal("Please enter base 2 of the trapezoid", true);
                BigDecimal height = ProjectUtils.getValidBigDecimal("Please enter the height of the trapezoid", true);
                Shape2D trapezoid = new Trapezoid(base1, base2, height);
                BigDecimal trapezoidArea = trapezoid.area();
                System.out.printf("Area of the trapezoid: %s %s²%n", trapezoidArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(trapezoidArea);
                HistoryManager.setPrev(trapezoidArea);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again");
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
}

