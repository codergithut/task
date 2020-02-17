package com.factory.task.data.inventory.curd;


import com.factory.task.data.inventory.OutGoingRecordData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/2/16.
 */
public interface OutGoingRecordDataCurd extends CrudRepository<OutGoingRecordData,String> {
}
