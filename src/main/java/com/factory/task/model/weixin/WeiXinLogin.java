package com.factory.task.model.weixin;

import io.swagger.annotations.ApiModel;

/**
 * Created by tianjian on 2020/4/12.
 */
@ApiModel
public class WeiXinLogin {

    private String session_key;

    private String openid;

    private String unionId;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
