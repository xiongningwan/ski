package com.ski.box.bean;

/**
 * Created by tom on 2020/10/22.
 * 个人中心
 * tab 数据实体
 *
 *
 */
public class PTabBean {
    private int id;
    private int icon;
    private String name;

    public PTabBean() {
    }

    public PTabBean(int id, int icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
