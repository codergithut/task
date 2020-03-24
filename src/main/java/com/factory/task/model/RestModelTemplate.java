package com.factory.task.model;

import com.factory.task.config.ResponseCodeEnum;

import java.util.Map;

import static com.factory.task.config.ResponseCodeEnum.USER_INFO_ERROR;

/**
 * Created by tianjian on 2020/1/13.
 */
public class RestModelTemplate<T> {

    private String code;

    private T data;

    private String message;

    public RestModelTemplate(String code, String desc) {
        this.code = code;
        this.message = desc;
    }

    public RestModelTemplate() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestModelTemplate Success(T t) {
        this.data = t;
        this.code = ResponseCodeEnum.OK.getCode();
        this.message = ResponseCodeEnum.OK.getDesc();
        return this;
    }

    public RestModelTemplate Fail(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public static RestModelTemplate FailUserInfoCheck() {
        return new RestModelTemplate(USER_INFO_ERROR.getCode(), USER_INFO_ERROR.getDesc());
    }

    public static RestModelTemplate OK() {
        return new RestModelTemplate().Success(true);
    }
}
