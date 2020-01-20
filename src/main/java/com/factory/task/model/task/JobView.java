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

}
