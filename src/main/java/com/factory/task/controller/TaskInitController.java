package com.factory.task.controller;

import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.TaskTplDescMetaView;
import com.factory.task.model.task.TaskTplView;
import com.factory.task.service.TaskTplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.factory.task.config.ResponseCodeEnum.TASK_TPL_ERROR;
import static com.factory.task.util.ConstantUtils.USERINFO;

/**
 * 创建工作流模板
 * Created by tianjian on 2020/1/11.
 */
@RestController
@RequestMapping("/taskTpl")
@CrossOrigin
public class TaskInitController {

    @Autowired
    private TaskTplService taskTplService;

    @Autowired
    private AuthResource authResource;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/create")
    public RestModelTemplate<Boolean> createTaskTpl(@RequestBody TaskTplView taskTplView, HttpServletRequest request) {
        String userCode = (String)request.getSession().getAttribute(USERINFO);
        taskTplView.setTaskCode(UUID.randomUUID().toString());
        taskTplView.setPublisherUserId(userCode);
        taskTplView.setIsParent(true);
        taskTplView.setCreateDate(new Date());
        if(taskTplService.createTaskTpl(taskTplView)) {
            return RestModelTemplate.OK();
        } else {
            return new RestModelTemplate(TASK_TPL_ERROR.getCode(), TASK_TPL_ERROR.getDesc());
        }
    }

    @GetMapping("/list")
    public RestModelTemplate<List<TaskTplView>> getAllFactoryTaskTpl(@RequestParam("isParent") Boolean isParent) {
        return new RestModelTemplate<List<TaskTplView>>().Success(taskTplService.getParentTaskTpl(isParent));
    }

    @GetMapping("/listAll")
    public RestModelTemplate<List<TaskTplView>> getAllTaskTpl() {
        return new RestModelTemplate<List<TaskTplView>>().Success(taskTplService.getAllTaskTpl());
    }

    @GetMapping("/listByTaskType")
    public RestModelTemplate<List<TaskTplView>> getAllTaskTpl(@RequestParam("taskType") String taskType) {
        return new RestModelTemplate<List<TaskTplView>>().Success(taskTplService.getTaskTplByNodeType(taskType));
    }

    @GetMapping("/getTplDescMeta")
    public RestModelTemplate<List<TaskTplDescMetaView>>
    getTaskDescTplDescMetaViewByTaskCode(@RequestParam("taskCode") String taskCode) {
        return new RestModelTemplate<List<TaskTplDescMetaView>>().Success(taskTplService.getTaskDescMetaByTaskCode(taskCode));

    }

}
