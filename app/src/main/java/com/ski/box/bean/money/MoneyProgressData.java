package com.ski.box.bean.money;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tom on 2020/10/31.
 */
public class MoneyProgressData {
    /**
     * pageSize : 10
     * total : 4
     * currentPage : 1
     * list : [{"orderId":"1322464957912059926","createAt":"2020-10-31 17:06:31","dwType":1,"amt":"0","status":1,"remark":"","detail":"充值通道在线支付","updateAt":"2020-10-31 17:06:31"},{"orderId":"1322484617319550994","createAt":"2020-10-31 18:24:38","dwType":1,"amt":"0","status":1,"remark":"","detail":"充值通道网银支付","updateAt":"2020-10-31 18:24:38"},{"orderId":"1322485251158577192","createAt":"2020-10-31 18:27:09","dwType":1,"amt":"0","status":1,"remark":"","detail":"充值通道网银支付","updateAt":"2020-10-31 18:27:09"},{"orderId":"1322497045965049930","createAt":"2020-10-31 19:14:01","dwType":1,"amt":"0","status":1,"remark":"","detail":"充值通道网银支付","updateAt":"2020-10-31 19:14:01"}]
     * totalPage : 1
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

    public static class ListBean {
        /**
         * orderId : 1322464957912059926
         * createAt : 2020-10-31 17:06:31
         * dwType : 1
         * amt : 0
         * status : 1
         * remark :
         * detail : 充值通道在线支付
         * updateAt : 2020-10-31 17:06:31
         */

        private String orderId;
        private String createAt;
        private int dwType;
        private BigDecimal amt;
        private int status;
        private String remark;
        private String detail;
        private String updateAt;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public int getDwType() {
            return dwType;
        }

        public void setDwType(int dwType) {
            this.dwType = dwType;
        }

        public BigDecimal getAmt() {
            return amt;
        }

        public void setAmt(BigDecimal amt) {
            this.amt = amt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }
    }
}
