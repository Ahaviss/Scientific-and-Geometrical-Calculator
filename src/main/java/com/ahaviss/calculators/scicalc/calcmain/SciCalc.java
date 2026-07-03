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
package com.ahaviss.calculators.scicalc.calcmain;

import java.util.List;

import com.ahaviss.utils.ProjectUtils;
import com.ahaviss.history.*;
import com.ahaviss.calculators.scicalc.operations.*;
public class SciCalc {
    public static void history () {
        System.out.println("History:");
        List<History> history = HistoryManager.getHistory();
        int size = history.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("%d.", i);
            history.get(i).printHistory();
        }
    }
    public static void clearHistory () {
        HistoryManager.clearHistory();
        System.out.println("History cleared successfully!");
    }
    public static void sciCalc() {
        while (true) {
            try{
                int option = ProjectUtils.getValidInt("Which operation?\n1 (Add), 2 (Subtract), 3 (Multiply), 4 (Divide), 5 (Exponents), 6 (Find Factors), 7 (Square Root),\n8 (Factorial), 9 (See History), 10 (Quit Scientific Calculator), 11 (Quit Program)", false);
                switch (option) {
                    case 1:
                        Addition.addition();
                        break;
                    case 2:
                        Subtraction.subtraction();
                        break;
                    case 3:
                        Multiplication.multiplication();
                        break;
                    case 4:
                        Division.division();
                        break;
                    case 5:
                        Exponents.exponents();
                        break;
                    case 6:
                        Factors.factors();
                        break;
                    case 7:
                        SquareRoot.squareRoot();
                        break;
                    case 8:
                        Factorial.factorial();
                        break;
                    case 9:
                        while (true) {
                            if (HistoryManager.getHistory().isEmpty()) {
                                System.out.println("History is empty.");
                                break;
                            }
                            else {
                                String historyOption = ProjectUtils.getValidString("Would you like to see history or clear history? (see history/clear history):");
                                if (historyOption.equalsIgnoreCase("see history")) {
                                    history();
                                    break;
                                } else if (historyOption.equalsIgnoreCase("clear history")) {
                                    clearHistory();
                                    break;
                                } else {
                                    System.out.println("Error: Invalid option. Try again.");
                                }
                            }
                        }
                        break;
                    case 10:
                        return;
                    case 11:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid input.");
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

