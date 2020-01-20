package com.factory.task.model.task;

import java.util.Date;

/**
 * Created by tianjian on 2020/1/13.
 */
public class TaskInsExtView {

    /**
     * 编号
     */
    private String taskInsExtCode;

    /**
     * 标记事件编号
     */
    private String taskInsCode;

    /**
     * 类型
     */
    private String type;

    /**
     * 对任务对额外描述
     */
    private String taskInsExtDesc;

    /**
     * 标记时间
     */
    private Date date;


    public String getTaskInsExtCode() {
        return taskInsExtCode;
    }

    public void setTaskInsExtCode(String taskInsExtCode) {
        this.taskInsExtCode = taskInsExtCode;
    }

    public String getTaskInsCode() {
        return taskInsCode;
    }

    public void setTaskInsCode(String taskInsCode) {
        this.taskInsCode = taskInsCode;
    }

    public String getTaskInsExtDesc() {
        return taskInsExtDesc;
    }

    public void setTaskInsExtDesc(String taskInsExtDesc) {
        this.taskInsExtDesc = taskInsExtDesc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
