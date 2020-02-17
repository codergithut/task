package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.department.DepartmentView;
import com.factory.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/2/13.
 */
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/create")
    public RestModelTemplate<Boolean> createDepartment(@RequestBody DepartmentView departmentView) {
        return new RestModelTemplate<>().Success(departmentService.saveDepartment(departmentView));
    }

    @GetMapping("/list")
    public RestModelTemplate<List<DepartmentView>> listDepartment() {
        return new RestModelTemplate<>().Success(departmentService.listDepartment());
    }

    @DeleteMapping("/delete")
    public RestModelTemplate<Boolean> deleteDepartment(@RequestParam("departmentCode") String departmentCode) {
        departmentService.deleteDepartmentByCode(departmentCode);
        return new RestModelTemplate<>().Success(true);
    }


}
