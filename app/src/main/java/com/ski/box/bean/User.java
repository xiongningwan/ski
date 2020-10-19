package com.ski.box.bean;

/**
 * Created by tom on 2020/10/19.
 */
public class User {
    /**
     * memberAccount : water14
     * memberAlias : water14的昵称
     * memberProfile :
     * authorization : 8eb847b60b13442c812e3b65e94dd039
     * token : 6424fb0f00a340e09ffa7c130e0d97d81603109585906
     */

    private String memberAccount;
    private String memberAlias;
    private String memberProfile;
    private String authorization;
    private String token;

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberAlias() {
        return memberAlias;
    }

    public void setMemberAlias(String memberAlias) {
        this.memberAlias = memberAlias;
    }

    public String getMemberProfile() {
        return memberProfile;
    }

    public void setMemberProfile(String memberProfile) {
        this.memberProfile = memberProfile;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
