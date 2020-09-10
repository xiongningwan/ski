package com.ski.box.bean;

/**
 * @CreateDate: 2020/3/5 14:47
 * @ClassName: BallBean
 * @Author: doms
 * @Description:
 */
public class BallBean {
    /**投注名称**/
    private String playItemName;
    /**赔率**/
    private String playItemOdds;
    /**是否选中**/
    private boolean isCheck;

    private String playItemId;
    private String playItemCode;
    private String palyGroupName;

    /**投注金额**/
    private long betAmount_d;

    public String getPalyGroupName() {
        return palyGroupName;
    }

    public void setPalyGroupName(String palyGroupName) {
        this.palyGroupName = palyGroupName;
    }

    public long getBetAmount_d() {
        return betAmount_d;
    }

    public void setBetAmount_d(long betAmount_d) {
        this.betAmount_d = betAmount_d;
    }

    public String getPlayItemId() {
        return playItemId;
    }

    public void setPlayItemId(String playItemId) {
        this.playItemId = playItemId;
    }

    public String getPlayItemCode() {
        return playItemCode;
    }

    public void setPlayItemCode(String playItemCode) {
        this.playItemCode = playItemCode;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getPlayItemName() {
        return playItemName;
    }

    public void setPlayItemName(String playItemName) {
        this.playItemName = playItemName;
    }

    public String getPlayItemOdds() {
        return playItemOdds;
    }

    public void setPlayItemOdds(String playItemOdds) {
        this.playItemOdds = playItemOdds;
    }
}
