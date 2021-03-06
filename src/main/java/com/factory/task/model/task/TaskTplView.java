package com.factory.task.model.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2020/1/11.
 */
@ApiModel
public class TaskTplView {

    /**
     * 任务编码
     */
    @ApiModelProperty("任务编码")
    private String taskCode;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String taskName;

    /**
     * 任务描述
     */
    @ApiModelProperty("任务描述")
    private String taskDes;

    /**
     * 任务额外参数
     */
    @ApiModelProperty("任务额外参数")
    private List<TaskTplDescMetaView> taskTplDescMetaViews;

    /**
     * 任务类型
     */
    @ApiModelProperty("任务类型")
    private String taskType;

    /**
     * 任务依赖
     */
    @ApiModelProperty("任务依赖")
    private List<String> dependTaskCodes;

    /**
     * 发布人用户id
     */
    @ApiModelProperty("发布人用户id")
    private String publisherUserId;

    /**
     * 接受人id
     */
    @ApiModelProperty("接受人id")
    private String receiverUserId;

    /**
     * 子节点任务
     */
    @ApiModelProperty("子节点任务")
    private String nextTaskCode;

    /**
     * 是否是根节点
     */
    @ApiModelProperty("是否是根节点")
    private Boolean isParent;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createDate;


    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getPublisherUserId() {
        return publisherUserId;
    }

    public void setPublisherUserId(String publisherUserId) {
        this.publisherUserId = publisherUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public List<TaskTplDescMetaView> getTaskTplDescMetaViews() {
        return taskTplDescMetaViews;
    }

    public void setTaskTplDescMetaViews(List<TaskTplDescMetaView> taskTplDescMetaViews) {
        this.taskTplDescMetaViews = taskTplDescMetaViews;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public String getNextTaskCode() {
        return nextTaskCode;
    }

    public void setNextTaskCode(String nextTaskCode) {
        this.nextTaskCode = nextTaskCode;
    }

    public List<String> getDependTaskCodes() {
        return dependTaskCodes;
    }

    public void setDependTaskCodes(List<String> dependTaskCodes) {
        this.dependTaskCodes = dependTaskCodes;
    }
}
