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

import java.math.BigDecimal;
import java.math.MathContext;

public class Trapezoid extends Shape2D {
    private final BigDecimal base1;
    private final BigDecimal base2;
    private final BigDecimal height;
    public Trapezoid (BigDecimal base1, BigDecimal base2, BigDecimal height) {
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
    }

    @Override
    public BigDecimal area() {return ((base1.add(base2)).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128)).multiply(height).stripTrailingZeros();}
}
