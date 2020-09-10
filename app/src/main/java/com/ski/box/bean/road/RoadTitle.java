package com.ski.box.bean.road;

import android.os.Parcel;
import android.os.Parcelable;

import com.yb.core.utils.AppUtil;
import com.yb.core.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class RoadTitle implements Parcelable {
    private String title;
    private List<RoadSub> subList;
    private boolean isSelected;

    public RoadTitle() {
    }

    public RoadTitle(String title, String[] subTitles) {
        this.title = title;
        subList = new ArrayList<>();
        for (String subTitle : subTitles) {
            subList.add(new RoadSub(subTitle));
        }
    }

    public RoadTitle(String title, String[] subTitles, boolean isSelected, int lotteryId) {
        this.title = title;
        subList = new ArrayList<>();
        this.isSelected = isSelected;

        String key = title + RoadFactory.ROAD_POSITION_PREFIX_SUB + lotteryId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        for (int i = 0; i < subTitles.length; i++) {
            subList.add(new RoadSub(subTitles[i], position == i ? true : false));
        }
    }

    public RoadTitle(String title, String[] subTitles, Integer[][] arr1_ids, boolean isSelected, int lotteryId) {
        this.title = title;
        subList = new ArrayList<>();
        this.isSelected = isSelected;

        String key = title + RoadFactory.ROAD_POSITION_PREFIX_SUB + lotteryId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        for (int i = 0; i < subTitles.length; i++) {
            subList.add(new RoadSub(subTitles[i], position == i ? true : false, arr1_ids[i]));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RoadSub> getSubList() {
        return subList;
    }

    public void setSubList(List<RoadSub> subList) {
        this.subList = subList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(this.subList);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected RoadTitle(Parcel in) {
        this.title = in.readString();
        this.subList = in.createTypedArrayList(RoadSub.CREATOR);
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<RoadTitle> CREATOR = new Creator<RoadTitle>() {
        @Override
        public RoadTitle createFromParcel(Parcel source) {
            return new RoadTitle(source);
        }

        @Override
        public RoadTitle[] newArray(int size) {
            return new RoadTitle[size];
        }
    };
}
