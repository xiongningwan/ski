package com.ski.box.bean.group;

import java.util.List;

/**
 * Created by tom on 2020/10/29.
 */
public class InviteData {
    /**
     * pageSize : 10
     * total : 1
     * currentPage : 1
     * list : [{"code":"5a3d6ed88e0c4bac90942b5e7c5c126c","rebate":1984,"count":0,"word":"啊啊啊","createAt":"2020-10-29 22:23:58","expireAt":"2030-10-27 22:23:58","status":1,"inviteUrl":"https://web.k5615.com/reg/5a3d6ed88e0c4bac90942b5e7c5c126c"}]
     * totalPage : 1
     */

    private int pageSize;
    private int total;
    private int currentPage;
    private int totalPage;
    private List<InviteUrl> list;

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

    public List<InviteUrl> getList() {
        return list;
    }

    public void setList(List<InviteUrl> list) {
        this.list = list;
    }

}
