package com.ski.box.bean.group;

/**
 * Created by tom on 2020/10/29.
 */
public class InviteUrl {
    /**
     * code : 5a3d6ed88e0c4bac90942b5e7c5c126c
     * rebate : 1984
     * count : 0
     * word : 啊啊啊
     * createAt : 2020-10-29 22:23:58
     * expireAt : 2030-10-27 22:23:58
     * status : 1
     * inviteUrl : https://web.k5615.com/reg/5a3d6ed88e0c4bac90942b5e7c5c126c
     */

    private String code;
    private int rebate;
    private int count;
    private String word;
    private String createAt;
    private String expireAt;
    private int status;
    private String inviteUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }
}
