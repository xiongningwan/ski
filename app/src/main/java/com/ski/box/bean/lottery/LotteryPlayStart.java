package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LotteryPlayStart implements Parcelable {
    /**
     * [双面，组选]
     */
    private long id;
    private String code;
    private String remoteCode;
    private String title; // 标题
    private List<LotteryPlaySub> subPlays;
    private String handlerKey;
    private int itemPlayId;//玩法ID
    private boolean isLocate;
    private boolean isSelected;

    public LotteryPlayStart() {
    }

    public LotteryPlayStart(String title, boolean isSelected) {
        this.title = title;
        this.isSelected = isSelected;
    }

    public LotteryPlayStart(String title, boolean isSelected, String remoteCode) {
        this.title = title;
        this.isSelected = isSelected;
        this.remoteCode = remoteCode;
    }

    public LotteryPlayStart(String title) {
        this.title = title;
    }

    public LotteryPlayStart(String title, String remoteCode) {
        this.title = title;
        this.remoteCode = remoteCode;
    }

    public String getRemoteCode() {
        return remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        this.remoteCode = remoteCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHandlerKey() {
        return handlerKey;
    }

    public void setHandlerKey(String handlerKey) {
        this.handlerKey = handlerKey;
    }

    public int getItemPlayId() {
        return itemPlayId;
    }

    public void setItemPlayId(int itemPlayId) {
        this.itemPlayId = itemPlayId;
    }

    public List<LotteryPlaySub> getSubPlays() {
        return subPlays;
    }

    public void setSubPlays(List<LotteryPlaySub> subPlays) {
        this.subPlays = subPlays;
    }

    public boolean isLocate() {
        return isLocate;
    }

    public void setLocate(boolean locate) {
        isLocate = locate;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.code);
        dest.writeString(this.remoteCode);
        dest.writeString(this.title);
        dest.writeTypedList(this.subPlays);
        dest.writeString(this.handlerKey);
        dest.writeInt(this.itemPlayId);
        dest.writeByte(this.isLocate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected LotteryPlayStart(Parcel in) {
        this.id = in.readLong();
        this.code = in.readString();
        this.remoteCode = in.readString();
        this.title = in.readString();
        this.subPlays = in.createTypedArrayList(LotteryPlaySub.CREATOR);
        this.handlerKey = in.readString();
        this.itemPlayId = in.readInt();
        this.isLocate = in.readByte() != 0;
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<LotteryPlayStart> CREATOR = new Creator<LotteryPlayStart>() {
        @Override
        public LotteryPlayStart createFromParcel(Parcel source) {
            return new LotteryPlayStart(source);
        }

        @Override
        public LotteryPlayStart[] newArray(int size) {
            return new LotteryPlayStart[size];
        }
    };
}
