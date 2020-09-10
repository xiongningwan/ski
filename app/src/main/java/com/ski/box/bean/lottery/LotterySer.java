package com.ski.box.bean.lottery;

import java.util.List;

public class LotterySer {
    private int id;
    private String name;
    private List<LotteryBean> list;
    private boolean isSelected;

    public LotterySer() {
    }

    public LotterySer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LotteryBean> getList() {
        return list;
    }

    public void setList(List<LotteryBean> list) {
        this.list = list;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
