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
package com.ahaviss.calculators.geocalc.shapes2D;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;

import java.math.BigDecimal;
import java.math.MathContext;

public class Circle extends Shape2D {
    private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433");
    private final BigDecimal variable;
    private final RadiusOrDiameter radiusOrDiameter;
    public Circle (BigDecimal variable, RadiusOrDiameter radiusOrDiameter) {
        this.radiusOrDiameter = radiusOrDiameter;
        this.variable = variable;
    }
    @Override
    public BigDecimal area() {
        BigDecimal radius;
        if (radiusOrDiameter == RadiusOrDiameter.RADIUS) {
            radius = variable;
        } else {
            MathContext mc = MathContext.DECIMAL128;
            radius = variable.divide(BigDecimal.valueOf(2), mc);
        }
        return radius.multiply(radius).multiply(PI).stripTrailingZeros();
    }
}
