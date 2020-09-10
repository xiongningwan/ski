package com.ski.box.utils.lottery.algorithm.utils.math;

import java.math.BigDecimal;

public class AlgorithmUtil {
    public AlgorithmUtil() {
    }

    public static BigDecimal factorial(int n) {
        return n > 1 ? (new BigDecimal(n)).multiply(factorial(n - 1)) : new BigDecimal(1);
    }

    public static Long combination(int n, int m) {
        return n >= m ? factorial(n).divide(factorial(n - m)).divide(factorial(m)).longValue() : 0L;
    }
}
