package com.factory.task.model.weixin;

/**
 * Created by tianjian on 2020/4/19.
 */
public class WeiXinToken {
    /**
     * token
     */
    private String access_token;

    /**
     * 过期时间
     */
    private Long expires_in;

    /**
     * 错误码
     */
    private Long errcode;

    /**
     * 错误消息
     */
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
