package com.ski.box.bean.group;

/**
 * Created by tom on 2020/10/29.
 */
public class RebateKV {
    /**
     * baseRebate : 1900
     * maxRebate : 1985
     */

    private int rebate;
    private String percent;

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
