package com.factory.task.data.user.relation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/1/15.
 */
@Entity
@Data
public class ResourceAndUriRelationData {

    /**
     * code 关系编码
     */
    @Id
    private String code;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * uri编码
     */
    private String uriCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getUriCode() {
        return uriCode;
    }

    public void setUriCode(String uriCode) {
        this.uriCode = uriCode;
    }
}
