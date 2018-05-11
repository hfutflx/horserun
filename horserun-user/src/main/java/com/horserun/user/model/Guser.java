package com.horserun.user.model;

/**
 * Created by lxfang on 2018/3/16.
 */
public class Guser {

    private String id;

    private String phoNum;

    private String loginName;

    private String password;

    private int creditLevel;

    private  int userType;

    private  String pictureUrl;

    private String lastupdatetime;

    public Guser(String id, String phoNum, String loginName, String password, int creditLevel, int userType, String pictureUrl, String lastupdatetime) {
        this.id = id;
        this.phoNum = phoNum;
        this.loginName = loginName;
        this.password = password;
        this.creditLevel = creditLevel;
        this.userType = userType;
        this.pictureUrl = pictureUrl;
        this.lastupdatetime = lastupdatetime;
    }

    public Guser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoNum() {
        return phoNum;
    }

    public void setPhoNum(String phoNum) {
        this.phoNum = phoNum;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(int creditLevel) {
        this.creditLevel = creditLevel;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
