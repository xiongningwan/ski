package com.ski.box.bean.road;

import android.os.Parcel;
import android.os.Parcelable;

import com.ski.box.bean.lottery.LotteryConstant;


public class RoadBean implements Parcelable {
    private String name;
    private int bp; // banker:1 player:2 tie:3 wei 4
    private int pair; // 庄对:1 闲对:2 双对:3
    private int tieCount; // 和个数
    private int line; // 行数 未用上
    private boolean isFuture;//问路

    public RoadBean() {
    }

    public RoadBean(int bp, int pair) {
        this.bp = bp;
        this.pair = pair;
    }

    /**
     * 配置数据
     *
     * @param serId    彩系ID
     * @param arr      开奖号码
     * @param total    总数
     * @param titleKey 条目
     */
    public RoadBean(int serId, String[] arr, int total, String titleKey) {
        Object[] attArr;
        switch (serId) {
            case LotteryConstant.SER_ID_11X5:
                if (arr.length != 5) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_11x5(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_PK10:
                if (arr.length != 10) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_pk10(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_PL35:
                if (arr.length != 5) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_ssc(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_K3:
                if (arr.length != 3) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_k3(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_3D:
                if (arr.length != 3) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_3d(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_KL8:
                if (arr.length != 20) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_klc(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_LHC:
                if (arr.length != 7) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_lhc(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
            case LotteryConstant.SER_ID_F1_JJS:
                if (arr.length != 10) {
                    return;
                }
                attArr = RoadAttUtil.road_arr_f1_jjs(arr, total, titleKey);
                name = (String) attArr[0];
                bp = (int) attArr[1];
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public int getTieCount() {
        return tieCount;
    }

    public void setTieCount(int tieCount) {
        this.tieCount = tieCount;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public boolean isFuture() {
        return isFuture;
    }

    public void setFuture(boolean future) {
        isFuture = future;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "bp=" + bp +
                ", pair=" + pair +
                ", tieCount=" + tieCount +
                ", line=" + line +
                '}';
    }

    public interface Con {
        int BANKER = 1; // 红色
        int PLAYER = 2; // 蓝色
        int TIE = 3; // 绿色
        int WEI = 4;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.bp);
        dest.writeInt(this.pair);
        dest.writeInt(this.tieCount);
        dest.writeInt(this.line);
    }

    protected RoadBean(Parcel in) {
        this.name = in.readString();
        this.bp = in.readInt();
        this.pair = in.readInt();
        this.tieCount = in.readInt();
        this.line = in.readInt();
    }

    public static final Creator<RoadBean> CREATOR = new Creator<RoadBean>() {
        @Override
        public RoadBean createFromParcel(Parcel source) {
            return new RoadBean(source);
        }

        @Override
        public RoadBean[] newArray(int size) {
            return new RoadBean[size];
        }
    };
}
