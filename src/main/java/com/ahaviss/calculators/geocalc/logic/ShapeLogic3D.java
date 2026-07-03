package com.ahaviss.calculators.geocalc.logic;
import com.ahaviss.calculators.geocalc.enums.*;
import com.ahaviss.enums.*;
import com.ahaviss.calculators.geocalc.shapes3D.*;
import com.ahaviss.history.*;
import com.ahaviss.utils.ProjectUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShapeLogic3D {
    private static GeometryOperation operation;
    public static GeometryOperation getOperation() {
        while (true) {
            System.out.println("What operation?");
            int option = ProjectUtils.getValidInt("1 (Surface Area), 2 (Volume)", true);
            if (option == 1) {
                return GeometryOperation.SURFACE_AREA;
            } else if (option == 2) {
                return GeometryOperation.VOLUME;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void cube (String metric) {
        operation = getOperation();
        BigDecimal edge = ProjectUtils.getValidBigDecimal("Please enter the edge length of the cube", true);
        Shape3D cube = new Cube(edge);
        determineOperation(cube, operation, metric);
    }
    public static void cylinder (String metric) {
        operation = getOperation();
        RadiusOrDiameter radiusOrDiameter;
        BigDecimal variable;
        BigDecimal height;
        while (true) {
            String measurement = ProjectUtils.getValidString("Do you have the radius or diameter of the base circle? (radius/diameter)");
            if (measurement.equalsIgnoreCase("radius")) {
                radiusOrDiameter = RadiusOrDiameter.RADIUS;
                variable = ProjectUtils.getValidBigDecimal("Please enter the radius of the base circle", true);
            } else if (measurement.equalsIgnoreCase("diameter")) {
                radiusOrDiameter = RadiusOrDiameter.DIAMETER;
                variable = ProjectUtils.getValidBigDecimal("Please enter the diameter of the circle base.", true);
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
            height = ProjectUtils.getValidBigDecimal("Please enter the height of the cylinder", true);
            Shape3D cylinder = new Cylinder(variable, radiusOrDiameter, height);
            determineOperation(cylinder, operation, metric);
            break;
        }
    }
    public static void pyramidSquare (String metric) {
        operation = getOperation();
        BigDecimal edge = ProjectUtils.getValidBigDecimal("Please enter the square length/triangle base length of the pyramid", true);
        BigDecimal height = ProjectUtils.getValidBigDecimal("Please enter the height of the pyramid", true);
        Shape3D pyramidSquare = new PyramidSquare(edge, height);
        determineOperation(pyramidSquare, operation, metric);
    }
    public static void recPrism (String metric) {
        operation = getOperation();
        BigDecimal width = ProjectUtils.getValidBigDecimal("Please enter the width of the rectangular prism", true);
        BigDecimal length = ProjectUtils.getValidBigDecimal("Please enter the length of the rectangular prism", true);
        BigDecimal height = ProjectUtils.getValidBigDecimal("Please enter the height of the rectangular prism", true);
        Shape3D recPrism = new RecPrism(width, length, height);
        determineOperation(recPrism, operation, metric);
    }
    public static void trianglePrism (String metric) {
        operation = getOperation();
        BigDecimal base = ProjectUtils.getValidBigDecimal("Please enter the base length of the triangular base", true);
        BigDecimal height = ProjectUtils.getValidBigDecimal("Please enter the height of the triangular prism compared to the base side of the triangular base.", true);
        BigDecimal length = ProjectUtils.getValidBigDecimal("Please enter the length of the triangular prism", true);
        if (operation == GeometryOperation.SURFACE_AREA) {
            BigDecimal side2 = ProjectUtils.getValidBigDecimal("Please enter the second side length of the triangular base", true);
            BigDecimal side3 = ProjectUtils.getValidBigDecimal("Please enter the third side length of the triangular base", true);
            Shape3D trianglePrism = new TrianglePrism(height, base, side2, side3, length);
            determineOperation(trianglePrism, operation, metric);
        } else if (operation == GeometryOperation.VOLUME) {
            Shape3D trianglePrism = new TrianglePrism(height, base, length);
            determineOperation(trianglePrism, operation, metric);
        }
    }
    private static void determineOperation (Shape3D object, GeometryOperation operation, String metric) {
        try {
            if (operation == GeometryOperation.SURFACE_AREA) {
                BigDecimal surfaceArea = object.surfaceArea();
                System.out.printf("Surface area: %s %s²%n", surfaceArea.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(surfaceArea);
                HistoryManager.addHistory(new History(CalculatorType.GEOMETRICAL, TypeOfCalculation.SURFACE_AREA, surfaceArea));
                HistoryManager.setPrev(surfaceArea);
            } else if (operation == GeometryOperation.VOLUME) {
                BigDecimal volume = object.volume();
                System.out.printf("Volume: %s %s³%n", volume.setScale(2, RoundingMode.HALF_EVEN).toPlainString(), metric);
                ProjectUtils.checkDecimal(volume);
                HistoryManager.addHistory(new History(CalculatorType.GEOMETRICAL, TypeOfCalculation.VOLUME, volume));
                HistoryManager.setPrev(volume);
            }
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred. " +  e.getMessage());
        }
    }
}
