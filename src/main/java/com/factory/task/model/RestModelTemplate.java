package com.factory.task.model;

/**
 * Created by tianjian on 2020/1/13.
 */
public class RestModelTemplate<T> {

    private String code;

    private T data;

    private String message;

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
        this.code = "0000";
        this.message = "success";
        return this;
    }

    public RestModelTemplate Fail(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}
