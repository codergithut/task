package com.factory.task.config;

/**
 * Created by tianjian on 2020/3/19.
 */
public enum ResponseCodeEnum {

    USER_INFO_ERROR("1000", "用户验证数据错误"),
    TEMPLATE_NULL_ERROR("1001", "模版为空"),
    OK("0000", "成功"),
    DEPEND_TASK_ERROR("1002", "依赖任务未完成");

    private String code;

    private String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
