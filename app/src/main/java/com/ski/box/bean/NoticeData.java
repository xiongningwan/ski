package com.ski.box.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tom on 2020/11/6.
 */
public class NoticeData {
    /**
     * pageSize : 10
     * total : 12
     * currentPage : 1
     * list : [{"id":12,"noticeTitle":"king10","noticeContent":"<p>绯闻分我家我就绯闻分加我叫我if就我家分我尽量做什么v什么洛克菲勒开始v肌肤哦我家分<\/p>","updateAt":"2020-11-06 16:06:49"},{"id":11,"noticeTitle":"king9","noticeContent":"<p>微风微风将哦 违法来看我理解礼物礼物礼物我就我就我理解我理解礼物卡礼物<\/p>","updateAt":"2020-11-06 16:06:35"},{"id":10,"noticeTitle":"king8","noticeContent":"<p>绯闻玩法叫我发觉我i 为我服务来我家来我家礼物我就来我家我理解为了减肥为了减肥我就我理解来我家分；邻居；lkavs.nvl；会计法；肌肤 <\/p>","updateAt":"2020-11-06 16:06:23"},{"id":9,"noticeTitle":"king7","noticeContent":"<p>wjfwjflkjfj绯闻金额哦if就无法<\/p>","updateAt":"2020-11-06 16:06:11"},{"id":8,"noticeTitle":"king6","noticeContent":"<p>king3king3king3king3king3king3<\/p>","updateAt":"2020-11-06 16:06:00"},{"id":7,"noticeTitle":"king5","noticeContent":"<p>wefwefwf<\/p>","updateAt":"2020-11-06 16:05:51"},{"id":6,"noticeTitle":"king4","noticeContent":"<p>fwefwfwfe<\/p>","updateAt":"2020-11-06 16:05:44"},{"id":5,"noticeTitle":"king3","noticeContent":"<p>fwefwefwf<\/p>","updateAt":"2020-11-06 16:05:36"},{"id":4,"noticeTitle":"wefwf","noticeContent":"<p>fwefw<\/p>","updateAt":"2020-11-06 16:05:23"},{"id":3,"noticeTitle":"fwefwf","noticeContent":"<p>wefwefw<\/p>","updateAt":"2020-11-06 16:05:15"}]
     * totalPage : 2
     */

    private int pageSize;
    private int total;
    private int currentPage;
    private int totalPage;
    private List<ListBean> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * id : 12
         * noticeTitle : king10
         * noticeContent : <p>绯闻分我家我就绯闻分加我叫我if就我家分我尽量做什么v什么洛克菲勒开始v肌肤哦我家分</p>
         * updateAt : 2020-11-06 16:06:49
         */

        private int id;
        private String noticeTitle;
        private String noticeContent;
        private String updateAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getNoticeContent() {
            return noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.noticeTitle);
            dest.writeString(this.noticeContent);
            dest.writeString(this.updateAt);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readInt();
            this.noticeTitle = in.readString();
            this.noticeContent = in.readString();
            this.updateAt = in.readString();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }
}
