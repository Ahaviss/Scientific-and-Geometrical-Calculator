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
package com.ahaviss.history;
import com.ahaviss.enums.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class History {
    private final CalculatorType calculatorType;
    private final TypeOfCalculation typeOfCalculation;
    private final BigDecimal result;
    public History (CalculatorType calculatorType, TypeOfCalculation typeOfCalculation, BigDecimal result) {
        this.calculatorType = calculatorType;
        this.typeOfCalculation = typeOfCalculation;
        this.result = result;
    }
    public void printHistory () {
        System.out.printf("%s: %s: %s%n", calculatorType.getCalculatorType(), typeOfCalculation.getTypeOfCalculation(), result.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}
