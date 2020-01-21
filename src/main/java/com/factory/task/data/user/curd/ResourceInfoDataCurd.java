package com.factory.task.data.user.curd;

import com.factory.task.data.user.ResourceInfoData;
import com.factory.task.data.user.RoleInfoData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface ResourceInfoDataCurd extends CrudRepository<ResourceInfoData,String> {
}
