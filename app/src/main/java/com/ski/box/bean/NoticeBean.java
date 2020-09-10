package com.ski.box.bean;



public class NoticeBean  {
    private String title;
    private String date;
    private String type;
    private String content;

    public NoticeBean(String title, String date, String type, String content) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
