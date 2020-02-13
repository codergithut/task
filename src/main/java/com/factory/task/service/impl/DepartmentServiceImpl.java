package com.factory.task.service.impl;

import com.factory.task.data.department.DepartmentData;
import com.factory.task.data.department.curd.DepartmentDataCurd;
import com.factory.task.data.user.UserInfoData;
import com.factory.task.model.user.DepartmentView;
import com.factory.task.service.DepartmentService;
import com.factory.task.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/2/13.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDataCurd departmentDataCurd;

    @Autowired
    private UserService userService;

    @Override
    public Boolean saveDepartment(DepartmentView departmentView) {
        DepartmentData departmentData = new DepartmentData();
        BeanUtils.copyProperties(departmentView, departmentData);
        departmentData.setDepartmentCode(UUID.randomUUID().toString());
        return departmentDataCurd.save(departmentData) != null;
    }

    @Override
    public List<DepartmentView> listDepartment() {
        List<DepartmentData> departmentDatas = (List<DepartmentData>) departmentDataCurd.findAll();
        return departmentDatas.stream().map(e -> {
            DepartmentView departmentView = new DepartmentView();
            BeanUtils.copyProperties(e,departmentView);
            return departmentView;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentByCode(String departmentCode) {
        List<UserInfoData> userInfoDatas = userService.findUserInfoDataByDepartmentCode(departmentCode);
        List<UserInfoData> filterUserInfoDatas = userInfoDatas.stream().map(e -> {
            e.setDepartmentCode(null);
            return e;
        }).collect(Collectors.toList());
        userService.updateUserInfoDatas(filterUserInfoDatas);
        departmentDataCurd.deleteById(departmentCode);
    }
}
