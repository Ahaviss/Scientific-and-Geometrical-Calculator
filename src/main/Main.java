package main;

import utils.ProjectUtils;
import calculators.geocalc.calcmain.GeoCalc;
import calculators.scicalc.calcmain.SciCalc;
public class Main {
    public static void main (String[] args) {
        while (true) {
            int option = ProjectUtils.getValidInt("Welcome to the calculator!\nWould you like to open: 1 (Scientific Calculator), 2 (Geometrical Calculator), 3 (Quit Program)", false);
            if (option == 1) {
                SciCalc.sciCalc();
            } else if (option == 2) {
                GeoCalc.geoCalc();
            } else if (option == 3) {
                System.out.println("Bye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
