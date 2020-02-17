package com.factory.task.controller;

import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.inventory.InventoryRecordView;
import com.factory.task.model.inventory.MaterialView;
import com.factory.task.model.inventory.OutGoingRecordView;
import com.factory.task.model.inventory.PurchaseRecordsView;
import com.factory.task.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2020/2/16.
 */
@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create/material")
    public RestModelTemplate<Boolean> createMaterial(@RequestBody MaterialView materialView) {
        return new RestModelTemplate<>().Success(inventoryService.saveMaterial(materialView));
    }

    @GetMapping("/list/material")
    public RestModelTemplate<MaterialView> listMaterialView(@RequestParam("materialName") String materialName) {
        return new RestModelTemplate<>().Success(inventoryService.getAllMaterials(materialName));
    }

    @PostMapping("/create/purchaseRecords")
    public RestModelTemplate<Boolean> createPurchaseRecords(@RequestBody List<PurchaseRecordsView> purchaseRecordsViews) {
        return new RestModelTemplate<>().Success(inventoryService.savePurchaseRecords(purchaseRecordsViews));
    }

    @PostMapping("/create/outGoingRecords")
    public RestModelTemplate<Boolean> createOutGoingRecords(@RequestBody List<OutGoingRecordView> outGoingRecordViews) {
        return new RestModelTemplate<>().Success(inventoryService.saveOutGoingRecords(outGoingRecordViews));
    }

    @GetMapping("/list/inventory")
    public RestModelTemplate<List<InventoryRecordView>> listInventory(@RequestParam("materialCode") String materialCode) {
        return new RestModelTemplate<>().Success(inventoryService.listInventoryByMaterialCode(materialCode));
    }


}
