package com.ski.box.bean.group;

import java.util.List;

/**
 * Created by tom on 2020/10/30.
 */
public class GroupMemberData {
    private int pageSize;
    private int total;
    private int currentPage;
    private int totalPage;
    private List<GroupMember> list;

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

    public List<GroupMember> getList() {
        return list;
    }

    public void setList(List<GroupMember> list) {
        this.list = list;
    }
}
