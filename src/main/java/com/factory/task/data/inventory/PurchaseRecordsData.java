package com.factory.task.data.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/2/16.
 */
@Entity
public class PurchaseRecordsData {

    /**
     * 入库编号
     */
    @Id
    private String purchaseRecordsCode;

    /**
     * 材料编码
     */
    private String materialCode;

    /**
     * 购买数量
     */
    private Integer purchaseNum;

    /**
     * 购买时间
     */
    private Date purchaseDate;

    /**
     * 入库描述
     */
    private String purchaseDesc;

    public String getPurchaseRecordsCode() {
        return purchaseRecordsCode;
    }

    public void setPurchaseRecordsCode(String purchaseRecordsCode) {
        this.purchaseRecordsCode = purchaseRecordsCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    public void setPurchaseDesc(String purchaseDesc) {
        this.purchaseDesc = purchaseDesc;
    }
}
