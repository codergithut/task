package com.factory.task.data.task;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
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
     * 任务数据
     */
    private String taskData;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 工作流编号
     */
    private String jobCode;

    /**
     * 下一个任务编号
     */
    private String nextTaskTplCode;

    /**
     * 当前处理人
     */
    private String handleUserCode;

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

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }

    public String getTaskData() {
        return taskData;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getHandleUserCode() {
        return handleUserCode;
    }

    public void setHandleUserCode(String handleUserCode) {
        this.handleUserCode = handleUserCode;
    }




}
