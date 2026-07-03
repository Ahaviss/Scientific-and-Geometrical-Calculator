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
