package com.factory.task.model.inventory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tianjian on 2020/2/16.
 */
@ApiModel
public class MaterialView {

    /**
     * 材料编码
     */
    @ApiModelProperty("材料编码")
    private String materialCode;

    /**
     * 品名
     */
    @ApiModelProperty("品名")
    private String materialName;

    /**
     * 品色
     */
    @ApiModelProperty("")
    private String materialColour;

    /**
     * 用料说明
     */
    @ApiModelProperty("用料说明")
    private String materialDesc;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;


    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
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
