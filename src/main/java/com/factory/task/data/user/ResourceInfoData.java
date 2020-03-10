package com.factory.task.data.user;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
public class ResourceInfoData {

    /**
     * 资源编码
     */
    @Id
    private String resourceCode;

    /**
     * 资源名称
     */
    private String resourceName;

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
