package com.ski.box.utils.lottery.algorithm.utils.algorithm;

public class BinomialCoeff {
    public BinomialCoeff() {
    }

    public static long compute2(int n, int k) {
        long[] C = new long[k + 1];
        C[0] = 1L;
        for(int i = 1; i <= n; ++i) {
            for(int j = Math.min(i, k); j > 0; --j) {
                C[j] += C[j - 1];
            }
        }
        return C[k];
    }
}
