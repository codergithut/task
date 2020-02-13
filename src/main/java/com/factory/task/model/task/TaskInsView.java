package com.factory.task.model.task;

/**
 * Created by tianjian on 2020/1/11.
 */
public class TaskInsView {

    /**
     * 任务编码
     */
    private String taskInsCode;

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

    /**
     * 实例数据
     */
    private String taskInsDescInfo;

    public String getTaskInsCode() {
        return taskInsCode;
    }

    public void setTaskInsCode(String taskInsCode) {
        this.taskInsCode = taskInsCode;
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

    public String getDependTaskTplCode() {
        return dependTaskTplCode;
    }

    public void setDependTaskTplCode(String dependTaskTplCode) {
        this.dependTaskTplCode = dependTaskTplCode;
    }

    public String getNextTaskTplCode() {
        return nextTaskTplCode;
    }

    public void setNextTaskTplCode(String nextTaskTplCode) {
        this.nextTaskTplCode = nextTaskTplCode;
    }

    public String getTaskInsDescInfo() {
        return taskInsDescInfo;
    }

    public void setTaskInsDescInfo(String taskInsDescInfo) {
        this.taskInsDescInfo = taskInsDescInfo;
    }
}
