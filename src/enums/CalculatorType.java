package enums;

public enum CalculatorType {
    SCIENTIFIC("Scientific"),
    GEOMETRICAL("Geometrical");
    private final String calculatorType;
    CalculatorType (String calculatorType) {
        this.calculatorType = calculatorType;
    }
    public String getCalculatorType () {
        return calculatorType;
    }
}
