package com.factory.task.data.task;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/5/17.
 * 工作模版数据
 */
@Entity
public class JobTemplateData {

    /**
     * 工作模版编码
     */
    @Id
    private String jobTemplateCode;

    /**
     * 模版名称
     */
    private String jobTemplateName;



    public String getJobTemplateCode() {
        return jobTemplateCode;
    }

    public void setJobTemplateCode(String jobTemplateCode) {
        this.jobTemplateCode = jobTemplateCode;
    }

    public String getJobTemplateName() {
        return jobTemplateName;
    }

    public void setJobTemplateName(String jobTemplateName) {
        this.jobTemplateName = jobTemplateName;
    }
}
