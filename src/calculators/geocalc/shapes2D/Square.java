package calculators.geocalc.shapes2D;

public class Square extends Shape2D {
    private final double sideLength;
    public Square (double sideLength) {
        this.sideLength = sideLength;
    }
    @Override
    public double area () {return Math.pow(sideLength, 2);}
}
