package com.factory.task.data.user.curd.relation;

import com.factory.task.data.user.relation.UserAndRoleRelationData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UserAndRoleRelationDataCurd extends CrudRepository<UserAndRoleRelationData, String> {
    List<UserAndRoleRelationData> findByUserCode(String userCode);
}
