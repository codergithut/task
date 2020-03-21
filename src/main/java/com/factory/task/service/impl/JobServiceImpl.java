package com.factory.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.factory.task.data.task.*;
import com.factory.task.data.task.curd.*;
import com.factory.task.model.task.JobView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.model.task.TaskInsView;
import com.factory.task.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
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

    @Autowired
    private TaskTplDescMetaDataCurd taskTplDescMetaDataCurd;


    @Override
    public Boolean createJob(JobView jobView, String pubCode) {
        if(jobDataCurd.findByJobCode(jobView.getJobCode()) != null) {
            return false;
        }
        JobData jobData = new JobData();
        BeanUtils.copyProperties(jobView, jobData);
        jobData.setPublisherUserId(pubCode);
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
            List<String> dependTaskTplCodes = JSON.parseArray(taskInsData.getDependTaskTplCode(),String.class);
            for(String e : dependTaskTplCodes) {
                TaskInsData depTaskInsData = taskInsDataCurd.findTaskInsDataByTaskTplCodeAndJobCode
                        (e, taskInsData.getJobCode());
                if(!"Finish".equals(depTaskInsData.getTaskStatus())) {
                    return false;
                }
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
    public List<TaskInsView> findTaskInsByStatus(String taskStatus, String userCode) {

        List<TaskInsData> taskInsDatas = taskInsDataCurd.findTaskInsDataByTaskStatusAndHandleUserCode(taskStatus, userCode);
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

    public Boolean checkTaskInsExtInfo(String taskInsCode) {
        List<TaskInsExtData> taskInsExtDatas = taskInsExtDataCurd.findByTaskInsCode(taskInsCode);
        return CollectionUtils.isEmpty(taskInsExtDatas);
    }

    @Override
    public List<JobView> findJobViewsByStarByMe(String userCode) {
        List<TaskInsData> taskInsDatas = taskInsDataCurd.findTaskInsDataByTaskStatusAndHandleUserCode("Begin",userCode);
        List<TaskInsData> filteData = taskInsDatas.stream().filter(e ->
            !checkTaskInsExtInfo(e.getTaskInsCode())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(filteData)) {
            return changeTaskInsToJobData(filteData);
        }
        return new ArrayList<>();

    }

    @Override
    public Boolean checkTaskInsInfoAndUser(String taskInsCode, String userCode) {
        TaskInsData taskInsData = taskInsDataCurd.findTaskInsDataByTaskInsCode(taskInsCode);
        return taskInsData.getHandleUserCode().equals(userCode);
    }

    @Override
    @Transactional
    public Boolean deleteTaskInsExtInfo(String taskInsCode) {
        return taskInsExtDataCurd.deleteByTaskInsCode(taskInsCode) > 0;
    }

    @Override
    public List<JobView> findJobViewsByUserId(String userCode) {
        List<TaskInsData> jobDatas = taskInsDataCurd.findTaskInsDataByHandleUserCode(userCode);
        return changeTaskInsToJobData(jobDatas);
    }

    @Override
    public List<JobView> findJobViewsByWaitMe(String userCode) {
        List<TaskInsData> taskInsDatas = taskInsDataCurd.findTaskInsDataByTaskStatusAndHandleUserCode("Begin",userCode);
        return changeTaskInsToJobData(taskInsDatas);
    }

    private List<JobView> changeTaskInsToJobData(List<TaskInsData> taskInsDatas) {
        if(CollectionUtils.isEmpty(taskInsDatas)) {
            return null;
        }
        return taskInsDatas.stream().map(e -> {
            JobData jobData = jobDataCurd.findByJobCode(e.getJobCode());
            JobView jobView = new JobView();
            BeanUtils.copyProperties(jobData, jobView);
            jobView.setTaskInsCode(e.getTaskInsCode());
            jobView.setTaskName(e.getTaskName());
            return jobView;
        }).collect(Collectors.toList());

    }

    @Override
    public TaskInsView findTaskInsByCode(String taskInsCode) {
        TaskInsData taskInsData = taskInsDataCurd.findTaskInsDataByTaskInsCode(taskInsCode);
        TaskInsView taskInsView = new TaskInsView();
        BeanUtils.copyProperties(taskInsData, taskInsView);
        List<TaskInsExtData> taskInsExtDatas = taskInsExtDataCurd.findByTaskInsCode(taskInsCode);
        taskInsView.setStar(!CollectionUtils.isEmpty(taskInsExtDatas));
        return taskInsView;
    }

    @Override
    public Boolean editTaskInsByCode(String taskInsCode, String taskData) {
        TaskInsData taskInsData = taskInsDataCurd.findTaskInsDataByTaskInsCode(taskInsCode);
        if("Finish".equals(taskInsData.getTaskStatus())) {
            return false;
        }
        boolean checkValue = checkTaskData(taskData, taskInsData.getTaskTplCode());
        taskInsData.setTaskData(taskData);
        return taskInsDataCurd.save(taskInsData) != null;
    }

    private boolean checkTaskData(String taskData, String taskTplCode) {
        return false;
    }


    private void createTaskInsByTpl(TaskTplData taskTplData, String jobCode) {
        TaskInsData taskInsData = new TaskInsData();
        taskInsData.setTaskTplCode(taskTplData.getTaskCode());
        taskInsData.setTaskStatus("Begin");
        taskInsData.setTaskInsCode(UUID.randomUUID().toString());
        taskInsData.setNextTaskTplCode(taskTplData.getNextTaskTplCode());
        taskInsData.setJobCode(jobCode);
        taskInsData.setHandleUserCode(taskTplData.getReceiverUserId());
        taskInsData.setTaskName(taskTplData.getTaskName());
        List<TaskTplDescMetaData> taskTplDescMetaDatas = taskTplDescMetaDataCurd.findTaskDescMetaDataByTaskCode(taskTplData.getTaskCode());
        if(!CollectionUtils.isEmpty(taskTplDescMetaDatas)) {
            Map<String,String> params = new HashMap<>();
            taskTplDescMetaDatas.forEach(e -> {
                params.put(e.getMetaName(), "");
            });
            taskInsData.setTaskData(JSON.toJSONString(params));

        }
        taskInsDataCurd.save(taskInsData);

        if(!StringUtils.isEmpty(taskTplData.getDependTaskTplCode())) {
            List<String> dependTaskTplCodes = JSON.parseArray(taskTplData.getDependTaskTplCode(),String.class);
            if(!CollectionUtils.isEmpty(dependTaskTplCodes)) {
                dependTaskTplCodes.forEach(e -> {
                    TaskTplData depTaskTpl = taskTplDataCurd.findTaskTplDataByTaskCode(e);
                    createTaskInsByTpl(depTaskTpl, jobCode);
                });
            }
        }

    }

}
