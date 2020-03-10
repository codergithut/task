package com.factory.task.data.task;



import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/13.
 */
@Entity
public class TaskTplDescMetaData {

    /**
     * 任务描述编码
     */
    @Id
    private String taskTplDescCode;

    /**
     * 任务模版编码
     */
    private String taskCode;

    /**
     * 描述名称
     */
    private String metaName;

    /**
     * 描述类型 int string so on
     */
    private String metaType;

    public String getTaskTplDescCode() {
        return taskTplDescCode;
    }

    public void setTaskTplDescCode(String taskTplDescCode) {
        this.taskTplDescCode = taskTplDescCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

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
}
