package com.factory.task.service.impl;

import com.factory.task.data.inventory.InventoryRecordData;
import com.factory.task.data.inventory.MaterialData;
import com.factory.task.data.inventory.OutGoingRecordData;
import com.factory.task.data.inventory.PurchaseRecordsData;
import com.factory.task.data.inventory.curd.InventoryRecordDataCurd;
import com.factory.task.data.inventory.curd.MaterialDataCurd;
import com.factory.task.data.inventory.curd.OutGoingRecordDataCurd;
import com.factory.task.data.inventory.curd.PurchaseRecordsDataCurd;
import com.factory.task.model.inventory.InventoryRecordView;
import com.factory.task.model.inventory.MaterialView;
import com.factory.task.model.inventory.OutGoingRecordView;
import com.factory.task.model.inventory.PurchaseRecordsView;
import com.factory.task.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/2/17.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private MaterialDataCurd materialDataCurd;

    @Autowired
    private InventoryRecordDataCurd inventoryRecordDataCurd;

    @Autowired
    private OutGoingRecordDataCurd outGoingRecordDataCurd;

    @Autowired
    private PurchaseRecordsDataCurd purchaseRecordsDataCurd;

    @Override
    public Boolean saveMaterial(MaterialView materialView) {
        MaterialData materialData = new MaterialData();
        BeanUtils.copyProperties(materialView, materialData);
        materialData.setMaterialCode(UUID.randomUUID().toString());
        return materialDataCurd.save(materialData) != null;
    }

    @Override
    public List<MaterialView> getAllMaterials(String materialName) {
        List<MaterialData> materialDatas = materialDataCurd.findByMaterialName(materialName);
        if(CollectionUtils.isEmpty(materialDatas)) {
            return new ArrayList<>();
        }
        return materialDatas.stream().map(e -> {
            MaterialView materialView = new MaterialView();
            BeanUtils.copyProperties(e, materialView);
            return materialView;

        }).collect(Collectors.toList());
    }

    @Override
    public Boolean savePurchaseRecords(List<PurchaseRecordsView> purchaseRecordsViews) {
        purchaseRecordsViews.forEach(e -> {
            PurchaseRecordsData purchaseRecordsData = new PurchaseRecordsData();
            BeanUtils.copyProperties(e, purchaseRecordsData);
            purchaseRecordsData.setPurchaseRecordsCode(UUID.randomUUID().toString());
            purchaseRecordsDataCurd.save(purchaseRecordsData);
            InventoryRecordData inventoryRecordData = inventoryRecordDataCurd
                    .findByMaterialCodeAndDataTag(purchaseRecordsData.getMaterialCode(),0);
            if(inventoryRecordData != null) {
                inventoryRecordData.setDataTag(1);
                inventoryRecordDataCurd.save(inventoryRecordData);
                inventoryRecordData.setInventoryRecordCode(UUID.randomUUID().toString());
                inventoryRecordData.setExistsNum(inventoryRecordData.getExistsNum() + purchaseRecordsData.getPurchaseNum());
                inventoryRecordData.setDataTag(0);
                inventoryRecordData.setUpdateTime(new Date());
                inventoryRecordData.setVersion(inventoryRecordData.getVersion() + 1);
                inventoryRecordDataCurd.save(inventoryRecordData);
            } else {
                inventoryRecordData = new InventoryRecordData();
                inventoryRecordData.setDataTag(0);
                inventoryRecordData.setInventoryRecordCode(UUID.randomUUID().toString());
                inventoryRecordData.setVersion(1);
                inventoryRecordData.setUsedNum(0);
                inventoryRecordData.setMaterialCode(e.getMaterialCode());
                inventoryRecordData.setChangeRecordCode(e.getPurchaseRecordsCode());
                inventoryRecordData.setExistsNum(e.getPurchaseNum());
                inventoryRecordDataCurd.save(inventoryRecordData);
            }

        });
        return true;
    }

    @Override
    public Boolean saveOutGoingRecords(List<OutGoingRecordView> outGoingRecordViews) {
        List<Map<String,String>> exceptionInfo = new ArrayList<>();
        outGoingRecordViews.forEach(e -> {
            InventoryRecordData inventoryRecordData = inventoryRecordDataCurd
                    .findByMaterialCodeAndDataTag(e.getMaterialCode(), 0);
            inventoryRecordData.setDataTag(1);
            inventoryRecordDataCurd.save(inventoryRecordData);
            if(inventoryRecordData == null) {
                return;
            }
            if(inventoryRecordData.getExistsNum() - e.getOutGoingNum() < 0) {
                Map<String,String> key = new HashMap<>();
                key.put(e.getMaterialCode(), "出库数量多于库存数量无法出库");
                exceptionInfo.add(key);
                return;
            }
            inventoryRecordData.setInventoryRecordCode(UUID.randomUUID().toString());
            inventoryRecordData.setExistsNum(inventoryRecordData.getExistsNum() - e.getOutGoingNum());
            inventoryRecordData.setUpdateTime(new Date());
            inventoryRecordData.setUsedNum(inventoryRecordData.getUsedNum() + e.getOutGoingNum());
            inventoryRecordData.setVersion(inventoryRecordData.getVersion() + 1);
            inventoryRecordData.setChangeRecordCode(e.getOutGoingRecordCode());
            inventoryRecordData.setDataTag(0);
            inventoryRecordDataCurd.save(inventoryRecordData);
            OutGoingRecordData outGoingRecordData = new OutGoingRecordData();
            BeanUtils.copyProperties(e, outGoingRecordData);
            outGoingRecordData.setOutGoingRecordCode(UUID.randomUUID().toString());
            outGoingRecordDataCurd.save(outGoingRecordData);

        });
        return true;
    }

    @Override
    public List<InventoryRecordView> listInventoryByMaterialCode(String materialCode) {
        List<InventoryRecordData> inventoryRecordDatas = inventoryRecordDataCurd.findByMaterialCode(materialCode);
        return inventoryRecordDatas.stream().map(e -> {
           InventoryRecordView inventoryRecordView = new InventoryRecordView();
           BeanUtils.copyProperties(e, inventoryRecordView);
           return inventoryRecordView;
        }).collect(Collectors.toList());
    }
}
