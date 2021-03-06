package com.factory.task.model.task;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tianjian on 2020/2/11.
 */
@ApiModel
public class TaskTplDescMetaView {

    /**
     * 任务描述编码
     */
    @ApiModelProperty("任务描述编码")
    private String taskTplDescCode;

    /**
     * 任务模版编码
     */
    @ApiModelProperty("任务模版编码")
    private String taskCode;

    /**
     * 描述名称
     */
    @ApiModelProperty("描述名称")
    private String metaName;


    /**
     * 描述类型 int string so on
     */
    @ApiModelProperty("描述类型 int string so on")
    private String metaType;

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName;
    }

    public String getMetaType() {
        return metaType;
    }

    public void setMetaType(String metaType) {
        this.metaType = metaType;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskTplDescCode() {
        return taskTplDescCode;
    }

    public void setTaskTplDescCode(String taskTplDescCode) {
        this.taskTplDescCode = taskTplDescCode;
    }

}
