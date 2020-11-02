package com.ski.box.bean.user;

/**
 * Created by tom on 2020/11/3.
 */
public class HeadBean {
    private String profile;
    private int res;
    private boolean selected;

    public HeadBean() {
    }

    public HeadBean(String profile) {
        this.profile = profile;
    }

    public HeadBean(String profile, int res) {
        this.profile = profile;
        this.res = res;
    }

    public HeadBean(String profile, boolean selected) {
        this.profile = profile;
        this.selected = selected;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
