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
package com.ahaviss.calculators.geocalc.shapes3D;
import com.ahaviss.calculators.geocalc.enums.RadiusOrDiameter;

import java.math.BigDecimal;
import java.math.MathContext;

public class Cylinder extends Shape3D {
    private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433");
    private final BigDecimal variable;
    private final RadiusOrDiameter radiusOrDiameter;
    private final BigDecimal height;
    public Cylinder (BigDecimal variable, RadiusOrDiameter radiusOrDiameter, BigDecimal height) {
        this.variable = variable;
        this.radiusOrDiameter = radiusOrDiameter;
        this.height = height;
    }
    @Override
    public BigDecimal volume() {
        BigDecimal radius = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable
                : variable.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

        return radius.pow(2)
                .multiply(PI)
                .multiply(height);
    }

    @Override
    public BigDecimal surfaceArea() {
        BigDecimal radius = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable
                : variable.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

        BigDecimal diameter = radiusOrDiameter == RadiusOrDiameter.RADIUS
                ? variable.multiply(BigDecimal.valueOf(2))
                : variable;

        BigDecimal topAndBottom = BigDecimal.valueOf(2)
                .multiply(radius.pow(2))
                .multiply(PI);
        BigDecimal lateral = height.multiply(PI).multiply(diameter);
        return topAndBottom.add(lateral).stripTrailingZeros();
    }
}