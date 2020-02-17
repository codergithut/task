package com.factory.task.data.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/2/16.
 */
@Entity
public class MaterialData {

    /**
     * 材料编码
     */
    @Id
    private String materialCode;

    /**
     * 用料说明
     */
    private String desc;

    /**
     * 品名
     */
    private String materialName;

    /**
     * 品色
     */
    private String materialColour;

    /**
     * 备注
     */
    private String remarks;


    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialColour() {
        return materialColour;
    }

    public void setMaterialColour(String materialColour) {
        this.materialColour = materialColour;
    }
}
