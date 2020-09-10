package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;


public class LotteryPlay implements MultiItemEntity, Parcelable {
    private long id;
    private String remoteCodeUp;
    private String remoteCode;
    private String tag;
    private String name;
    private String zodiac;
    private String code;
    private String odds = "";
    private String standIn; //辅助显示内容
    private boolean isSelected;
    private int itemType;//样式
    private String miss = "0"; // 遗漏
    private String coldHot_20 = "0"; // 冷热 20 期
    private String coldHot_50 = "0"; // 冷热 50
    private String coldHot_100 = "0"; // 冷热 100
    private int coldHot_color_20;
    private int coldHot_color_50;
    private int coldHot_color_100;
    private int coldHot_color_miss;

    private long roadId; // 映射路子图

    public LotteryPlay() {
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, int itemType) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.itemType = itemType;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, int itemType, String remoteCodeUp, String remoteCode) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.itemType = itemType;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, String remoteCode, int itemType) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.remoteCode = remoteCode;
        this.itemType = itemType;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, String remoteCodeUp, String remoteCode, int itemType) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
        this.itemType = itemType;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, long roadId) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.roadId = roadId;
    }

    public LotteryPlay(long id, String tag, String name, String code, long roadId) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.roadId = roadId;
    }

    public LotteryPlay(long id, String tag, String name, String code, long roadId, int itemType) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.roadId = roadId;
        this.itemType = itemType;
    }

    public LotteryPlay(long id, String tag, String name, String code, String odds, String remoteCodeUp, String remoteCode) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.odds = odds;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
    }

    public LotteryPlay(long id, String tag, String name, String code, long roadId, String remoteCodeUp, String remoteCode) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.roadId = roadId;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
    }

    public LotteryPlay(long id, String tag, String name, String code, long roadId, int itemType, String remoteCodeUp, String remoteCode) {
        this.id = id;
        this.tag = tag;
        this.name = name;
        this.code = code;
        this.roadId = roadId;
        this.itemType = itemType;
        this.remoteCodeUp = remoteCodeUp;
        this.remoteCode = remoteCode;
    }


    public String getRemoteCode() {
        return remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        this.remoteCode = remoteCode;
    }

    public String getRemoteCodeUp() {
        return remoteCodeUp;
    }

    public void setRemoteCodeUp(String remoteCodeUp) {
        this.remoteCodeUp = remoteCodeUp;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getStandIn() {
        return standIn;
    }

    public void setStandIn(String standIn) {
        this.standIn = standIn;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMiss() {
        return miss;
    }

    public void setMiss(String miss) {
        this.miss = miss;
    }


    public String getColdHot_20() {
        return coldHot_20;
    }

    public void setColdHot_20(String coldHot_20) {
        this.coldHot_20 = coldHot_20;
    }

    public String getColdHot_50() {
        return coldHot_50;
    }

    public void setColdHot_50(String coldHot_50) {
        this.coldHot_50 = coldHot_50;
    }

    public String getColdHot_100() {
        return coldHot_100;
    }

    public void setColdHot_100(String coldHot_100) {
        this.coldHot_100 = coldHot_100;
    }

    public long getRoadId() {
        return roadId;
    }

    public void setRoadId(long roadId) {
        this.roadId = roadId;
    }

    public int getColdHot_color_20() {
        return coldHot_color_20;
    }

    public void setColdHot_color_20(int coldHot_color_20) {
        this.coldHot_color_20 = coldHot_color_20;
    }

    public int getColdHot_color_50() {
        return coldHot_color_50;
    }

    public void setColdHot_color_50(int coldHot_color_50) {
        this.coldHot_color_50 = coldHot_color_50;
    }

    public int getColdHot_color_100() {
        return coldHot_color_100;
    }

    public void setColdHot_color_100(int coldHot_color_100) {
        this.coldHot_color_100 = coldHot_color_100;
    }

    public int getColdHot_color_miss() {
        return coldHot_color_miss;
    }

    public void setColdHot_color_miss(int coldHot_color_miss) {
        this.coldHot_color_miss = coldHot_color_miss;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.remoteCodeUp);
        dest.writeString(this.remoteCode);
        dest.writeString(this.tag);
        dest.writeString(this.name);
        dest.writeString(this.zodiac);
        dest.writeString(this.code);
        dest.writeString(this.odds);
        dest.writeString(this.standIn);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.itemType);
        dest.writeString(this.miss);
        dest.writeString(this.coldHot_20);
        dest.writeString(this.coldHot_50);
        dest.writeString(this.coldHot_100);
        dest.writeInt(this.coldHot_color_20);
        dest.writeInt(this.coldHot_color_50);
        dest.writeInt(this.coldHot_color_100);
        dest.writeInt(this.coldHot_color_miss);
        dest.writeLong(this.roadId);
    }

    protected LotteryPlay(Parcel in) {
        this.id = in.readLong();
        this.remoteCodeUp = in.readString();
        this.remoteCode = in.readString();
        this.tag = in.readString();
        this.name = in.readString();
        this.zodiac = in.readString();
        this.code = in.readString();
        this.odds = in.readString();
        this.standIn = in.readString();
        this.isSelected = in.readByte() != 0;
        this.itemType = in.readInt();
        this.miss = in.readString();
        this.coldHot_20 = in.readString();
        this.coldHot_50 = in.readString();
        this.coldHot_100 = in.readString();
        this.coldHot_color_20 = in.readInt();
        this.coldHot_color_50 = in.readInt();
        this.coldHot_color_100 = in.readInt();
        this.coldHot_color_miss = in.readInt();
        this.roadId = in.readLong();
    }

    public static final Creator<LotteryPlay> CREATOR = new Creator<LotteryPlay>() {
        @Override
        public LotteryPlay createFromParcel(Parcel source) {
            return new LotteryPlay(source);
        }

        @Override
        public LotteryPlay[] newArray(int size) {
            return new LotteryPlay[size];
        }
    };
}
