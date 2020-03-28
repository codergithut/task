package com.factory.task.data.user.relation;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
public class RoleAndUriRelationData {

    @Id
    private String code;

    private String roleCode;

    private String uriId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }
}
