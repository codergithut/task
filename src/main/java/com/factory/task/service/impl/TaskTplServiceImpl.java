package com.factory.task.service.impl;

import com.factory.task.data.task.TaskTplData;
import com.factory.task.data.task.TaskTplDescMetaData;
import com.factory.task.data.task.curd.TaskTplDataCurd;
import com.factory.task.data.task.curd.TaskTplDescMetaDataCurd;
import com.factory.task.model.task.TaskTplDescMetaView;
import com.factory.task.model.task.TaskTplView;
import com.factory.task.service.TaskTplService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/1/13.
 */
@Service
public class TaskTplServiceImpl implements TaskTplService {

    @Autowired
    private TaskTplDataCurd taskTplDataCurd;

    @Autowired
    private TaskTplDescMetaDataCurd taskTplDescMetaDataCurd;

    @Override
    public Boolean createTaskTpl(TaskTplView taskTplView) {
        List<TaskTplDescMetaView> taskTplDescMetaViews = taskTplView.getTaskTplDescMetaViews();
        String taskCode = saveTaskTpl(taskTplView);
        saveTaskTplDescMeta(taskTplDescMetaViews, taskCode);
        taskTplView.setTaskCode(UUID.randomUUID().toString());
        return !StringUtils.isEmpty(saveTaskTpl(taskTplView));

    }

    private void saveTaskTplDescMeta(List<TaskTplDescMetaView> taskTplDescMetaViews, String taskCode) {
        String taskTplDescMetaId = UUID.randomUUID().toString();
        taskTplDescMetaViews.stream().forEach(e -> {
            TaskTplDescMetaData taskTplDescMetaData = new TaskTplDescMetaData();
            BeanUtils.copyProperties(e, taskTplDescMetaData);
            taskTplDescMetaData.setTaskTplDescCode(taskTplDescMetaId);
            taskTplDescMetaData.setTaskCode(taskCode);
            taskTplDescMetaData.setTaskTplDescCode(UUID.randomUUID().toString());
            taskTplDescMetaDataCurd.save(taskTplDescMetaData);
        });
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
        taskTplData.setCreateDate(new Date());
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

    @Override
    public List<TaskTplDescMetaView> getTaskDescMetaByTaskCode(String taskCode) {

        return taskTplDescMetaDataCurd.findTaskDescMetaDataByTaskCode(taskCode).stream().map(e -> {
            TaskTplDescMetaView taskTplDescMetaView = new TaskTplDescMetaView();
            BeanUtils.copyProperties(e, taskTplDescMetaView);
            return taskTplDescMetaView;
        }).collect(Collectors.toList());
    }
}
