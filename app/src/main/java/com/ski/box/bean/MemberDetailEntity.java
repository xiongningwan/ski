package com.ski.box.bean;


import java.util.List;

/**
 * 用户信息--用户余额
 */
public class MemberDetailEntity {

    /**
     * client : 0
     * memberId : 915020
     * merchantId : 122
     * memberAccount : Harrison
     * memberName : Harrison
     * merchantName : Harrison
     * merchantAccount : Harrison
     * balance : 1.0031119799115E9
     * status : 0
     * limitBet : 1.0
     * registIp : 172.18.11.228
     * registTime : 2019-10-06T11:00:00.000+0000
     * loginIp : 172.18.3.114
     * loginTime : 2019-10-14T03:43:25.000+0000
     * doubleGroupId : 1568513708053
     * standardGroupId : 1568513707884
     * doubleGroupName : 默认双面盘代理奖金组
     * standardGroupName : 默认标准盘代理奖金组
     * list : [{"ticketId":1,"ticketName":"重庆时时彩","odds":1},{"ticketId":3,"ticketName":"新疆时时彩","odds":1},{"ticketId":4,"ticketName":"广东11选5","odds":1},{"ticketId":5,"ticketName":"山东11选5","odds":1},{"ticketId":6,"ticketName":"福彩3D","odds":1},{"ticketId":7,"ticketName":"排列三","odds":1},{"ticketId":8,"ticketName":"天津时时彩","odds":1},{"ticketId":9,"ticketName":"黑龙江时时彩","odds":1},{"ticketId":11,"ticketName":"江西11选5","odds":1},{"ticketId":16,"ticketName":"美开3D","odds":1},{"ticketId":17,"ticketName":"极速3D","odds":1},{"ticketId":18,"ticketName":"北京PK10","odds":1},{"ticketId":20,"ticketName":"吉林快三","odds":1},{"ticketId":26,"ticketName":"北京快乐八","odds":1},{"ticketId":27,"ticketName":"香港六合彩","odds":1},{"ticketId":28,"ticketName":"幸运28","odds":1},{"ticketId":40,"ticketName":"排列五","odds":1},{"ticketId":41,"ticketName":"江苏快三","odds":1},{"ticketId":42,"ticketName":"安徽快三","odds":1},{"ticketId":43,"ticketName":"广西快三","odds":1},{"ticketId":44,"ticketName":"福建快三","odds":1},{"ticketId":45,"ticketName":"美开分分彩","odds":1},{"ticketId":46,"ticketName":"极速11选5","odds":1},{"ticketId":47,"ticketName":"极速快乐8","odds":1},{"ticketId":48,"ticketName":"极速赛车","odds":1},{"ticketId":49,"ticketName":"分分六合彩","odds":1},{"ticketId":50,"ticketName":"极速快三","odds":1},{"ticketId":54,"ticketName":"美开赛马","odds":1},{"ticketId":55,"ticketName":"美开5分彩","odds":1},{"ticketId":56,"ticketName":"河内5分彩","odds":1},{"ticketId":57,"ticketName":"腾讯分分彩","odds":1},{"ticketId":58,"ticketName":"美开11选5","odds":1},{"ticketId":59,"ticketName":"六合5分彩","odds":1},{"ticketId":89,"ticketName":"美开快三","odds":1}]
     */

    private int client;
    private String memberId;
    private int merchantId;
    private String memberAccount;
    private String memberName;
    /**
     * 会员名称
     */
    private String merchantName;
    private String merchantAccount;
    /**
     * 用户余额
     */
    private String balance;
    private int status;
    private double limitBet;
    private String registIp;
    private String registTime;
    private String loginIp;
    private String loginTime;
    private long doubleGroupId;
    private long standardGroupId;
    private String doubleGroupName;
    private String standardGroupName;
    private List<ListBean> list;
    private String hotColdMiss;
    private String transferType;
    private String merchantAccout;

    public String getMerchantAccout() {
        return merchantAccout;
    }

    public void setMerchantAccout(String merchantAccout) {
        this.merchantAccout = merchantAccout;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getHotColdMiss() {
        return hotColdMiss;
    }

    public void setHotColdMiss(String hotColdMiss) {
        this.hotColdMiss = hotColdMiss;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getBalance() {
        if (balance != null) {
            String[] split = balance.split("\\.");
            String s = split[0];
            String balances = "";
            if (split.length == 2) {
                String s1 = split[1];
                int length = s1.length();
                if (length > 2) {
                    balances = "." + s1.substring(0, 2);
                } else {
                    balances = "." + s1;
                }
            }
            balance = s + "" + balances;

        }
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLimitBet() {
        return limitBet;
    }

    public void setLimitBet(double limitBet) {
        this.limitBet = limitBet;
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public long getDoubleGroupId() {
        return doubleGroupId;
    }

    public void setDoubleGroupId(long doubleGroupId) {
        this.doubleGroupId = doubleGroupId;
    }

    public long getStandardGroupId() {
        return standardGroupId;
    }

    public void setStandardGroupId(long standardGroupId) {
        this.standardGroupId = standardGroupId;
    }

    public String getDoubleGroupName() {
        return doubleGroupName;
    }

    public void setDoubleGroupName(String doubleGroupName) {
        this.doubleGroupName = doubleGroupName;
    }

    public String getStandardGroupName() {
        return standardGroupName;
    }

    public void setStandardGroupName(String standardGroupName) {
        this.standardGroupName = standardGroupName;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ticketId : 1
         * ticketName : 重庆时时彩
         * odds : 1.0
         */

        private int ticketId;
        private String ticketName;
        private double odds;

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }
    }
}
