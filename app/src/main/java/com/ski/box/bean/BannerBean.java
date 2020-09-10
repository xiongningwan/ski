package com.ski.box.bean;

/**
 * Created by tom on 2020/8/6.
 */
public class BannerBean {
    private long id;
    private String imageUrl;
    private String pathUrl;
    private int resId;

    public BannerBean(long id, String imageUrl, String pathUrl, int resId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.pathUrl = pathUrl;
        this.resId = resId;
    }

    public BannerBean() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }
}
