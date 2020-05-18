package com.factory.task.model.department;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tianjian on 2020/2/13.
 */
@ApiModel
public class DepartmentView {

    /**
     * 部门编码
     */
    @ApiModelProperty("部门编码")
    private String departmentCode;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String departmentName;

    /**
     * 上级部门编码
     */
    @ApiModelProperty("上级部门编码")
    private String parentDepartmentCode;

    /**
     * 下级部门编码
     */
    @ApiModelProperty("下级部门编码")
    private String childDepartmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentDepartmentCode() {
        return parentDepartmentCode;
    }

    public void setParentDepartmentCode(String parentDepartmentCode) {
        this.parentDepartmentCode = parentDepartmentCode;
    }

    public String getChildDepartmentCode() {
        return childDepartmentCode;
    }

    public void setChildDepartmentCode(String childDepartmentCode) {
        this.childDepartmentCode = childDepartmentCode;
    }
}
