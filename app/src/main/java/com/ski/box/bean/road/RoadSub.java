package com.ski.box.bean.road;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RoadSub implements Parcelable {
    private String subTitle;
    private List<RoadBean> AttList = new ArrayList<>(); // 属性
    private List<RoadBean> list1;
    private List<RoadBean> list2;
    private List<RoadBean> list3;
    private List<RoadBean> list4;
    private boolean isSelected;

    private Integer[] roadPlayIds;

    public RoadSub() {
    }

    public RoadSub(String subTitle) {
        this.subTitle = subTitle;
    }

    public RoadSub(String subTitle, boolean isSelected) {
        this.subTitle = subTitle;
        this.isSelected = isSelected;
    }

    public RoadSub(String subTitle, boolean isSelected, Integer[] roadPlayIds) {
        this.subTitle = subTitle;
        this.isSelected = isSelected;
        this.roadPlayIds = roadPlayIds;
    }

    public Integer[] getRoadPlayIds() {
        return roadPlayIds;
    }

    public void setRoadPlayIds(Integer[] roadPlayIds) {
        this.roadPlayIds = roadPlayIds;
    }

    public List<RoadBean> getAttList() {
        return AttList;
    }

    public void setAttList(List<RoadBean> attList) {
        AttList = attList;
    }


    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<RoadBean> getList1() {
        return list1;
    }

    public void setList1(List<RoadBean> list1) {
        this.list1 = list1;
    }

    public List<RoadBean> getList2() {
        return list2;
    }

    public void setList2(List<RoadBean> list2) {
        this.list2 = list2;
    }

    public List<RoadBean> getList3() {
        return list3;
    }

    public void setList3(List<RoadBean> list3) {
        this.list3 = list3;
    }

    public List<RoadBean> getList4() {
        return list4;
    }

    public void setList4(List<RoadBean> list4) {
        this.list4 = list4;
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
        dest.writeString(this.subTitle);
        dest.writeTypedList(this.AttList);
        dest.writeTypedList(this.list1);
        dest.writeTypedList(this.list2);
        dest.writeTypedList(this.list3);
        dest.writeTypedList(this.list4);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeArray(this.roadPlayIds);
    }

    protected RoadSub(Parcel in) {
        this.subTitle = in.readString();
        this.AttList = in.createTypedArrayList(RoadBean.CREATOR);
        this.list1 = in.createTypedArrayList(RoadBean.CREATOR);
        this.list2 = in.createTypedArrayList(RoadBean.CREATOR);
        this.list3 = in.createTypedArrayList(RoadBean.CREATOR);
        this.list4 = in.createTypedArrayList(RoadBean.CREATOR);
        this.isSelected = in.readByte() != 0;
        this.roadPlayIds = (Integer[]) in.readArray(Integer[].class.getClassLoader());
    }

    public static final Creator<RoadSub> CREATOR = new Creator<RoadSub>() {
        @Override
        public RoadSub createFromParcel(Parcel source) {
            return new RoadSub(source);
        }

        @Override
        public RoadSub[] newArray(int size) {
            return new RoadSub[size];
        }
    };
}
