package com.ski.box.bean.record;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 资金记录列表
 */
public class RecordMoney {
    private int total;
    private int pages;
    private  boolean hasPreviousPage;
    private  boolean hasNextPage;

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }



    public static class ListBean {
        private String createdAt;
        private String id;
        private String ticketName;
        private int tradeType;
        private String balanceBefore;
        private String balanceAfter;
        private double amount;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public String getBalanceBefore() {
            return balanceBefore;
        }

        public void setBalanceBefore(String balanceBefore) {
            this.balanceBefore = balanceBefore;
        }

        public String getBalanceAfter() {
            return balanceAfter;
        }

        public void setBalanceAfter(String balanceAfter) {
            this.balanceAfter = balanceAfter;
        }

        public double getAmount() {
            /*dd*/
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
