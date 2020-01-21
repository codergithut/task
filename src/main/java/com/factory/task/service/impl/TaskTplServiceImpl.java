package com.factory.task.service.impl;

import com.factory.task.data.task.TaskTplData;
import com.factory.task.data.task.curd.TaskTplDataCurd;
import com.factory.task.model.task.TaskTplView;
import com.factory.task.service.TaskTplService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/1/13.
 */
@Service
public class TaskTplServiceImpl implements TaskTplService {

    @Autowired
    private TaskTplDataCurd taskTplDataCurd;

    @Override
    public Boolean createTaskTpl(TaskTplView taskTplView) {
        return !StringUtils.isEmpty(saveTaskTpl(taskTplView));

    }

    private String saveTaskTpl(TaskTplView taskTplView) {
        String nextCode = null;
        String dependCode = null;
        if(taskTplView.getNextTaskTpl() != null) {
            nextCode = saveTaskTpl(taskTplView.getNextTaskTpl());
        }
        if(taskTplView.getTaskDependTpl() != null) {
            dependCode = saveTaskTpl(taskTplView.getTaskDependTpl());
        }

        return saveData(taskTplView, nextCode, dependCode);
    }

    private String saveData(TaskTplView taskTplView, String nextCode, String dependCode) {
        TaskTplData taskTplData = new TaskTplData();
        BeanUtils.copyProperties(taskTplView, taskTplData);
        taskTplData.setDependTaskTplCode(dependCode);
        taskTplData.setNextTaskTplCode(nextCode);
        taskTplData = taskTplDataCurd.save(taskTplData);
        return taskTplData.getTaskCode();
    }


    @Override
    public List<TaskTplView> getParentTaskTpl() {
        return taskTplDataCurd.findTaskTplDataByIsParent(true).stream().map(e -> {
            TaskTplView taskTplView = new TaskTplView();
            BeanUtils.copyProperties(e, taskTplView);
            return taskTplView;
        }).collect(Collectors.toList());
    }
}
