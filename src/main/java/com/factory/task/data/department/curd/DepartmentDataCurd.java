package com.factory.task.data.department.curd;

import com.factory.task.data.department.DepartmentData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/2/13.
 */
public interface DepartmentDataCurd extends CrudRepository<DepartmentData,String> {
}
