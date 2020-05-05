package com.factory.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.factory.task.TaskType;
import com.factory.task.data.task.TaskTplData;
import com.factory.task.data.task.TaskTplDescMetaData;
import com.factory.task.data.task.curd.TaskTplDataCurd;
import com.factory.task.data.task.curd.TaskTplDescMetaDataCurd;
import com.factory.task.model.task.TaskTplDescMetaView;
import com.factory.task.model.task.TaskTplView;
import com.factory.task.service.TaskTplService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.factory.task.TaskType.DEPEND;

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
        if(!TaskType.checkType(taskTplView.getTaskType())) {
            return false;
        }
        if(DEPEND.getType().equals(taskTplView.getTaskType())) {
            if(taskTplView.getNextTaskCode() != null || taskTplView.getDependTaskCodes() != null) {
                return false;
            }
        }
        String taskCode = saveTaskTpl(taskTplView);
        if(!CollectionUtils.isEmpty(taskTplDescMetaViews)) {
            saveTaskTplDescMeta(taskTplDescMetaViews, taskCode);
        }
        taskTplView.setTaskCode(taskCode);
        return !StringUtils.isEmpty(taskCode);

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
        BeanUtils.copyProperties(taskTplView, taskTplData);
        String nextTask = taskTplView.getNextTaskCode();
        if(!CollectionUtils.isEmpty(taskTplView.getDependTaskCodes())) {
            taskTplData.setDependTaskTplCode(JSON.toJSONString(taskTplView.getDependTaskCodes()));
        }
        taskTplData.setCreateDate(new Date());
        if(DEPEND.getType().equals(taskTplView.getTaskType())) {
            taskTplData.setDependTaskTplCode(null);
            taskTplData.setNextTaskTplCode(null);
        }
        //todo 模版需要校验
        if(!StringUtils.isEmpty(nextTask)) {
            taskTplData.setNextTaskTplCode(taskTplView.getNextTaskCode());
        }
        taskTplData.setCreateDate(new Date());
        taskTplData = taskTplDataCurd.save(taskTplData);
        return taskTplData.getTaskCode();
    }

    @Override
    public List<TaskTplView> getParentTaskTpl(Boolean isParent) {
        String taskType = null;
        if(isParent) {
            taskType = TaskType.NODE.getType();
        }else {
            taskType = DEPEND.getType();
        }

        return taskTplDataCurd.findTaskTplDataByTaskType(taskType).stream().map(e -> {
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

    @Override
    public List<TaskTplView> getAllTaskTpl() {
        List<TaskTplData> taskTplDatas = Lists.newArrayList(taskTplDataCurd.findAll());
        if(!CollectionUtils.isEmpty(taskTplDatas)) {
            return taskTplDatas.stream().map(e -> {
                TaskTplView taskTplView = new TaskTplView();
                BeanUtils.copyProperties(e, taskTplView);
                return taskTplView;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<TaskTplView> getTaskTplByNodeType(String taskType) {
        List<TaskTplData> taskTplDatas = taskTplDataCurd.findTaskTplDataByTaskType(taskType);
        if(!CollectionUtils.isEmpty(taskTplDatas)) {
            return taskTplDatas.stream().map(e -> {
                TaskTplView taskTplView = new TaskTplView();
                BeanUtils.copyProperties(e, taskTplView);
                return taskTplView;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
