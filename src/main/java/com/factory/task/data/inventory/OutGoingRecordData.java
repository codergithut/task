package com.factory.task.data.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/2/16.
 */
@Entity
public class OutGoingRecordData {

    /**
     * 出库记录编码
     */
    @Id
    private String outGoingRecordCode;

    /**
     * 材料编码
     */
    private String materialCode;

    /**
     * 出库数量
     */
    private Integer outGoingNum;

    /**
     * 购买时间
     */
    private Date outGoingDate;

    /**
     * 出库描述
     */
    private String outGoingDesc;

    public String getOutGoingRecordCode() {
        return outGoingRecordCode;
    }

    public void setOutGoingRecordCode(String outGoingRecordCode) {
        this.outGoingRecordCode = outGoingRecordCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getOutGoingNum() {
        return outGoingNum;
    }

    public void setOutGoingNum(Integer outGoingNum) {
        this.outGoingNum = outGoingNum;
    }

    public Date getOutGoingDate() {
        return outGoingDate;
    }

    public void setOutGoingDate(Date outGoingDate) {
        this.outGoingDate = outGoingDate;
    }

    public String getOutGoingDesc() {
        return outGoingDesc;
    }

    public void setOutGoingDesc(String outGoingDesc) {
        this.outGoingDesc = outGoingDesc;
    }
}
