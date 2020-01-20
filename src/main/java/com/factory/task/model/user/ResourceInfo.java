package com.factory.task.model.user;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public class ResourceInfo {

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 资源名称
     */
    private String resourceName;

    private List<String> uriCodes;


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

    public List<String> getUriCodes() {
        return uriCodes;
    }

    public void setUriCodes(List<String> uriCodes) {
        this.uriCodes = uriCodes;
    }
}
