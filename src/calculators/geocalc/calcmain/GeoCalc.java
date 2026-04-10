package calculators.geocalc.calcmain;
import calculators.geocalc.logic.*;
import utils.ProjectUtils;
public class GeoCalc {
    public static String getMetric () {
        while (true) {
            try {
                String metric = ProjectUtils.getValidString("Please enter the metric (cm/m/ft/in)");
                if (metric == null || metric.isEmpty()) {
                    System.out.println("Metric cannot be empty. Please try again");
                    continue;
                }
                return metric;
            }
            catch (Exception e) {
                System.out.printf("An unexpected error occurred. %s%n", e.getMessage());
            }
        }
    }
    public static boolean shape2DCalc () {
        while (true) {
            try {
                System.out.println("What would you like to calculate the area of?");
                int menuOption = ProjectUtils.getValidInt("1 (Circle), 2 (Rectangle), 3 (Square), 4 (Triangle), 5 (Trapezoid), 6 (Quit 2D Calculation),\n7 (Quit Geometrical Calculator), 8 (Quit Program)", false);
                switch (menuOption) {
                    case 1:
                        ShapeLogic2D.getCircleArea(getMetric());
                        break;
                    case 2:
                        ShapeLogic2D.getRectangleArea(getMetric());
                        break;
                    case 3:
                        ShapeLogic2D.getSquareArea(getMetric());
                        break;
                    case 4:
                        ShapeLogic2D.getTriangleArea(getMetric());
                        break;
                    case 5:
                        ShapeLogic2D.getTrapezoidArea(getMetric());
                        break;
                    case 6:
                        return false;
                    case 7:
                        return true;
                    case 8:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again");
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
    public static boolean shape3DCalc () {
        while (true) {
            try {
                int menuOption = ProjectUtils.getValidInt("1 (Cube), 2 (Cylinder), 3 (Square-Based Pyramid), 4 (Rectangular Prism), 5 (Triangular Prism), 6 (Quit 3D Calculation),\n7 (Quit Geometrical Calculator), 8 (Quit Program)", false);
                switch (menuOption) {
                    case 1:
                        ShapeLogic3D.cube(getMetric());
                        break;
                    case 2:
                        ShapeLogic3D.cylinder(getMetric());
                        break;
                    case 3:
                        ShapeLogic3D.pyramidSquare(getMetric());
                        break;
                    case 4:
                        ShapeLogic3D.recPrism(getMetric());
                        break;
                    case 5:
                        ShapeLogic3D.trianglePrism(getMetric());
                        break;
                    case 6:
                        return false;
                    case 7:
                        return true;
                    case 8:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again");
                }
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred. " + e.getMessage());
            }
        }
    }
    public static void geoCalc () {
        while (true) {
            try {
                String calculation = ProjectUtils.getValidString("Would you like to do a 2D (Area) calculation or a 3D (Volume and Surface Area) calculation, or quit? (3D/2D/Quit)");
                switch (calculation.toLowerCase()) {
                    case "3d":
                        if (shape3DCalc()) {
                            return;
                        }
                        break;
                    case "2d":
                        if (shape2DCalc()) {
                            return;
                        }
                        break;
                    case "quit":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again");
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
}


