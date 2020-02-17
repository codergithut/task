package com.factory.task.service;

import com.factory.task.model.inventory.InventoryRecordView;
import com.factory.task.model.inventory.MaterialView;
import com.factory.task.model.inventory.OutGoingRecordView;
import com.factory.task.model.inventory.PurchaseRecordsView;

import java.util.List;

/**
 * Created by tianjian on 2020/2/16.
 */
public interface InventoryService {
    Boolean saveMaterial(MaterialView materialView);

    List<MaterialView> getAllMaterials(String materialName);

    Boolean savePurchaseRecords(List<PurchaseRecordsView> purchaseRecordsViews);

    Boolean saveOutGoingRecords(List<OutGoingRecordView> outGoingRecordViews);

    List<InventoryRecordView> listInventoryByMaterialCode(String materialCode);
}
