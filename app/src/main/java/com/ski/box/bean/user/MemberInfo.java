package com.ski.box.bean.user;

/**
 * Created by tom on 2020/10/23.
 */
public class MemberInfo {
    /**
     * account : water14
     * alias : water14的昵称
     * profile :
     * vip : 0
     * rebate : 1985
     * tester : 0
     * loginIp : 172.31.14.172
     * loginLocation : 0  内网IP
     * loginTime : 2020-10-23T00:04:53
     * loginPwdTime : 2020-10-19T20:07:49
     * fundPwdTime : null
     * havefundPwd : 0
     * mobile :
     * email :
     */

    private String account;
    private String alias;
    private String profile;
    private int vip;
    private int rebate;
    private int tester;
    private String loginIp;
    private String loginLocation;
    private String loginTime;
    private String loginPwdTime;
    private Object fundPwdTime;
    private int havefundPwd;
    private String mobile;
    private String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getTester() {
        return tester;
    }

    public void setTester(int tester) {
        this.tester = tester;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginPwdTime() {
        return loginPwdTime;
    }

    public void setLoginPwdTime(String loginPwdTime) {
        this.loginPwdTime = loginPwdTime;
    }

    public Object getFundPwdTime() {
        return fundPwdTime;
    }

    public void setFundPwdTime(Object fundPwdTime) {
        this.fundPwdTime = fundPwdTime;
    }

    public int getHavefundPwd() {
        return havefundPwd;
    }

    public void setHavefundPwd(int havefundPwd) {
        this.havefundPwd = havefundPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
