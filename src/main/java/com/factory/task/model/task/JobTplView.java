package com.factory.task.model.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by tianjian on 2020/5/17.
 * 工作模版名称和任务模版编码
 */
@ApiModel
public class JobTplView {

    /**
     * 模版id
     */
    @ApiModelProperty("模版id")
    private String jobTplCode;

    /**
     * 工作模版名称
     */
    @ApiModelProperty("工作模版名称")
    private String jobTplName;

    /**
     * 人员模版数据
     */
    @ApiModelProperty("人员模版数据")
    private List<TaskHandleTemplateView> taskHandleTemplateViews;

    public String getJobTplCode() {
        return jobTplCode;
    }

    public void setJobTplCode(String jobTplCode) {
        this.jobTplCode = jobTplCode;
    }

    public String getJobTplName() {
        return jobTplName;
    }

    public void setJobTplName(String jobTplName) {
        this.jobTplName = jobTplName;
    }

    public List<TaskHandleTemplateView> getTaskHandleTemplateViews() {
        return taskHandleTemplateViews;
    }

    public void setTaskHandleTemplateViews(List<TaskHandleTemplateView> taskHandleTemplateViews) {
        this.taskHandleTemplateViews = taskHandleTemplateViews;
    }
}
