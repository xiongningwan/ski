package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;


public class LotteryPlaySub implements Parcelable {
    /**
     * [前二组选，前三组选]
     */
    private long playId;
    private String remoteCodeUp;
    private String remoteCode;
    private String titleSub; // 副标题
    private List<LotteryPlayEnd> lotteryPlayEnds;
    private boolean isSelected;
    private String odds;
    private String code;
    private String coldHotMissLayout;//冷热遗漏布局
    private int seatType;//任选 位置

    // 风控
    /**
     * 单挑状态
     */
    private int smStatus;
    /**
     * 单挑注数
     */
    private String smLimit;
    /**
     * 奖金组：返奖率
     */
    private List<RaBean> rate;

    public LotteryPlaySub() {
    }

    public LotteryPlaySub(String titleSub) {
        this.titleSub = titleSub;
    }

    public LotteryPlaySub(String titleSub, String chmLayout) {
        this.titleSub = titleSub;
        this.coldHotMissLayout = chmLayout;
    }

    public LotteryPlaySub(String titleSub, String odds, String code) {
        this(titleSub, odds, code, MethodIdInterface.S_Nothing);
    }

    public LotteryPlaySub(String titleSub, String odds, String remoteCode, String chmLayout) {
        this.titleSub = titleSub;
        this.odds = odds;
        this.remoteCode = remoteCode;
        this.coldHotMissLayout = chmLayout;
    }


    public LotteryPlaySub(String titleSub, String odds, String remoteCodeUp, String remoteCode, String chmLayout,long playId) {
        this.titleSub = titleSub;
        this.odds = odds;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
        this.coldHotMissLayout = chmLayout;
        this.playId=playId;
    }

    public LotteryPlaySub(String titleSub, String odds, String remoteCodeUp, String remoteCode, String chmLayout) {
        this.titleSub = titleSub;
        this.odds = odds;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
        this.coldHotMissLayout = chmLayout;
    }

    public LotteryPlaySub(String titleSub, List<LotteryPlayEnd> lotteryPlayEnds, boolean isSelected, String odds) {
        this.titleSub = titleSub;
        this.lotteryPlayEnds = lotteryPlayEnds;
        this.isSelected = isSelected;
        this.odds = odds;
    }

    public String getRemoteCode() {
        return remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        this.remoteCode = remoteCode;
    }

    public long getPlayId() {
        return playId;
    }

    public void setPlayId(long playId) {
        this.playId = playId;
    }

    public String getTitleSub() {
        return titleSub;
    }

    public void setTitleSub(String titleSub) {
        this.titleSub = titleSub;
    }

    public List<LotteryPlayEnd> getLotteryPlayEnds() {
        return lotteryPlayEnds;
    }

    public void setLotteryPlayEnds(List<LotteryPlayEnd> lotteryPlayEnds) {
        this.lotteryPlayEnds = lotteryPlayEnds;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColdHotMissLayout() {
        return coldHotMissLayout;
    }

    public void setColdHotMissLayout(String coldHotMissLayout) {
        this.coldHotMissLayout = coldHotMissLayout;
    }

    public String getRemoteCodeUp() {
        return remoteCodeUp;
    }

    public void setRemoteCodeUp(String remoteCodeUp) {
        this.remoteCodeUp = remoteCodeUp;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public int getSmStatus() {
        return smStatus;
    }

    public void setSmStatus(int smStatus) {
        this.smStatus = smStatus;
    }

    public String getSmLimit() {
        return smLimit;
    }

    public void setSmLimit(String smLimit) {
        this.smLimit = smLimit;
    }

    public List<RaBean> getRate() {
        return rate;
    }

    public void setRate(List<RaBean> rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.playId);
        dest.writeString(this.remoteCodeUp);
        dest.writeString(this.remoteCode);
        dest.writeString(this.titleSub);
        dest.writeTypedList(this.lotteryPlayEnds);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeString(this.odds);
        dest.writeString(this.code);
        dest.writeString(this.coldHotMissLayout);
        dest.writeInt(this.seatType);
        dest.writeInt(this.smStatus);
        dest.writeString(this.smLimit);
        dest.writeTypedList(this.rate);
    }

    protected LotteryPlaySub(Parcel in) {
        this.playId = in.readLong();
        this.remoteCodeUp = in.readString();
        this.remoteCode = in.readString();
        this.titleSub = in.readString();
        this.lotteryPlayEnds = in.createTypedArrayList(LotteryPlayEnd.CREATOR);
        this.isSelected = in.readByte() != 0;
        this.odds = in.readString();
        this.code = in.readString();
        this.coldHotMissLayout = in.readString();
        this.seatType = in.readInt();
        this.smStatus = in.readInt();
        this.smLimit = in.readString();
        this.rate = in.createTypedArrayList(RaBean.CREATOR);
    }

    public static final Creator<LotteryPlaySub> CREATOR = new Creator<LotteryPlaySub>() {
        @Override
        public LotteryPlaySub createFromParcel(Parcel source) {
            return new LotteryPlaySub(source);
        }

        @Override
        public LotteryPlaySub[] newArray(int size) {
            return new LotteryPlaySub[size];
        }
    };
}
