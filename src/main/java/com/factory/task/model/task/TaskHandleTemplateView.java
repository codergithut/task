package com.factory.task.model.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by tianjian on 2020/5/17.
 */
@ApiModel
public class TaskHandleTemplateView {

    @ApiModelProperty("处理用户")
    private String dependUserCode;

    @ApiModelProperty("模版编码")
    private String taskCode;

    @ApiModelProperty("普通用户")
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
