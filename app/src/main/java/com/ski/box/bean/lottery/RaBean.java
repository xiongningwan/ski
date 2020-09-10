package com.ski.box.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2020/6/20.
 */
public class RaBean implements Parcelable {
    /**
     * level : 1
     * rate : 0.9775
     */

    private int level;
    private String rate = "0";
    private String bonus = "0";

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.level);
        dest.writeString(this.rate);
        dest.writeString(this.bonus);
    }

    public RaBean() {
    }

    protected RaBean(Parcel in) {
        this.level = in.readInt();
        this.rate = in.readString();
        this.bonus = in.readString();
    }

    public static final Creator<RaBean> CREATOR = new Creator<RaBean>() {
        @Override
        public RaBean createFromParcel(Parcel source) {
            return new RaBean(source);
        }

        @Override
        public RaBean[] newArray(int size) {
            return new RaBean[size];
        }
    };
}
