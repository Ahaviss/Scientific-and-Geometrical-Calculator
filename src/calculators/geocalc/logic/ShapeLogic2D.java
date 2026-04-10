package calculators.geocalc.logic;
import calculators.geocalc.enums.*;
import calculators.geocalc.shapes2D.*;
import history.HistoryManager;
import utils.ProjectUtils;
public class ShapeLogic2D {
    public static void getCircleArea (String metric) {
        while (true) {
            try {
                String radiusOrDiameter = ProjectUtils.getValidString("Do you have the radius or diameter?");
                if (radiusOrDiameter.equalsIgnoreCase("radius")) {
                    double radius = ProjectUtils.getValidDouble("Please enter the radius of the circle", true);
                    Shape2D circle = new Circle(radius,RadiusOrDiameter.RADIUS);
                    double circleArea = circle.area();
                    if (!Double.isFinite(circleArea)) {
                        System.out.println("Overflow");
                        continue;
                    }
                    System.out.printf("Area of the circle: ~%.2f %s²%n", circleArea, metric);
                }
                else if (radiusOrDiameter.equalsIgnoreCase("diameter")) {
                    double diameter = ProjectUtils.getValidDouble("Please enter the diameter of the circle", true);
                    Shape2D circle = new Circle(diameter,RadiusOrDiameter.DIAMETER);
                    double circleArea = circle.area();
                    if (!Double.isFinite(circleArea)) {
                        System.out.println("Overflow");
                        continue;
                    }
                    System.out.printf("Area of the circle: ~%.2f %s²%n", circleArea, metric);
                    ProjectUtils.checkDecimal(circleArea);
                    HistoryManager.prev = circleArea;
                    break;
                }
                else {
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
                double length = ProjectUtils.getValidDouble("Please enter the length of the rectangle", true);
                double width = ProjectUtils.getValidDouble("Please enter the width of the rectangle", true);
                Shape2D rectangle = new Rectangle(length, width);
                double rectangleArea = rectangle.area();
                if (!Double.isFinite(rectangleArea)) {
                    System.out.println("Overflow");
                    continue;
                }
                System.out.printf("Area of the rectangle: %.2f %s²%n", rectangleArea, metric);
                ProjectUtils.checkDecimal(rectangleArea);
                HistoryManager.prev = rectangleArea;
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
                double sideLength = ProjectUtils.getValidDouble("Please enter the side length of the square", true);
                Shape2D square = new Square(sideLength);
                double squareArea = square.area();
                if (!Double.isFinite(squareArea)) {
                    System.out.println("Overflow");
                    continue;
                }
                System.out.printf("Area of the square: %.2f %s²%n", squareArea, metric);
                ProjectUtils.checkDecimal(squareArea);
                HistoryManager.prev = squareArea;
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
                double base = ProjectUtils.getValidDouble("Please enter the base of the triangle", true);
                double height = ProjectUtils.getValidDouble("Please enter height of the triangle", true);
                Shape2D triangle = new Triangle(base, height);
                double triangleArea = triangle.area();
                if (!Double.isFinite(triangleArea)) {
                    System.out.println("Overflow");
                    continue;
                }
                System.out.printf("Area of the triangle: %.2f %s²%n", triangleArea, metric);
                ProjectUtils.checkDecimal(triangleArea);
                HistoryManager.prev = triangleArea;
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
                double base1 = ProjectUtils.getValidDouble("Please enter base 1 of the trapezoid", true);
                double base2 = ProjectUtils.getValidDouble("Please enter base 2 of the trapezoid", true);
                double height = ProjectUtils.getValidDouble("Please enter the height of the trapezoid", true);
                Shape2D trapezoid = new Trapezoid(base1, base2, height);
                double trapezoidArea = trapezoid.area();
                if (!Double.isFinite(trapezoidArea)) {
                    System.out.println("Overflow");
                    continue;
                }
                System.out.printf("Area of the trapezoid: %.2f %s²%n", trapezoidArea, metric);
                ProjectUtils.checkDecimal(trapezoidArea);
                HistoryManager.prev = trapezoidArea;
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

