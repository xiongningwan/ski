package com.ski.box.bean;

import java.util.List;

/**
 * 提醒配置信息
 */
public class NoticeConfigInfoEntity {
    private Number seriesId;
    private String seriesName;
    private String code;
    private List<TicketData> ticketList;
    private List<PlayData> playList;
    private boolean checked;

    public Number getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Number seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TicketData> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketData> ticketList) {
        this.ticketList = ticketList;
    }

    public List<PlayData> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayData> playList) {
        this.playList = playList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public class TicketData {
        /**
         * ticketId : 1
         * ticketName : 重庆时时彩
         */

        private int ticketId;
        private String ticketName;
        private String code;
        private boolean checked;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

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
    }

    public class PlayData {
        private int playId;
        private String playName;
        private String playCode;
        private boolean checked;

        public int getPlayId() {
            return playId;
        }

        public void setPlayId(int playId) {
            this.playId = playId;
        }

        public String getPlayName() {
            return playName;
        }

        public void setPlayName(String playName) {
            this.playName = playName;
        }

        public String getPlayCode() {
            return playCode;
        }

        public void setPlayCode(String playCode) {
            this.playCode = playCode;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
