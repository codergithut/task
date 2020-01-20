package com.factory.task.service;

import com.factory.task.model.task.TaskTplView;

import java.util.List;

/**
 * Created by tianjian on 2020/1/13.
 */
public interface TaskTplService {

    /**
     * 创建任务模版
     * @param taskTplView 任务模版前端数据
     * @return
     */
    Boolean createTaskTpl(TaskTplView taskTplView);

    /**
     * 获取所有根节点数据
     * @return
     */
    List<TaskTplView> getParentTaskTpl();
}
