package com.factory.task.data.user.relation;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
public class UserAndRoleRelationData {

    @Id
    private String code;

    private String userCode;

    private String roleCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
