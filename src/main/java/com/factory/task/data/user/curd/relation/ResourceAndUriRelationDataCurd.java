package com.factory.task.data.user.curd.relation;

import com.factory.task.data.user.relation.ResourceAndUriRelationData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface ResourceAndUriRelationDataCurd extends CrudRepository<ResourceAndUriRelationData,String> {
}
