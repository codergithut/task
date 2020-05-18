package com.factory.task.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/16.
 */
@ApiModel
public class UriInfo {

    /**
     * uri资源编码
     */
    @Id
    @ApiModelProperty("uri资源编码")
    private String uriCode;

    /**
     * uri名称
     */
    @ApiModelProperty("uri名称")
    private String uriName;

    /**
     * uri地址
     */
    @ApiModelProperty("uri地址")
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
