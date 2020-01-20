package com.factory.task.data.user.curd.relation;

import com.factory.task.data.user.relation.RoleAndResourceRelationData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface RoleAndResourceRelationDataCurd extends CrudRepository<RoleAndResourceRelationData,String> {
}
