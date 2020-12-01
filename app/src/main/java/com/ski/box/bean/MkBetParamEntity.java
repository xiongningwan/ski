package com.ski.box.bean;

import android.view.View;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 投注请求实体，四个投注接口共用
 */
public class MkBetParamEntity {
    private int from;//提交源头
    private int buyMode;// 0 投注 1 追号 2追中即停 3 不中即停
    private String planNo;
    private int ticketId;
    private String totalAmount;//总金额
    private List<BetParamEntity> bet = new ArrayList<>();
    private List<ChaseParamEntity> chase = new ArrayList<>();
    private View betView;
    private int playMode; // 标准，双面


    public int getPlayMode() {
        return playMode;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    public View getBetView() {
        return betView;
    }

    public void setBetView(View betView) {
        this.betView = betView;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    private boolean clickEnable;//本次选中是否有效

    public int getBuyMode() {
        return buyMode;
    }

    public void setBuyMode(int buyMode) {
        this.buyMode = buyMode;
    }

    public List<ChaseParamEntity> getChase() {
        return chase;
    }

    public void setChase(List<ChaseParamEntity> chase) {
        this.chase = chase;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }


    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }


    public List<BetParamEntity> getBet() {
        return bet;
    }

    public void setBet(List<BetParamEntity> bet) {
        this.bet = bet;
    }


    public boolean isClickEnable() {
        return clickEnable;
    }

    public void setClickEnable(boolean clickEnable) {
        this.clickEnable = clickEnable;
    }

    public static class BetParamEntity implements Serializable {
        //	 玩法ID
        private String playId;
        //	 投注号码
        private String betNum;
        /*标准盘倍数*/
        private long betBeiShu;
        //投注模式（圆角分离）
        private String moneyMethod;
        //注数
        private long betCount;
        /*双面盘*/
        private long betAmount_d;
        /*标准盘*/
        private double betAmount_s;
        //投注模式 选择最高奖金还是最低奖金
        private int betMethod;
        /*玩法投注内容*/
        private String content;

        /*玩法赔率*/
        private String playOdds;
        /*双面盘  false 复试玩法  true 单式玩法*/
        private boolean isSingle = false;
        /*玩法组名称*/
        private String groupName;
        private String groupCode;
        /*单式 有投注位置*/
        private String positionName = "";
        /*购彩篮用*/
        private boolean isDanTiao;

        public String getGroupCode() {
            return groupCode;
        }

        public void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }

        public boolean isDanTiao() {
            return isDanTiao;
        }

        public void setDanTiao(boolean danTiao) {
            isDanTiao = danTiao;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public long getBetBeiShu() {
            return betBeiShu;
        }

        public void setBetBeiShu(long betBeiShu) {
            this.betBeiShu = betBeiShu;
        }

        //双面盘限红
        private boolean redLimit = false;

        public String getPlayOdds() {
            return playOdds;
        }

        public void setPlayOdds(String playOdds) {
            this.playOdds = playOdds;
        }

        public boolean isSingle() {
            return isSingle;
        }

        public void setSingle(boolean aDouble) {
            isSingle = aDouble;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getContent() {

            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPlayId() {
            return playId;
        }

        public void setPlayId(String playId) {
            this.playId = playId;
        }


        public String getBetNum() {
            return betNum;
        }

        public void setBetNum(String betNum) {
            this.betNum = betNum;
        }

        public String getMoneyMethod() {
            return moneyMethod;
        }

        public void setMoneyMethod(String moneyMethod) {
            this.moneyMethod = moneyMethod;
        }

        public long getBetCount() {
            return betCount;
        }

        public void setBetCount(long betCount) {
            this.betCount = betCount;
        }

        public long getBetAmount_d() {
            return betAmount_d;
        }

        public void setBetAmount_d(long betAmount_d) {
            this.betAmount_d = betAmount_d;
        }

        public double getBetAmount_s() {
            return betAmount_s;
        }

        public void setBetAmount_s(double betAmount_s) {
            this.betAmount_s = betAmount_s;
        }

        public int getBetMethod() {
            return betMethod;
        }

        public void setBetMethod(int betMethod) {
            this.betMethod = betMethod;
        }

        public boolean isRedLimit() {
            return redLimit;
        }

        public void setRedLimit(boolean redLimit) {
            this.redLimit = redLimit;
        }

    }

    public static class ChaseParamEntity implements Serializable {
        private String planId; // 期号
        private int times; // 倍数

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }

}
