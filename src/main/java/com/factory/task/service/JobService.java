package com.factory.task.service;

import com.factory.task.model.task.JobView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.model.task.TaskInsView;

import java.util.List;


/**
 * Created by tianjian on 2020/1/13.
 */
public interface JobService {
    
    Boolean createJob(JobView jobView, String pubCode);

    Boolean editJob(JobView jobView);

    Boolean startJob(String jobCode);

    Boolean finishTaskIns(String taskInsCode);

    List<TaskInsView> findTaskInsByStatus(String taskStatus, String userCode);

    Boolean addTaskInsExtInfo(TaskInsExtView taskInsExt);

    List<JobView> findJobViewsByUserId(String userCode);

    List<JobView> findJobViewsByWaitMe(String userCode);

    TaskInsView findTaskInsByCode(String taskInsCode);

    Boolean editTaskInsByCode(String taskInsCode, String taskInsDescInfo);
}
