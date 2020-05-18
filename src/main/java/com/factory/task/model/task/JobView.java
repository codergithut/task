package com.factory.task.model.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by tianjian on 2020/1/11.
 */
@ApiModel
public class JobView {

    /**
     * 工作编码
     */
    @ApiModelProperty("工作编码")
    private String jobCode;

    /**
     * 工作名称
     */
    @ApiModelProperty("工作名称")
    private String jobName;

    /**
     * 工作描述
     */
    @ApiModelProperty("工作描述")
    private String jobDes;


    /**
     * 使用改数据 工作启动模版
     *
     */
    @ApiModelProperty("工作启动模版")
    private String jobTemplateCode;

    /**
     * 是否结束
     */
    @ApiModelProperty("是否结束")
    private String isFinished;

    /**
     * 最晚完成时间
     */
    @ApiModelProperty("最晚完成时间")
    private Date deadLine;

    /**
     * 当前节点
     */
    @ApiModelProperty("当前节点编码")
    private String taskInsCode;

    /**
     * 当前节点名称
     */
    @ApiModelProperty("当前节点名称")
    private String taskName;

    /**
     * job数据
     */
    @ApiModelProperty("job数据")
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
