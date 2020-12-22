package com.ski.box.bean.group;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tom on 2020/10/30.
 */
public class GroupBetData implements Parcelable {
    /**
     * total : 20
     * pageBetAmt : 5000
     * pageWinAmt : 7715.45
     * totalPage : 2
     * totalBetAmt : 10000
     * pageSize : 10
     * currentPage : 1
     * list : [{"memberAccount":"agent11","orderId":1321530757339942972,"issueNo":"20201029-0195","ticketName":"极速飞艇","playName":"冠军大小","betContent":"大","betAmt":"500.0000","winAmt":"986.0000","status":"已中奖","betTime":"2020-10-29 03:14:20","rebate":"1972","odds":"[1.972]","openResult":"7,4,10,5,2,8,6,1,9,3"},{"memberAccount":"agent11","orderId":1321530757335748627,"issueNo":"20201029-0195","ticketName":"极速飞艇","playName":"冠亚和单双","betContent":"单","betAmt":"500.0000","winAmt":"887.4000","status":"已中奖","betTime":"2020-10-29 03:14:20","rebate":"1972","odds":"[1.774]","openResult":"7,4,10,5,2,8,6,1,9,3"},{"memberAccount":"agent11","orderId":1321530757331554372,"issueNo":"20201029-0195","ticketName":"极速飞艇","playName":"冠亚和大小","betContent":"大","betAmt":"500.0000","winAmt":"0.0000","status":"未中奖","betTime":"2020-10-29 03:14:20","rebate":"1972","odds":"[2.218]","openResult":"7,4,10,5,2,8,6,1,9,3"},{"memberAccount":"agent11","orderId":1321530586384306266,"issueNo":"20201029-0194","ticketName":"极速赛车","playName":"冠军单双","betContent":"单","betAmt":"500.0000","winAmt":"986.0000","status":"已中奖","betTime":"2020-10-29 03:13:39","rebate":"1972","odds":"[1.972]","openResult":"1,9,7,3,5,8,2,6,10,4"},{"memberAccount":"agent11","orderId":1321530586380111951,"issueNo":"20201029-0194","ticketName":"极速赛车","playName":"冠军大小","betContent":"大","betAmt":"500.0000","winAmt":"0.0000","status":"未中奖","betTime":"2020-10-29 03:13:39","rebate":"1972","odds":"[1.972]","openResult":"1,9,7,3,5,8,2,6,10,4"},{"memberAccount":"agent11","orderId":1321530586371723319,"issueNo":"20201029-0194","ticketName":"极速赛车","playName":"冠亚和单双","betContent":"双","betAmt":"500.0000","winAmt":"1109.2500","status":"已中奖","betTime":"2020-10-29 03:13:39","rebate":"1972","odds":"[2.218]","openResult":"1,9,7,3,5,8,2,6,10,4"},{"memberAccount":"agent11","orderId":1321530174407184410,"issueNo":"20201029-0193","ticketName":"极速赛车","playName":"冠军单双","betContent":"单","betAmt":"500.0000","winAmt":"986.0000","status":"已中奖","betTime":"2020-10-29 03:12:01","rebate":"1972","odds":"[1.972]","openResult":"9,10,7,8,2,3,5,1,4,6"},{"memberAccount":"agent11","orderId":1321530174394601484,"issueNo":"20201029-0193","ticketName":"极速赛车","playName":"冠军大小","betContent":"大","betAmt":"500.0000","winAmt":"986.0000","status":"已中奖","betTime":"2020-10-29 03:12:01","rebate":"1972","odds":"[1.972]","openResult":"9,10,7,8,2,3,5,1,4,6"},{"memberAccount":"agent11","orderId":1321530086519738372,"issueNo":"20201029-0192","ticketName":"极速赛车","playName":"冠亚和单双","betContent":"单","betAmt":"500.0000","winAmt":"887.4000","status":"已中奖","betTime":"2020-10-29 03:11:40","rebate":"1972","odds":"[1.774]","openResult":"7,4,9,6,2,1,10,5,8,3"},{"memberAccount":"agent11","orderId":1321530086511349845,"issueNo":"20201029-0192","ticketName":"极速赛车","playName":"冠亚和大小","betContent":"小","betAmt":"500.0000","winAmt":"887.4000","status":"已中奖","betTime":"2020-10-29 03:11:40","rebate":"1972","odds":"[1.774]","openResult":"7,4,9,6,2,1,10,5,8,3"}]
     * totalWinAmt : 11649.45
     */

    private int total;
    private int pageBetAmt;
    private double pageWinAmt;
    private int totalPage;
    private int totalBetAmt;
    private int pageSize;
    private int currentPage;
    private double totalWinAmt;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageBetAmt() {
        return pageBetAmt;
    }

    public void setPageBetAmt(int pageBetAmt) {
        this.pageBetAmt = pageBetAmt;
    }

    public double getPageWinAmt() {
        return pageWinAmt;
    }

    public void setPageWinAmt(double pageWinAmt) {
        this.pageWinAmt = pageWinAmt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalBetAmt() {
        return totalBetAmt;
    }

    public void setTotalBetAmt(int totalBetAmt) {
        this.totalBetAmt = totalBetAmt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public double getTotalWinAmt() {
        return totalWinAmt;
    }

    public void setTotalWinAmt(double totalWinAmt) {
        this.totalWinAmt = totalWinAmt;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * memberAccount : agent11
         * orderId : 1321530757339942972
         * issueNo : 20201029-0195
         * ticketName : 极速飞艇
         * playName : 冠军大小
         * betContent : 大
         * betAmt : 500.0000
         * winAmt : 986.0000
         * status : 已中奖
         * betTime : 2020-10-29 03:14:20
         * rebate : 1972
         * odds : [1.972]
         * openResult : 7,4,10,5,2,8,6,1,9,3
         */

        private String memberAccount;
        private long orderId;
        private String issueNo;
        private String ticketName;
        private String playName;
        private String betContent;
        private String betAmt;
        private String winAmt;
        private String status;
        private String betTime;
        private String rebate;
        private String odds;
        private String openResult;
        private int ticketId;
        private int seriesId;

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getMemberAccount() {
            return memberAccount;
        }

        public void setMemberAccount(String memberAccount) {
            this.memberAccount = memberAccount;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getIssueNo() {
            return issueNo;
        }

        public void setIssueNo(String issueNo) {
            this.issueNo = issueNo;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public String getPlayName() {
            return playName;
        }

        public void setPlayName(String playName) {
            this.playName = playName;
        }

        public String getBetContent() {
            return betContent;
        }

        public void setBetContent(String betContent) {
            this.betContent = betContent;
        }

        public String getBetAmt() {
            return betAmt;
        }

        public void setBetAmt(String betAmt) {
            this.betAmt = betAmt;
        }

        public String getWinAmt() {
            return winAmt;
        }

        public void setWinAmt(String winAmt) {
            this.winAmt = winAmt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBetTime() {
            return betTime;
        }

        public void setBetTime(String betTime) {
            this.betTime = betTime;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getOpenResult() {
            return openResult;
        }

        public void setOpenResult(String openResult) {
            this.openResult = openResult;
        }

        public ListBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.memberAccount);
            dest.writeLong(this.orderId);
            dest.writeString(this.issueNo);
            dest.writeString(this.ticketName);
            dest.writeString(this.playName);
            dest.writeString(this.betContent);
            dest.writeString(this.betAmt);
            dest.writeString(this.winAmt);
            dest.writeString(this.status);
            dest.writeString(this.betTime);
            dest.writeString(this.rebate);
            dest.writeString(this.odds);
            dest.writeString(this.openResult);
            dest.writeInt(this.ticketId);
            dest.writeInt(this.seriesId);
        }

        protected ListBean(Parcel in) {
            this.memberAccount = in.readString();
            this.orderId = in.readLong();
            this.issueNo = in.readString();
            this.ticketName = in.readString();
            this.playName = in.readString();
            this.betContent = in.readString();
            this.betAmt = in.readString();
            this.winAmt = in.readString();
            this.status = in.readString();
            this.betTime = in.readString();
            this.rebate = in.readString();
            this.odds = in.readString();
            this.openResult = in.readString();
            this.ticketId = in.readInt();
            this.seriesId = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeInt(this.pageBetAmt);
        dest.writeDouble(this.pageWinAmt);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.totalBetAmt);
        dest.writeInt(this.pageSize);
        dest.writeInt(this.currentPage);
        dest.writeDouble(this.totalWinAmt);
        dest.writeTypedList(this.list);
    }

    public GroupBetData() {
    }

    protected GroupBetData(Parcel in) {
        this.total = in.readInt();
        this.pageBetAmt = in.readInt();
        this.pageWinAmt = in.readDouble();
        this.totalPage = in.readInt();
        this.totalBetAmt = in.readInt();
        this.pageSize = in.readInt();
        this.currentPage = in.readInt();
        this.totalWinAmt = in.readDouble();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<GroupBetData> CREATOR = new Parcelable.Creator<GroupBetData>() {
        @Override
        public GroupBetData createFromParcel(Parcel source) {
            return new GroupBetData(source);
        }

        @Override
        public GroupBetData[] newArray(int size) {
            return new GroupBetData[size];
        }
    };
}
