package com.factory.task.model.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tianjian on 2020/1/11.
 */
@ApiModel
public class TaskInsView {

    /**
     * 任务编码
     */
    @ApiModelProperty("任务编码")
    private String taskInsCode;

    /**
     * 依赖任务的code
     */
    @ApiModelProperty("依赖任务的code")
    private String dependTaskTplCode;

    /**
     * 工作状态
     */
    @ApiModelProperty("工作状态")
    private String taskStatus;

    /**
     * 工作流编号
     */
    @ApiModelProperty("工作流编号")
    private String jobCode;

    /**
     * 下一个任务编号
     */
    @ApiModelProperty("下一个任务编号")
    private String nextTaskTplCode;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String taskName;

    /**
     * 实例数据
     */
    @ApiModelProperty("实例数据")
    private String taskData;

    private Boolean isStar;

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

    public String getTaskData() {
        return taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getStar() {
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }
}
