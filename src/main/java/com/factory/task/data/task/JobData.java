package com.factory.task.data.task;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
public class JobData {

    /**
     * 业务编码
     */
    @Id
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
     * 是否结束
     */
    private String isFinished;

    /**
     * 发布人用户id
     */
    private String publisherUserId;

    /**
     * 使用改数据 工作启动模版
     *
     */
    private String jobTemplateCode;

    /**
     * 最晚完成时间
     */
    private Date deadLine;

    /**
     * job数据
     */
    private String jobData;

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

    public String getPublisherUserId() {
        return publisherUserId;
    }

    public void setPublisherUserId(String publisherUserId) {
        this.publisherUserId = publisherUserId;
    }

    public String getJobData() {
        return jobData;
    }

    public String getJobTemplateCode() {
        return jobTemplateCode;
    }

    public void setJobTemplateCode(String jobTemplateCode) {
        this.jobTemplateCode = jobTemplateCode;
    }

    public void setJobData(String jobData) {
        this.jobData = jobData;
    }
}
