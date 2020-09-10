package com.ski.box.bean;

/**
 * Created by tom on 2020/5/11.
 */
public class SystemConfig {
    /**
     * service_date_url : https://date.fuzikpke.com
     * mqtt_url : wss://wss.fuzikpke.com/mqtt
     * play_desc_url : https://json.bobcp.vip
     * static_resouce_url : https://json.bobcp.vip
     */

    private String service_date_url; //系统时间
    private String mqtt_url; // mqtt
    private String play_desc_url; // 彩种说明
    private String static_resouce_url; // 静态资源

    public String getService_date_url() {
        return service_date_url;
    }

    public void setService_date_url(String service_date_url) {
        this.service_date_url = service_date_url;
    }

    public String getMqtt_url() {
        return mqtt_url;
    }

    public void setMqtt_url(String mqtt_url) {
        this.mqtt_url = mqtt_url;
    }

    public String getPlay_desc_url() {
        return play_desc_url;
    }

    public void setPlay_desc_url(String play_desc_url) {
        this.play_desc_url = play_desc_url;
    }

    public String getStatic_resouce_url() {
        return static_resouce_url;
    }

    public void setStatic_resouce_url(String static_resouce_url) {
        this.static_resouce_url = static_resouce_url;
    }
}
