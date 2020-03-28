package com.factory.task.data.user.curd.relation;

import com.factory.task.data.user.relation.RoleAndUriRelationData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface RoleAndUriRelationDataCurd extends CrudRepository<RoleAndUriRelationData,String> {
    List<RoleAndUriRelationData> findByRoleCode(String e);
}
