package com.factory.task.data.inventory.curd;

import com.factory.task.data.inventory.InventoryRecordData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/2/16.
 */
public interface InventoryRecordDataCurd extends CrudRepository<InventoryRecordData,String> {
    InventoryRecordData findByMaterialCodeAndDataTag(String materialCode, Integer dataTag);
    List<InventoryRecordData> findByMaterialCode(String materialCode);
}
