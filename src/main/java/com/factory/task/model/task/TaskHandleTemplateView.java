package com.factory.task.model.task;

import java.util.List;

/**
 * Created by tianjian on 2020/5/17.
 */
public class TaskHandleTemplateView {
    private String dependUserCode;
    private String taskCode;
    private List<String> commonUserCode;

    public String getDependUserCode() {
        return dependUserCode;
    }

    public void setDependUserCode(String dependUserCode) {
        this.dependUserCode = dependUserCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public List<String> getCommonUserCode() {
        return commonUserCode;
    }

    public void setCommonUserCode(List<String> commonUserCode) {
        this.commonUserCode = commonUserCode;
    }
}
