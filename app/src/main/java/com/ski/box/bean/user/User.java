package com.ski.box.bean.user;

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
    private String id;
    private String account;
    private String alias;
    private String profile;
    private String authorization;
    private String token;
    //
    private String balance = "";
    private String merchantAccout;
    private int transferType;
    //
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMerchantAccout() {
        return merchantAccout;
    }

    public void setMerchantAccout(String merchantAccout) {
        this.merchantAccout = merchantAccout;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
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
