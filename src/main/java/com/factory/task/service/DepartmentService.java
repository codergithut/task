package com.factory.task.service;

import com.factory.task.model.department.DepartmentView;

import java.util.List;

/**
 * Created by tianjian on 2020/2/13.
 */
public interface DepartmentService {
    Boolean saveDepartment(DepartmentView departmentView);

    List<DepartmentView> listDepartment();

    void deleteDepartmentByCode(String departmentCode);
}
