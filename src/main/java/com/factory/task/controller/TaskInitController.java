package com.factory.task.controller;

import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.TaskTplView;
import com.factory.task.service.TaskTplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建工作流模板
 * Created by tianjian on 2020/1/11.
 */
@RestController
@RequestMapping("/taskTpl")
public class TaskInitController {

    @Autowired
    private TaskTplService taskTplService;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/create")
    public RestModelTemplate<Boolean> createTaskTpl(@RequestBody TaskTplView taskTplView) {
        return new RestModelTemplate<Boolean>().Success(taskTplService.createTaskTpl(taskTplView));
    }

    @RequestMapping("/list")
    public RestModelTemplate<List<TaskTplView>> getAllFactoryTaskTpl() {
        List<TaskTplView> taskTplViews = new ArrayList<>();
        return new RestModelTemplate<List<TaskTplView>>().Success(taskTplService.getParentTaskTpl());
    }

}
