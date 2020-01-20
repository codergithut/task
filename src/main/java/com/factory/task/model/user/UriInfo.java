package com.factory.task.model.user;

import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/16.
 */
public class UriInfo {

    /**
     * uri资源编码
     */
    @Id
    private String uriCode;

    /**
     * uri名称
     */
    private String uriName;

    /**
     * uri地址
     */
    private String uriPath;

    public String getUriCode() {
        return uriCode;
    }

    public void setUriCode(String uriCode) {
        this.uriCode = uriCode;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getUriPath() {
        return uriPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath;
    }
}
