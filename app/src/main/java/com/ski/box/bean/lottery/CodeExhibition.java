package com.ski.box.bean.lottery;

/**
 * @CreateDate: 2020/2/22 16:04
 * @ClassName: CodeExhibition
 * @Author: Jofear
 * @Description: 号码
 */
public class CodeExhibition {
    private int betNum;
    private long playItemId;
    private String odds="0";
    private String playName = "";
    private String showTxt = "";
    private String betTxt = "";
    /*重庆时时彩用*/
    private String shengXiao = "";
    private String tag;
    /*带位置的单式*/
    private String positionName = "";

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getBetNum() {
        return betNum;
    }

    public void setBetNum(int betNum) {
        this.betNum = betNum;
    }

    public long getPlayItemId() {
        return playItemId;
    }

    public void setPlayItemId(long playItemId) {
        this.playItemId = playItemId;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getShowTxt() {
        return showTxt;
    }

    public void setShowTxt(String showTxt) {
        this.showTxt = showTxt;
    }

    public String getBetTxt() {
        return betTxt;
    }

    public void setBetTxt(String betTxt) {
        this.betTxt = betTxt;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getShengXiao() {
        return shengXiao;
    }

    public void setShengXiao(String shengXiao) {
        this.shengXiao = shengXiao;
    }
}
