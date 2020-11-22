package com.ski.box.bean;

/**
 * Created by tom on 2020/5/26.
 */
public class UpdateBean {
    private int type;
    private String update_url;
    private int is_force; // 是否强制更新: 1 强制 0 不强制
    private String version;
    private String introduction;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public int getIs_force() {
        return is_force;
    }

    public void setIs_force(int is_force) {
        this.is_force = is_force;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
