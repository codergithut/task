package com.factory.task.model.weixin.message;

/**
 * Created by tianjian on 2020/4/19.
 */
public class SubMessageBack {

    private Long errcode;

    private String errmsg;

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
