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
package com.ahaviss.generators.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class GeneratorUtils {
    private static final Random RANDOM = new Random();
    public static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433");
    public static BigDecimal getRandomBigDecimal(BigDecimal min, BigDecimal max, int scale) {
        BigDecimal range = max.subtract(min);
        BigInteger unscaledRange = range.movePointRight(scale).toBigInteger();
        BigInteger randomUnscaled;
        do {
            randomUnscaled = new BigInteger(unscaledRange.bitLength(), RANDOM);
        } while (randomUnscaled.compareTo(unscaledRange) >= 0);
        return new BigDecimal(randomUnscaled, scale).add(min);
    }
}
