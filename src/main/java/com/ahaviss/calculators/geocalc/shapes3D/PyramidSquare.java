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

public class PyramidSquare extends Shape3D {
    private final BigDecimal edge;
    private final BigDecimal height;
    public PyramidSquare(BigDecimal edge, BigDecimal height) {
        this.edge = edge;
        this.height = height;
    }

    @Override
    public BigDecimal volume() {
        return (edge.multiply(edge).multiply(height).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128)).stripTrailingZeros();
    }
    @Override
    public BigDecimal surfaceArea() {
        BigDecimal base = edge.multiply(edge);
        return (edge.multiply(height).divide(BigDecimal.TWO, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(4)).add(base)).stripTrailingZeros();
    }
}
