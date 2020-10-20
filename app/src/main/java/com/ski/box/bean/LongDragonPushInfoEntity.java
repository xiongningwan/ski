package com.ski.box.bean;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * 提醒推送信息
 * 长龙与遗漏
 */
public class LongDragonPushInfoEntity implements Serializable, MultiItemEntity {
    private Number seriesId;
    private String seriesCode;
    private Number ticketId;
    private String ticketName;
    private Number playId;
    private String playName;
    private String playCode;
    private Number times;
    private Number desireRate;
    private String playItemName;
    private String playGroupCode;
    private String playLevelCode;
    private Number playItemId;
    private String planId;
    private Number playLevelId;
    private long curEndSaleTime;
    private boolean isDrawing;
    private int itemType;
    private long cdTime; // 倒计时
    private int  currentTime;

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public long getCdTime() {
        return cdTime;
    }

    public void setCdTime(long cdTime) {
        this.cdTime = cdTime;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Number getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Number seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public Number getTicketId() {
        return ticketId;
    }

    public void setTicketId(Number ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Number getPlayId() {
        return playId;
    }

    public void setPlayId(Number playId) {
        this.playId = playId;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public Number getTimes() {
        return times;
    }

    public void setTimes(Number times) {
        this.times = times;
    }

    public Number getDesireRate() {
        return desireRate;
    }

    public void setDesireRate(Number desireRate) {
        this.desireRate = desireRate;
    }

    public String getPlayItemName() {
        return playItemName;
    }

    public void setPlayItemName(String playItemName) {
        this.playItemName = playItemName;
    }

    public String getPlayGroupCode() {
        return playGroupCode;
    }

    public void setPlayGroupCode(String playGroupCode) {
        this.playGroupCode = playGroupCode;
    }

    public String getPlayLevelCode() {
        return playLevelCode;
    }

    public void setPlayLevelCode(String playLevelCode) {
        this.playLevelCode = playLevelCode;
    }

    public Number getPlayItemId() {
        return playItemId;
    }

    public void setPlayItemId(Number playItemId) {
        this.playItemId = playItemId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Number getPlayLevelId() {
        return playLevelId;
    }

    public void setPlayLevelId(Number playLevelId) {
        this.playLevelId = playLevelId;
    }

    public long getCurEndSaleTime() {
        return curEndSaleTime;
    }

    public void setCurEndSaleTime(long curEndSaleTime) {
        this.curEndSaleTime = curEndSaleTime;
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof LongDragonPushInfoEntity) {
            LongDragonPushInfoEntity vo = (LongDragonPushInfoEntity) obj;

            if (vo.playId.equals(this.playId) && vo.playItemId.equals(this.playItemId))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.playId.hashCode() * this.playItemId.hashCode();
    }
}
