package com.factory.task.data.inventory.curd;

import com.factory.task.data.inventory.PurchaseRecordsData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/2/16.
 */
public interface PurchaseRecordsDataCurd extends CrudRepository<PurchaseRecordsData,String> {
}
