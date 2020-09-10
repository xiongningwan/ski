package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class RemoteLotteryPlay implements MultiItemEntity, Parcelable {
    private long id;
    private String name;
    private String code;
    private String odds;
    private List<RemoteLotteryPlay> list;
    private boolean isDefaultCode; // 默认替换， true 不替换

    private boolean isSelected;

    //------------------------
    private String tag;
    private int mode; // 0 单式，1复试
    private int divider; // line block
    private int itemType; // 方形状还是圆形，特殊
    private int spanCount; // 一排几个
    private int miss; // 遗漏
    private int coldHot; // 冷热
    private boolean hasTab2;// 是否有二级目录
    private boolean isTabOdds;// 是否显示tab赔率

    public boolean isTabOdds() {
        return isTabOdds;
    }

    public void setTabOdds(boolean tabOdds) {
        isTabOdds = tabOdds;
    }

    public boolean isHasTab2() {
        return hasTab2;
    }

    public void setHasTab2(boolean hasTab2) {
        this.hasTab2 = hasTab2;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getColdHot() {
        return coldHot;
    }

    public void setColdHot(int coldHot) {
        this.coldHot = coldHot;
    }

    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
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

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public List<RemoteLotteryPlay> getList() {
        return list;
    }

    public void setList(List<RemoteLotteryPlay> list) {
        this.list = list;
    }


    public boolean isDefaultCode() {
        return isDefaultCode;
    }

    public void setDefaultCode(boolean defaultCode) {
        isDefaultCode = defaultCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.odds);
        dest.writeTypedList(this.list);
        dest.writeByte(this.isDefaultCode ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeString(this.tag);
        dest.writeInt(this.mode);
        dest.writeInt(this.divider);
        dest.writeInt(this.itemType);
        dest.writeInt(this.spanCount);
        dest.writeInt(this.miss);
        dest.writeInt(this.coldHot);
        dest.writeByte(this.hasTab2 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTabOdds ? (byte) 1 : (byte) 0);
    }

    public RemoteLotteryPlay() {
    }

    protected RemoteLotteryPlay(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.code = in.readString();
        this.odds = in.readString();
        this.list = in.createTypedArrayList(RemoteLotteryPlay.CREATOR);
        this.isDefaultCode = in.readByte() != 0;
        this.isSelected = in.readByte() != 0;
        this.tag = in.readString();
        this.mode = in.readInt();
        this.divider = in.readInt();
        this.itemType = in.readInt();
        this.spanCount = in.readInt();
        this.miss = in.readInt();
        this.coldHot = in.readInt();
        this.hasTab2 = in.readByte() != 0;
        this.isTabOdds = in.readByte() != 0;
    }

    public static final Creator<RemoteLotteryPlay> CREATOR = new Creator<RemoteLotteryPlay>() {
        @Override
        public RemoteLotteryPlay createFromParcel(Parcel source) {
            return new RemoteLotteryPlay(source);
        }

        @Override
        public RemoteLotteryPlay[] newArray(int size) {
            return new RemoteLotteryPlay[size];
        }
    };
}
