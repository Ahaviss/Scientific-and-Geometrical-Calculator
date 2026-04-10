package history;
import enums.*;
public class History {
    private final CalculatorType calculatorType;
    private final TypeOfCalculation typeOfCalculation;
    private final double result;
    public History (CalculatorType calculatorType, TypeOfCalculation typeOfCalculation, double result) {
        this.calculatorType = calculatorType;
        this.typeOfCalculation = typeOfCalculation;
        this.result = result;
    }
    public void printHistory () {
        System.out.printf("%s: %s: %.2f%n", calculatorType.getCalculatorType(), typeOfCalculation.getTypeOfCalculation(), result);
    }
}
