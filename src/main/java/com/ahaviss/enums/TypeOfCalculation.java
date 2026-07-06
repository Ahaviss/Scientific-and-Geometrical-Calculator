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
package com.ahaviss.enums;

public enum TypeOfCalculation {
    ADDITION("Addition"),
    SUBTRACTION("Subtraction"),
    MULTIPLICATION("Multiplication"),
    DIVISION("Division"),
    FACTORIAL("Factorial"),
    ROOT("Root"),
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
