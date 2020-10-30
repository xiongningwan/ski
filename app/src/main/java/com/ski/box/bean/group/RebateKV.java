package com.ski.box.bean.group;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2020/10/29.
 */
public class RebateKV implements Parcelable {
    /**
     * baseRebate : 1900
     * maxRebate : 1985
     */

    private int rebate;
    private String percent;

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rebate);
        dest.writeString(this.percent);
    }

    public RebateKV() {
    }

    protected RebateKV(Parcel in) {
        this.rebate = in.readInt();
        this.percent = in.readString();
    }

    public static final Parcelable.Creator<RebateKV> CREATOR = new Parcelable.Creator<RebateKV>() {
        @Override
        public RebateKV createFromParcel(Parcel source) {
            return new RebateKV(source);
        }

        @Override
        public RebateKV[] newArray(int size) {
            return new RebateKV[size];
        }
    };
}
