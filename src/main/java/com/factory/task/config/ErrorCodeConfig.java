package com.factory.task.config;

/**
 * Created by tianjian on 2020/3/19.
 */
public enum  ErrorCodeConfig {

    USER_INFO_ERROR("100001", "用户名密码错误"),TEMPLATE_NULL_ERROR("100002", "模版为空");
    private String errorCode;

    private String errorDesc;

    ErrorCodeConfig(String errorCode, String errorDesc){
         this.errorCode = errorCode;
         this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
