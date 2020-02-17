package com.factory.task.data.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/2/16.
 */
@Entity
public class InventoryRecordData {

    /**
     * 仓库库存编码记录
     */
    @Id
    private String inventoryRecordCode;

    /**
     * 材料编码
     */
    private String materialCode;

    /**
     * 库存数量
     */
    private Integer existsNum;

    /**
     * 已领用用数量
     */
    private Integer usedNum;

    /**
     * 改变仓库记录的源头id
     */
    private String changeRecordCode;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 数据更新版本值方便查询数据变化
     */
    private Integer version;

    /**
     * 数据标记 最新的数据才是可以直接看的，其它是统计数据使用
     */
    private Integer dataTag;

    public String getInventoryRecordCode() {
        return inventoryRecordCode;
    }

    public void setInventoryRecordCode(String inventoryRecordCode) {
        this.inventoryRecordCode = inventoryRecordCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getExistsNum() {
        return existsNum;
    }

    public void setExistsNum(Integer existsNum) {
        this.existsNum = existsNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public String getChangeRecordCode() {
        return changeRecordCode;
    }

    public void setChangeRecordCode(String changeRecordCode) {
        this.changeRecordCode = changeRecordCode;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataTag() {
        return dataTag;
    }

    public void setDataTag(Integer dataTag) {
        this.dataTag = dataTag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
