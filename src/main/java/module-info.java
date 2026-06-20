module ScientificAndGeometricalCalculator {
    requires java.base;
    opens com.ahaviss.utils to org.junit.jupiter;
    opens com.ahaviss.calculators.geocalc.shapes3D to org.junit.jupiter, org.assertj.core;
    opens com.ahaviss.calculators.geocalc.shapes2D to org.junit.jupiter, org.assertj.core;
    opens com.ahaviss.calculators.scicalc.operations to org.junit.jupiter, org.assertj.core;
}