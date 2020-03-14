package com.factory.task.model.task;

import java.util.Date;

/**
 * Created by tianjian on 2020/1/11.
 */
public class JobView {

    /**
     * 工作编码
     */
    private String jobCode;

    /**
     * 工作名称
     */
    private String jobName;

    /**
     * 工作描述
     */
    private String jobDes;

    /**
     * 起始工作流编码
     */
    private String startTaskTplCode;

    /**
     * 是否结束
     */
    private String isFinished;

    /**
     * 最晚完成时间
     */
    private Date deadLine;

    /**
     * 当前节点
     */
    private String taskInsCode;

    /**
     * 当前节点名称
     */
    private String taskName;


    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDes() {
        return jobDes;
    }

    public void setJobDes(String jobDes) {
        this.jobDes = jobDes;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getStartTaskTplCode() {
        return startTaskTplCode;
    }

    public void setStartTaskTplCode(String startTaskTplCode) {
        this.startTaskTplCode = startTaskTplCode;
    }

    public String getTaskInsCode() {
        return taskInsCode;
    }

    public void setTaskInsCode(String taskInsCode) {
        this.taskInsCode = taskInsCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
