package com.factory.task.data.task;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
@Data
public class TaskInsData {

    /**
     * 业务Code
     */
    @Id
    private String taskInsCode;

    /**
     * 任务模版编码
     */
    private String taskTplCode;

    /**
     * 依赖任务的code
     */
    private String dependTaskTplCode;

    /**
     * 工作状态
     */
    private String taskStatus;

    /**
     * 工作流编号
     */
    private String jobCode;

    /**
     * 下一个任务编号
     */
    private String nextTaskTplCode;

    public String getTaskInsCode() {
        return taskInsCode;
    }

    public void setTaskInsCode(String taskInsCode) {
        this.taskInsCode = taskInsCode;
    }

    public String getTaskTplCode() {
        return taskTplCode;
    }

    public void setTaskTplCode(String taskTplCode) {
        this.taskTplCode = taskTplCode;
    }

    public String getDependTaskTplCode() {
        return dependTaskTplCode;
    }

    public void setDependTaskTplCode(String dependTaskTplCode) {
        this.dependTaskTplCode = dependTaskTplCode;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getNextTaskTplCode() {
        return nextTaskTplCode;
    }

    public void setNextTaskTplCode(String nextTaskTplCode) {
        this.nextTaskTplCode = nextTaskTplCode;
    }
}
