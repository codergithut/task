package com.factory.task.data.weixin;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/4/13.
 */
@Entity
public class WeiXinUserLinkSysUser {

    @Id
    private String id;

    private String unionId;

    private String userCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
