package com.ski.box.bean.group;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tom on 2020/10/31.
 */
public class GroupMoneyData {
    /**
     * pageSize : 10
     * total : 17
     * currentPage : 1
     * list : [{"memberAccount":"agent09","transId":1322219840433295423,"transType":"4","ticketName":"幸运分分彩","orderId":1322218975555227715,"beforeAmt":"1000365.2800","transAmt":"0.4000","afterAmt":"1000365.6800","transTime":"2020-10-31 00:52:30"},{"memberAccount":"agent03","transId":1322219840366186559,"transType":"4","ticketName":"幸运分分彩","orderId":1322218975555227715,"beforeAmt":"694.1331","transAmt":"0.6500","afterAmt":"694.7831","transTime":"2020-10-31 00:52:30"},{"memberAccount":"agent09","transId":1322219839846092850,"transType":"4","ticketName":"幸运分分彩","orderId":1322218958895452251,"beforeAmt":"1000364.8800","transAmt":"0.4000","afterAmt":"1000365.2800","transTime":"2020-10-31 00:52:30"},{"memberAccount":"agent03","transId":1322219839753818126,"transType":"4","ticketName":"幸运分分彩","orderId":1322218958895452251,"beforeAmt":"693.4831","transAmt":"0.6500","afterAmt":"694.1331","transTime":"2020-10-31 00:52:30"},{"memberAccount":"agent11","transId":1322219213313544251,"transType":"5","ticketName":"幸运分分彩","orderId":1322218958895452251,"beforeAmt":"952998.3980","transAmt":"195.2000","afterAmt":"953193.5980","transTime":"2020-10-31 00:50:00"},{"memberAccount":"agent11","transId":1322218975572004881,"transType":"3","ticketName":"幸运分分彩","orderId":1322218975555227715,"beforeAmt":"953098.3980","transAmt":"-100.0000","afterAmt":"952998.3980","transTime":"2020-10-31 00:49:04"},{"memberAccount":"agent11","transId":1322218958924812323,"transType":"3","ticketName":"幸运分分彩","orderId":1322218958895452251,"beforeAmt":"953198.3980","transAmt":"-100.0000","afterAmt":"953098.3980","transTime":"2020-10-31 00:49:00"},{"memberAccount":"agent03","transId":1322218330739707993,"transType":"4","ticketName":"极速赛车","orderId":1322217646418038826,"beforeAmt":"692.9831","transAmt":"0.5000","afterAmt":"693.4831","transTime":"2020-10-31 00:46:30"},{"memberAccount":"agent03","transId":1322218330647433277,"transType":"4","ticketName":"极速赛车","orderId":1322217614621020186,"beforeAmt":"692.6081","transAmt":"0.3750","afterAmt":"692.9831","transTime":"2020-10-31 00:46:30"},{"memberAccount":"agent03","transId":1322218330509021261,"transType":"4","ticketName":"极速赛车","orderId":1322217567632232521,"beforeAmt":"692.3581","transAmt":"0.2500","afterAmt":"692.6081","transTime":"2020-10-31 00:46:30"}]
     * totalPage : 2
     */

    private int pageSize;
    private int total;
    private int currentPage;
    private int totalPage;
    private List<ListBean> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * memberAccount : agent09
         * transId : 1322219840433295423
         * transType : 4
         * ticketName : 幸运分分彩
         * orderId : 1322218975555227715
         * beforeAmt : 1000365.2800
         * transAmt : 0.4000
         * afterAmt : 1000365.6800
         * transTime : 2020-10-31 00:52:30
         */

        private String memberAccount;
        private long transId;
        private int transType;
        private String ticketName;
        private long orderId;
        private BigDecimal beforeAmt;
        private double transAmt;
        private BigDecimal afterAmt;
        private String transTime;

        public String getMemberAccount() {
            return memberAccount;
        }

        public void setMemberAccount(String memberAccount) {
            this.memberAccount = memberAccount;
        }

        public long getTransId() {
            return transId;
        }

        public void setTransId(long transId) {
            this.transId = transId;
        }

        public int getTransType() {
            return transType;
        }

        public void setTransType(int transType) {
            this.transType = transType;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public BigDecimal getBeforeAmt() {
            return beforeAmt;
        }

        public void setBeforeAmt(BigDecimal beforeAmt) {
            this.beforeAmt = beforeAmt;
        }

        public double getTransAmt() {
            return transAmt;
        }

        public void setTransAmt(double transAmt) {
            this.transAmt = transAmt;
        }

        public BigDecimal getAfterAmt() {
            return afterAmt;
        }

        public void setAfterAmt(BigDecimal afterAmt) {
            this.afterAmt = afterAmt;
        }

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }
    }
}
