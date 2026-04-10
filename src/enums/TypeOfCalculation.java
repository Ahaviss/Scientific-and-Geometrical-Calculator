package enums;

public enum TypeOfCalculation {
    ADDITION("Addition"),
    SUBTRACTION("Subtraction"),
    MULTIPLICATION("Multiplication"),
    DIVISION("Division"),
    FACTORIAL("Factorial"),
    SQUARE_ROOT("Square Root"),
    EXPONENTS("Exponents"),
    CIRCUMFERENCE("Circumference"),
    FACTORS("Find Factors"),
    AREA("Area"),
    VOLUME("Volume"),
    SURFACE_AREA("Surface Area");
    private final String typeOfCalculation;
    TypeOfCalculation (String typeOfCalculation) {
        this.typeOfCalculation = typeOfCalculation;
    }
    public String getTypeOfCalculation() {
        return typeOfCalculation;
    }
}
