package com.factory.task.data.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
@Data
public class UriInfoData {

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
