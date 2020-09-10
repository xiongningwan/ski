package com.ski.box.bean;

import java.util.List;

/**
 * Created by tom on 2020/8/6.
 */
public class LotteryDataBean {
    /**
     * code : 0
     * msg : query ticket type success
     * sign : null
     * data : [{"id":1,"name":"时时彩","list":[{"ticketId":1,"ticketName":"重庆欢乐生肖","path":null,"groupMode":0,"code":"SSC"},{"ticketId":3,"ticketName":"新疆时时彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":8,"ticketName":"天津时时彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":9,"ticketName":"黑龙江时时彩","path":null,"groupMode":1,"code":"SSC"},{"ticketId":45,"ticketName":"幸运分分彩","path":null,"groupMode":1,"code":"SSC"},{"ticketId":55,"ticketName":"幸运5分彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":56,"ticketName":"河内5分彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":57,"ticketName":"腾讯分分彩","path":null,"groupMode":0,"code":"SSC"}]},{"id":2,"name":"11选5","list":[{"ticketId":4,"ticketName":"广东11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":5,"ticketName":"山东11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":11,"ticketName":"江西11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":14,"ticketName":"福建11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":15,"ticketName":"广西11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":46,"ticketName":"极速11选5","path":null,"groupMode":0,"code":"SYXW"},{"ticketId":58,"ticketName":"幸运11选5","path":null,"groupMode":0,"code":"SYXW"}]},{"id":3,"name":"六合彩","list":[{"ticketId":27,"ticketName":"香港六合彩","path":null,"groupMode":0,"code":"LHC"},{"ticketId":59,"ticketName":"六合5分彩","path":null,"groupMode":0,"code":"LHC"}]},{"id":4,"name":"3D","list":[{"ticketId":6,"ticketName":"福彩3D","path":null,"groupMode":0,"code":"SD"},{"ticketId":16,"ticketName":"幸运3D","path":null,"groupMode":0,"code":"SD"},{"ticketId":17,"ticketName":"极速3D","path":null,"groupMode":0,"code":"SD"}]},{"id":5,"name":"快乐8","list":[{"ticketId":26,"ticketName":"北京快乐8","path":null,"groupMode":0,"code":"KLC"},{"ticketId":47,"ticketName":"极速快乐8","path":null,"groupMode":0,"code":"KLC"}]},{"id":6,"name":"PK10","list":[{"ticketId":67,"ticketName":"极速赛车","path":null,"groupMode":0,"code":"PK10"},{"ticketId":68,"ticketName":"幸运飞艇","path":"http://172.18.3.119:8002/f3a68235-6f03-4849-8237-e459783c65d4west.png","groupMode":0,"code":"PK10"},{"ticketId":69,"ticketName":"马耳他飞艇","path":null,"groupMode":0,"code":"PK10"},{"ticketId":18,"ticketName":"北京PK10","path":null,"groupMode":0,"code":"PK10"},{"ticketId":48,"ticketName":"极速飞艇","path":null,"groupMode":0,"code":"PK10"},{"ticketId":54,"ticketName":"欢乐飞艇","path":null,"groupMode":0,"code":"PK10"}]},{"id":7,"name":"快三","list":[{"ticketId":20,"ticketName":"吉林快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":89,"ticketName":"幸运快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":41,"ticketName":"江苏快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":42,"ticketName":"安徽快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":43,"ticketName":"广西快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":44,"ticketName":"福建快三","path":null,"groupMode":0,"code":"K3"},{"ticketId":50,"ticketName":"极速快三","path":null,"groupMode":0,"code":"K3"}]},{"id":8,"name":"P3P5","list":[{"ticketId":40,"ticketName":"排列3/5","path":null,"groupMode":0,"code":"P3P5"}]}]
     */

    private int code;
    private String msg;
    private Object sign;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 时时彩
         * list : [{"ticketId":1,"ticketName":"重庆欢乐生肖","path":null,"groupMode":0,"code":"SSC"},{"ticketId":3,"ticketName":"新疆时时彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":8,"ticketName":"天津时时彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":9,"ticketName":"黑龙江时时彩","path":null,"groupMode":1,"code":"SSC"},{"ticketId":45,"ticketName":"幸运分分彩","path":null,"groupMode":1,"code":"SSC"},{"ticketId":55,"ticketName":"幸运5分彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":56,"ticketName":"河内5分彩","path":null,"groupMode":0,"code":"SSC"},{"ticketId":57,"ticketName":"腾讯分分彩","path":null,"groupMode":0,"code":"SSC"}]
         */

        private int id;
        private String name;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ticketId : 1
             * ticketName : 重庆欢乐生肖
             * path : null
             * groupMode : 0
             * code : SSC
             */

            private int ticketId;
            private String ticketName;
            private Object path;
            private int groupMode;
            private String code;

            public int getTicketId() {
                return ticketId;
            }

            public void setTicketId(int ticketId) {
                this.ticketId = ticketId;
            }

            public String getTicketName() {
                return ticketName;
            }

            public void setTicketName(String ticketName) {
                this.ticketName = ticketName;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
