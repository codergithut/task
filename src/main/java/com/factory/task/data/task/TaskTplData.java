package com.factory.task.data.task;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
public class TaskTplData {

    /**
     * 任务编码
     */
    @Id
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务描述
     */
    private String taskDes;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务依赖
     */
    private String dependTaskTplCode;

    /**
     * 发布人用户id
     */
    private String publisherUserId;

    /**
     * 接受人id
     */
    private String receiverUserId;

    /**
     * 子节点任务
     */
    private String nextTaskTplCode;

    /**
     * 模版创建时间
     */
    private Date createDate;

    /**
     * 是否是根节点
     */
    private Boolean isParent;

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

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
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

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
