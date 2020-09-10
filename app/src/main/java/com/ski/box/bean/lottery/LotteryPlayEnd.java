package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class LotteryPlayEnd implements Parcelable, MultiItemEntity {
    /**
     * 整个投注版面
     * [第一球，第二球]
     */
    private String remoteCode;
    private String tag;
    private boolean isHideTag; // 隐藏tag
    private boolean isRandom;
    private boolean isCanExpand = true; // 是否能收缩
    private boolean isDraft;
    private List<LotteryPlay> lotteryPlays;
    private boolean isSelected;

    private int spanCount;
    private int divider; // line block
    private int itemType;
    /*ture 隐藏 false 显示*/
    private boolean isNotFilter; // 是否有过滤 大小单双清
    private int limit;//可选个数限制

    public boolean isNotFilter() {
        return isNotFilter;
    }

    public void setNotFilter(boolean notFilter) {
        isNotFilter = notFilter;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getRemoteCode() {
        return remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        this.remoteCode = remoteCode;
    }

    public boolean isCanExpand() {
        return isCanExpand;
    }

    public void setCanExpand(boolean canExpand) {
        isCanExpand = canExpand;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public List<LotteryPlay> getLotteryPlays() {
        return lotteryPlays;
    }

    public void setLotteryPlays(List<LotteryPlay> lotteryPlays) {
        this.lotteryPlays = lotteryPlays;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }


    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public boolean isHideTag() {
        return isHideTag;
    }

    public void setHideTag(boolean hideTag) {
        isHideTag = hideTag;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.remoteCode);
        dest.writeString(this.tag);
        dest.writeByte(this.isHideTag ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isRandom ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCanExpand ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDraft ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.lotteryPlays);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.spanCount);
        dest.writeInt(this.divider);
        dest.writeInt(this.itemType);
        dest.writeByte(this.isNotFilter ? (byte) 1 : (byte) 0);
        dest.writeInt(this.limit);
    }

    public LotteryPlayEnd() {
    }

    public LotteryPlayEnd(int itemType) {
        this.itemType = itemType;
    }

    public LotteryPlayEnd(int itemType, int limit) {
        this.itemType = itemType;
        this.limit = limit;
    }

    protected LotteryPlayEnd(Parcel in) {
        this.remoteCode = in.readString();
        this.tag = in.readString();
        this.isHideTag = in.readByte() != 0;
        this.isRandom = in.readByte() != 0;
        this.isCanExpand = in.readByte() != 0;
        this.isDraft = in.readByte() != 0;
        this.lotteryPlays = in.createTypedArrayList(LotteryPlay.CREATOR);
        this.isSelected = in.readByte() != 0;
        this.spanCount = in.readInt();
        this.divider = in.readInt();
        this.itemType = in.readInt();
        this.isNotFilter = in.readByte() != 0;
        this.limit = in.readInt();
    }

    public static final Creator<LotteryPlayEnd> CREATOR = new Creator<LotteryPlayEnd>() {
        @Override
        public LotteryPlayEnd createFromParcel(Parcel source) {
            return new LotteryPlayEnd(source);
        }

        @Override
        public LotteryPlayEnd[] newArray(int size) {
            return new LotteryPlayEnd[size];
        }
    };
}
