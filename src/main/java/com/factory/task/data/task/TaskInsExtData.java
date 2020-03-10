package com.factory.task.data.task;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
public class TaskInsExtData {

    /**
     * 编号
     */
    @Id
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
    private Date extDate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskInsExtDesc() {
        return taskInsExtDesc;
    }

    public void setTaskInsExtDesc(String taskInsExtDesc) {
        this.taskInsExtDesc = taskInsExtDesc;
    }

    public Date getExtDate() {
        return extDate;
    }

    public void setExtDate(Date extDate) {
        this.extDate = extDate;
    }
}
