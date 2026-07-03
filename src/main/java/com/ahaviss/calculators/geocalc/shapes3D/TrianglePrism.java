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

import java.math.BigDecimal;
import java.math.MathContext;

public class TrianglePrism extends Shape3D {
    private final BigDecimal height;
    private final BigDecimal base;
    private BigDecimal side2 = BigDecimal.ZERO;
    private BigDecimal side3 = BigDecimal.ZERO;
    private final BigDecimal length;
    public TrianglePrism(BigDecimal height, BigDecimal base, BigDecimal side2, BigDecimal side3, BigDecimal length) {
        this.height = height;
        this.base = base;
        this.length = length;
        this.side2 = side2;
        this.side3 = side3;
    }
    public TrianglePrism (BigDecimal height, BigDecimal base, BigDecimal length) {
        this.height = height;
        this.base = base;
        this.length = length;
    }
    @Override
    public BigDecimal volume() {
        return (base.multiply(height).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(length)).stripTrailingZeros();
    }
    @Override
    public BigDecimal surfaceArea () {
        BigDecimal sidesAdded = base.add(side2).add(side3);
        BigDecimal triangles = base.multiply(height);
        return (triangles.add(sidesAdded.multiply(length))).stripTrailingZeros();
    }
}
