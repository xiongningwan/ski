package com.ski.box.bean.group;

import java.math.BigDecimal;

/**
 * Created by tom on 2020/10/30.
 */
public class GroupMember {
    /**
     * memberAccount : agent09
     * memberRebate : 1980
     * memberAmt : 1000405.3800
     * regTime : 2020-10-18 00:24:23
     * loginTime : 2020-10-21 00:08:52
     * teamCount : 2
     * teamAmt : 2952752.3780
     */

    private String memberAccount;
    private int memberRebate;
    private BigDecimal memberAmt;
    private String regTime;
    private String loginTime;
    private int teamCount;
    private BigDecimal teamAmt;

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public int getMemberRebate() {
        return memberRebate;
    }

    public void setMemberRebate(int memberRebate) {
        this.memberRebate = memberRebate;
    }

    public BigDecimal getMemberAmt() {
        return memberAmt;
    }

    public void setMemberAmt(BigDecimal memberAmt) {
        this.memberAmt = memberAmt;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public BigDecimal getTeamAmt() {
        return teamAmt;
    }

    public void setTeamAmt(BigDecimal teamAmt) {
        this.teamAmt = teamAmt;
    }
}
