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

public class RecPrism extends Shape3D {
    private final BigDecimal width;
    private final BigDecimal length;
    private final BigDecimal height;
    public RecPrism(BigDecimal width, BigDecimal length, BigDecimal height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    @Override
    public BigDecimal volume() {
        return (width.multiply(length).multiply(height)).stripTrailingZeros();
    }

    @Override
    public BigDecimal surfaceArea() {
        BigDecimal first = width.multiply(length);
        BigDecimal second = height.multiply(width);
        BigDecimal third = length.multiply(height);
        return BigDecimal.valueOf(2).multiply(first.add(second).add(third)).stripTrailingZeros();
    }
}
