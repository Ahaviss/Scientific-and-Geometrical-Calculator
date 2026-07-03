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
package com.ahaviss.main;

import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.calculators.geocalc.calcmain.GeoCalc;
import com.ahaviss.calculators.scicalc.calcmain.SciCalc;
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
