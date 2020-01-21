package com.factory.task.service.impl;

import com.factory.task.data.task.JobData;
import com.factory.task.data.task.TaskInsData;
import com.factory.task.data.task.TaskInsExtData;
import com.factory.task.data.task.TaskTplData;
import com.factory.task.data.task.curd.JobDataCurd;
import com.factory.task.data.task.curd.TaskInsDataCurd;
import com.factory.task.data.task.curd.TaskInsExtDataCurd;
import com.factory.task.data.task.curd.TaskTplDataCurd;
import com.factory.task.model.task.JobView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.model.task.TaskInsView;
import com.factory.task.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/1/13.
 */
@Service
public class JobServiceImpl implements JobService {


    @Autowired
    private JobDataCurd jobDataCurd;

    @Autowired
    private TaskTplDataCurd taskTplDataCurd;

    @Autowired
    private TaskInsDataCurd taskInsDataCurd;

    @Autowired
    private TaskInsExtDataCurd taskInsExtDataCurd;


    @Override
    public Boolean createJob(JobView jobView) {
        if(jobDataCurd.findByJobCode(jobView.getJobCode()) != null) {
            return false;
        }
        JobData jobData = new JobData();
        BeanUtils.copyProperties(jobView, jobData);
        return jobDataCurd.save(jobData) != null;
    }

    @Override
    public Boolean editJob(JobView jobView) {
        JobData jobData = jobDataCurd.findByJobCode(jobView.getJobCode());
        if(jobData == null) {
            return false;
        }
        BeanUtils.copyProperties(jobView, jobData);
        return jobDataCurd.save(jobData) != null;


    }

    @Override
    public Boolean startJob(String jobCode) {
        JobData jobData = jobDataCurd.findByJobCode(jobCode);
        String startTaskTplCode = jobData.getStartTaskTplCode();
        TaskTplData startTaskTpl = taskTplDataCurd.findTaskTplDataByTaskCode(startTaskTplCode);
        createTaskInsByTpl(startTaskTpl, jobCode);
        return true;
    }

    @Override
    public Boolean finishTaskIns(String taskInsCode) {
        TaskInsData taskInsData = taskInsDataCurd.findTaskInsDataByTaskInsCode(taskInsCode);
        if(!StringUtils.isEmpty(taskInsData.getDependTaskTplCode())) {
            TaskInsData depTaskInsData = taskInsDataCurd.findTaskInsDataByTaskTplCodeAndJobCode
                    (taskInsData.getDependTaskTplCode(), taskInsData.getJobCode());
            if(!"Finish".equals(depTaskInsData.getTaskStatus())) {
                return false;
            }
        }
        taskInsData.setTaskStatus("Finish");
        taskInsDataCurd.save(taskInsData);

        if(StringUtils.isEmpty(taskInsData.getNextTaskTplCode())) {
            JobData jobData = jobDataCurd.findByJobCode(taskInsData.getJobCode());
            jobData.setIsFinished("Finish");
            jobDataCurd.save(jobData);
        } else {
            TaskTplData taskTplData = taskTplDataCurd.findTaskTplDataByTaskCode(taskInsData.getNextTaskTplCode());
            createTaskInsByTpl(taskTplData, taskInsData.getJobCode());
        }

        return true;
    }

    @Override
    public List<TaskInsView> findTaskInsByStatus(String taskStatus) {

        List<TaskInsData> taskInsDatas = taskInsDataCurd.findTaskInsDataByTaskStatus(taskStatus);
        taskInsDatas.stream().map(e -> {
            TaskInsView taskInsView = new TaskInsView();
            BeanUtils.copyProperties(e, taskInsView);
            return taskInsView;
        }).collect(Collectors.toList());

        return taskInsDatas.stream().map(e -> {
            TaskInsView taskInsView = new TaskInsView();
            BeanUtils.copyProperties(e, taskInsView);
            return taskInsView;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean addTaskInsExtInfo(TaskInsExtView taskInsExt) {
        TaskInsExtData taskInsExtData = new TaskInsExtData();
        BeanUtils.copyProperties(taskInsExt, taskInsExtData);
        return taskInsExtDataCurd.save(taskInsExtData) != null;
    }

    @Override
    public List<JobView> findJobViewsByUserId(String userCode) {
        List<JobData> jobDatas = (List<JobData>) jobDataCurd.findAll();
        return jobDatas.stream().map(e -> {
            JobView jobView = new JobView();
            BeanUtils.copyProperties(e, jobView);
            return jobView;
        }).collect(Collectors.toList());
    }

    @Override
    public List<JobView> findJobViewsByWaitMe(String userCode) {
        List<TaskInsData> taskInsDatas = taskInsDataCurd.findTaskInsDataByTaskStatus("begin");
        if(CollectionUtils.isEmpty(taskInsDatas)) {
            return null;
        }
        return taskInsDatas.stream().map(e -> {
            JobData jobData = jobDataCurd.findByJobCode(e.getJobCode());
            JobView jobView = new JobView();
            BeanUtils.copyProperties(jobData, jobView);
            return jobView;
        }).collect(Collectors.toList());
    }

    private void createTaskInsByTpl(TaskTplData taskTplData, String jobCode) {
        List<TaskTplData> taskTplDatas = new ArrayList<>();
        TaskInsData taskInsData = new TaskInsData();
        taskInsData.setTaskTplCode(taskTplData.getTaskCode());
        taskInsData.setTaskStatus("begin");
        taskInsData.setTaskInsCode(UUID.randomUUID().toString());
        taskInsData.setDependTaskTplCode(taskTplData.getDependTaskTplCode());
        taskInsData.setNextTaskTplCode(taskTplData.getNextTaskTplCode());
        taskInsData.setJobCode(jobCode);
        taskInsDataCurd.save(taskInsData);
        String dependTaskTplCode = taskTplData.getDependTaskTplCode();
        if(!StringUtils.isEmpty(dependTaskTplCode)) {
            TaskTplData depTaskTpl = taskTplDataCurd.findTaskTplDataByTaskCode(dependTaskTplCode);
            createTaskInsByTpl (depTaskTpl, jobCode);
        }

    }

}