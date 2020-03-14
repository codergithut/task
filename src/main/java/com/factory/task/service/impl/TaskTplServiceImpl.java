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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
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
        if(!CollectionUtils.isEmpty(taskTplDescMetaViews)) {
            saveTaskTplDescMeta(taskTplDescMetaViews, taskCode);
        }
        taskTplView.setTaskCode(taskCode);
        return !StringUtils.isEmpty(saveTaskTpl(taskTplView));

    }

    private void saveTaskTplDescMeta(List<TaskTplDescMetaView> taskTplDescMetaViews, String taskCode) {
        taskTplDescMetaViews.stream().forEach(e -> {
            TaskTplDescMetaData taskTplDescMetaData = new TaskTplDescMetaData();
            BeanUtils.copyProperties(e, taskTplDescMetaData);
            taskTplDescMetaData.setTaskCode(taskCode);
            taskTplDescMetaData.setTaskTplDescCode(UUID.randomUUID().toString());
            taskTplDescMetaDataCurd.save(taskTplDescMetaData);
        });
    }

    private String saveTaskTpl(TaskTplView taskTplView) {
        TaskTplData taskTplData = new TaskTplData();
        TaskTplData dependTask = taskTplDataCurd.findTaskTplDataByTaskCode(taskTplView.getDependTaskCode());
        TaskTplData nextTask = taskTplDataCurd.findTaskTplDataByTaskCode(taskTplView.getNextTaskCode());
        BeanUtils.copyProperties(taskTplView, taskTplData);
        taskTplData.setCreateDate(new Date());
        taskTplData.setDependTaskTplCode(null);
        taskTplData.setNextTaskTplCode(null);
        if(dependTask != null) {
            taskTplData.setDependTaskTplCode(dependTask.getTaskCode());
        }
        if(nextTask != null) {
            taskTplData.setNextTaskTplCode(taskTplView.getNextTaskCode());
        }
        taskTplData.setCreateDate(new Date());
        taskTplData = taskTplDataCurd.save(taskTplData);
        return taskTplData.getTaskCode();
    }

    @Override
    public List<TaskTplView> getParentTaskTpl(Boolean isParent) {
        return taskTplDataCurd.findTaskTplDataByIsParent(isParent).stream().map(e -> {
            TaskTplView taskTplView = new TaskTplView();
            BeanUtils.copyProperties(e, taskTplView);
            return taskTplView;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TaskTplDescMetaView> getTaskDescMetaByTaskCode(String taskCode) {
        List<TaskTplDescMetaView> datas = taskTplDescMetaDataCurd.findTaskDescMetaDataByTaskCode(taskCode).stream().map(e -> {
            TaskTplDescMetaView taskTplDescMetaView = new TaskTplDescMetaView();
            BeanUtils.copyProperties(e, taskTplDescMetaView);
            return taskTplDescMetaView;
        }).collect(Collectors.toList());
        return datas;
    }
}
