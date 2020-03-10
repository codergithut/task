package com.factory.task.data.user;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
public class UserInfoData implements Serializable {
    /**
     * 用户编码
     */
    @Id
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 电话号码
     */
    private String telPhoneNum;

    /**
     * 部门id
     */
    private String departmentCode;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelPhoneNum() {
        return telPhoneNum;
    }

    public void setTelPhoneNum(String telPhoneNum) {
        this.telPhoneNum = telPhoneNum;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
