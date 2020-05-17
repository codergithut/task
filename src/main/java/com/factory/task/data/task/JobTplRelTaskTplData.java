package com.factory.task.data.task;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/5/17.
 */
@Entity
public class JobTplRelTaskTplData {

    /**
     * 工作模版编码
     */
    @Id
    private String jobTemplateCode;

    /**
     * 模版编码
     */
    private String taskCode;

    /**
     * 处理顺序
     */
    private Integer order;

    /**
     * 是否有下一个
     */
    private String hasNext;

    /**
     * 处理人编码
     */
    private String userCode;

    public String getJobTemplateCode() {
        return jobTemplateCode;
    }

    public void setJobTemplateCode(String jobTemplateCode) {
        this.jobTemplateCode = jobTemplateCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getHasNext() {
        return hasNext;
    }

    public void setHasNext(String hasNext) {
        this.hasNext = hasNext;
    }
}
