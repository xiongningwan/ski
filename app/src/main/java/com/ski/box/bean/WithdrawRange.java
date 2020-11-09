package com.ski.box.bean;

import java.math.BigDecimal;

/**
 * Created by tom on 2020/11/9.
 */
public class WithdrawRange {
    /**
     * minAmt : 100000
     * maxAmt : 1000000000
     */

    private BigDecimal minAmt;
    private BigDecimal maxAmt;

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }
}
