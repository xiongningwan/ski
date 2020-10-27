package com.ski.box.bean.record;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 四大列表 投注记录 返回ben对象
 */
public class RecordBet implements Parcelable {


    /**
     * total : 14
     * totalPage : 1
     * pageSize : 20
     * currentPage : 1
     * list : [{"orderId":"1252527973630738487","betTime":"2020-04-21 17:21:54","playId":"246020901","playCode":null,"playName":"前二组选","betContent":"01 02","winAmount":0,"betMoney":10,"ticketId":46,"ticketName":"极速11选5","canCancel":false,"seriesId":2,"seriesCode":null,"odd":"{\"1\":\"40.000\"}","betPrize":"{\"1\":\"400.0000\"}","ticketResult":"05,09,10,11,08","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1042","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":null,"nextOrderId":"1252525271966285826","win":false,"displayZuHe":false,"betNum":"01 02"},{"orderId":"1252525271966285826","betTime":"2020-04-21 17:11:10","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":50,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"110.2500\"}","ticketResult":"3,5,6,10,4,8,9,7,2,1","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1032","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252527973630738487","nextOrderId":"1252523446387081268","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252523446387081268","betTime":"2020-04-21 17:03:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"8,6,2,3,9,7,10,5,4,1","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1025","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252525271966285826","nextOrderId":"1252523194812727368","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252523194812727368","betTime":"2020-04-21 17:02:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"9,3,4,7,6,2,8,1,10,5","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1024","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252523446387081268","nextOrderId":"1252522943150293088","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522943150293088","betTime":"2020-04-21 17:01:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"10,2,5,6,1,8,9,4,3,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1023","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252523194812727368","nextOrderId":"1252522691584327716","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522691584327716","betTime":"2020-04-21 17:00:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"2,7,9,1,10,3,4,6,5,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1022","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522943150293088","nextOrderId":"1252522439888339015","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522439888339015","betTime":"2020-04-21 16:59:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,6,7,9,3,10,4,8,2,5","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1021","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522691584327716","nextOrderId":"1252522210963226654","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522210963226654","betTime":"2020-04-21 16:59:00","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"10,7,6,3,4,5,9,2,1,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1020","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522439888339015","nextOrderId":"1252521936446029917","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521936446029917","betTime":"2020-04-21 16:57:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,10,9,2,5,3,6,8,4,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1019","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522210963226654","nextOrderId":"1252521684922007608","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521684922007608","betTime":"2020-04-21 16:56:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"5,9,3,1,4,2,6,8,10,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1018","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521936446029917","nextOrderId":"1252521435000209442","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521435000209442","betTime":"2020-04-21 16:55:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"7,4,5,3,2,6,9,10,1,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1017","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521684922007608","nextOrderId":"1252521375545950247","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521375545950247","betTime":"2020-04-21 16:55:41","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"3,9,6,1,2,8,10,5,4,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1016","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521435000209442","nextOrderId":"1252512310019227658","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252512310019227658","betTime":"2020-04-21 16:19:39","playId":"258010101","playCode":null,"playName":"总和大小","betContent":"大","winAmount":19.6,"betMoney":10,"ticketId":58,"ticketName":"幸运11选5","canCancel":false,"seriesId":2,"seriesCode":null,"odd":"{\"1\":\"1.960\"}","betPrize":"{\"1\":\"19.6000\"}","ticketResult":"05,11,09,06,08","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-196","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521375545950247","nextOrderId":"1252495991928520736","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252495991928520736","betTime":"2020-04-21 15:14:49","playId":"248010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":48,"ticketName":"极速飞艇","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,6,4,5,7,10,9,3,8,2","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-0915","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252512310019227658","nextOrderId":"1252527973630738487","win":false,"displayZuHe":false,"betNum":"da"}]
     */

    private int total;
    private int totalPage;
    private int pageSize;
    private int currentPage;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements  MultiItemEntity, Parcelable {
        /**
         * orderId : 1252527973630738487
         * betTime : 2020-04-21 17:21:54
         * playId : 246020901
         * playCode : null
         * playName : 前二组选
         * betContent : 01 02
         * winAmount : 0
         * betMoney : 10
         * ticketId : 46
         * ticketName : 极速11选5
         * canCancel : false
         * seriesId : 2
         * seriesCode : null
         * odd : {"1":"40.000"}
         * betPrize : {"1":"400.0000"}
         * ticketResult : 05,09,10,11,08
         * solo : false
         * chaseOrder : false
         * groupMode : 2
         * singleGame : false
         * ticketPlanNo : 20200421-1042
         * betStatus : 2
         * betStatusDes : 未中奖
         * betMultiple : 1
         * betModel : 1
         * betNums : 1
         * chaseId : null
         * preOrderId : null
         * nextOrderId : 1252525271966285826
         * win : false
         * displayZuHe : false
         * betNum : 01 02
         */

        private String orderId;
        private String betTime;
        private String playId;
        private String playCode;
        private String playName;
        private String betContent;
        private double winAmount;
        private float betMoney;
        private int ticketId;
        private String ticketName;
        private boolean canCancel;
        private int seriesId;
        private String seriesCode;
        private String odd;
        private String betPrize;
        private String ticketResult;
        private boolean solo;
        private boolean chaseOrder;
        private int groupMode;
        private boolean singleGame;
        private String ticketPlanNo;
        private int betStatus;
        private String betStatusDes;
        private int betMultiple;
        private float betModel;
        private int betNums;
        private long chaseId;
        private long preOrderId;
        private String nextOrderId;
        private boolean win;
        private boolean displayZuHe;
        private String betNum;
        private int itemType;

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBetTime() {
            return betTime;
        }

        public void setBetTime(String betTime) {
            this.betTime = betTime;
        }

        public String getPlayId() {
            return playId;
        }

        public void setPlayId(String playId) {
            this.playId = playId;
        }

        public Object getPlayCode() {
            return playCode;
        }

        public void setPlayCode(String playCode) {
            this.playCode = playCode;
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

        public double getWinAmount() {
            return winAmount;
        }

        public void setWinAmount(int winAmount) {
            this.winAmount = winAmount;
        }

        public float getBetMoney() {
            return betMoney;
        }

        public void setBetMoney(int betMoney) {
            this.betMoney = betMoney;
        }

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

        public boolean isCanCancel() {
            return canCancel;
        }

        public void setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
        }

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public String getSeriesCode() {
            return seriesCode;
        }

        public void setSeriesCode(String seriesCode) {
            this.seriesCode = seriesCode;
        }

        public String getOdd() {
            return odd;
        }

        public void setOdd(String odd) {
            this.odd = odd;
        }

        public String getBetPrize() {
            return betPrize;
        }

        public void setBetPrize(String betPrize) {
            this.betPrize = betPrize;
        }

        public String getTicketResult() {
            return ticketResult;
        }

        public void setTicketResult(String ticketResult) {
            this.ticketResult = ticketResult;
        }

        public boolean isSolo() {
            return solo;
        }

        public void setSolo(boolean solo) {
            this.solo = solo;
        }

        public boolean isChaseOrder() {
            return chaseOrder;
        }

        public void setChaseOrder(boolean chaseOrder) {
            this.chaseOrder = chaseOrder;
        }

        public int getGroupMode() {
            return groupMode;
        }

        public void setGroupMode(int groupMode) {
            this.groupMode = groupMode;
        }

        public boolean isSingleGame() {
            return singleGame;
        }

        public void setSingleGame(boolean singleGame) {
            this.singleGame = singleGame;
        }

        public String getTicketPlanNo() {
            return ticketPlanNo;
        }

        public void setTicketPlanNo(String ticketPlanNo) {
            this.ticketPlanNo = ticketPlanNo;
        }

        public int getBetStatus() {
            return betStatus;
        }

        public void setBetStatus(int betStatus) {
            this.betStatus = betStatus;
        }

        public String getBetStatusDes() {
            return betStatusDes;
        }

        public void setBetStatusDes(String betStatusDes) {
            this.betStatusDes = betStatusDes;
        }

        public int getBetMultiple() {
            return betMultiple;
        }

        public void setBetMultiple(int betMultiple) {
            this.betMultiple = betMultiple;
        }

        public float getBetModel() {
            return betModel;
        }

        public void setBetModel(float betModel) {
            this.betModel = betModel;
        }

        public int getBetNums() {
            return betNums;
        }

        public void setBetNums(int betNums) {
            this.betNums = betNums;
        }

        public long getChaseId() {
            return chaseId;
        }

        public void setChaseId(long chaseId) {
            this.chaseId = chaseId;
        }

        public long getPreOrderId() {
            return preOrderId;
        }

        public void setPreOrderId(long preOrderId) {
            this.preOrderId = preOrderId;
        }

        public String getNextOrderId() {
            return nextOrderId;
        }

        public void setNextOrderId(String nextOrderId) {
            this.nextOrderId = nextOrderId;
        }

        public boolean isWin() {
            return win;
        }

        public void setWin(boolean win) {
            this.win = win;
        }

        public boolean isDisplayZuHe() {
            return displayZuHe;
        }

        public void setDisplayZuHe(boolean displayZuHe) {
            this.displayZuHe = displayZuHe;
        }

        public String getBetNum() {
            return betNum;
        }

        public void setBetNum(String betNum) {
            this.betNum = betNum;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.orderId);
            dest.writeString(this.betTime);
            dest.writeString(this.playId);
            dest.writeString(this.playCode);
            dest.writeString(this.playName);
            dest.writeString(this.betContent);
            dest.writeDouble(this.winAmount);
            dest.writeFloat(this.betMoney);
            dest.writeInt(this.ticketId);
            dest.writeString(this.ticketName);
            dest.writeByte(this.canCancel ? (byte) 1 : (byte) 0);
            dest.writeInt(this.seriesId);
            dest.writeString(this.seriesCode);
            dest.writeString(this.odd);
            dest.writeString(this.betPrize);
            dest.writeString(this.ticketResult);
            dest.writeByte(this.solo ? (byte) 1 : (byte) 0);
            dest.writeByte(this.chaseOrder ? (byte) 1 : (byte) 0);
            dest.writeInt(this.groupMode);
            dest.writeByte(this.singleGame ? (byte) 1 : (byte) 0);
            dest.writeString(this.ticketPlanNo);
            dest.writeInt(this.betStatus);
            dest.writeString(this.betStatusDes);
            dest.writeInt(this.betMultiple);
            dest.writeFloat(this.betModel);
            dest.writeInt(this.betNums);
            dest.writeLong(this.chaseId);
            dest.writeLong(this.preOrderId);
            dest.writeString(this.nextOrderId);
            dest.writeByte(this.win ? (byte) 1 : (byte) 0);
            dest.writeByte(this.displayZuHe ? (byte) 1 : (byte) 0);
            dest.writeString(this.betNum);
            dest.writeInt(this.itemType);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.orderId = in.readString();
            this.betTime = in.readString();
            this.playId = in.readString();
            this.playCode = in.readString();
            this.playName = in.readString();
            this.betContent = in.readString();
            this.winAmount = in.readDouble();
            this.betMoney = in.readFloat();
            this.ticketId = in.readInt();
            this.ticketName = in.readString();
            this.canCancel = in.readByte() != 0;
            this.seriesId = in.readInt();
            this.seriesCode = in.readString();
            this.odd = in.readString();
            this.betPrize = in.readString();
            this.ticketResult = in.readString();
            this.solo = in.readByte() != 0;
            this.chaseOrder = in.readByte() != 0;
            this.groupMode = in.readInt();
            this.singleGame = in.readByte() != 0;
            this.ticketPlanNo = in.readString();
            this.betStatus = in.readInt();
            this.betStatusDes = in.readString();
            this.betMultiple = in.readInt();
            this.betModel = in.readFloat();
            this.betNums = in.readInt();
            this.chaseId = in.readLong();
            this.preOrderId = in.readLong();
            this.nextOrderId = in.readString();
            this.win = in.readByte() != 0;
            this.displayZuHe = in.readByte() != 0;
            this.betNum = in.readString();
            this.itemType = in.readInt();
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
        dest.writeInt(this.totalPage);
        dest.writeInt(this.pageSize);
        dest.writeInt(this.currentPage);
        dest.writeTypedList(this.list);
    }

    public RecordBet() {
    }

    protected RecordBet(Parcel in) {
        this.total = in.readInt();
        this.totalPage = in.readInt();
        this.pageSize = in.readInt();
        this.currentPage = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Creator<RecordBet> CREATOR = new Creator<RecordBet>() {
        @Override
        public RecordBet createFromParcel(Parcel source) {
            return new RecordBet(source);
        }

        @Override
        public RecordBet[] newArray(int size) {
            return new RecordBet[size];
        }
    };
}
