package com.horserun.user.model;

/**
 * Created by lxfang on 2018/3/16.
 */
public class RegisterUser {

    private String id;

    private String phoNum;

    private String loginName;

    private String password;

    private  int userType;

    public RegisterUser() {
    }

    public RegisterUser(String phoNum, String loginName, String password, int userType) {
        this.phoNum = phoNum;
        this.loginName = loginName;
        this.password = password;
        this.userType = userType;

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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
