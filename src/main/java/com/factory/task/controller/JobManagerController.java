package com.factory.task.controller;

import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.JobView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.model.task.TaskInsView;
import com.factory.task.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.factory.task.config.ResponseCodeEnum.DEPEND_TASK_ERROR;
import static com.factory.task.util.RequestUtil.getUserCodeBySession;

/**
 * Created by tianjian on 2020/1/11.
 */
@RestController
@RequestMapping("/jobManager")
@CrossOrigin
public class JobManagerController {

    @Autowired
    private JobService jobService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/createJob")
    public RestModelTemplate<Boolean> createJob(@RequestBody JobView jobView) {
        jobView.setJobCode(UUID.randomUUID().toString());
        jobView.setIsFinished("Begin");
        jobService.createJob(jobView, getUserCodeBySession(request));
        jobService.startJob(jobView.getJobCode());
        return new RestModelTemplate<Boolean>().Success(true);
    }


    @GetMapping("/getTaskInsInfo")
    public RestModelTemplate<TaskInsView> getTaskInsInfo(@RequestParam("taskInsCode") String taskInsCode) {
        String userCode = getUserCodeBySession(request);
//        if(!jobService.checkTaskInsInfoAndUser(taskInsCode, userCode)) {
//            return new RestModelTemplate<>().Success(false);
//        }
        return new RestModelTemplate<>().Success(jobService.findTaskInsByCode(taskInsCode));
    }

    @GetMapping("/editTaskInsInfo")
    public RestModelTemplate<Boolean> editTaskInsInfo(@RequestParam("taskInsCode") String taskInsCode,
                                                      @RequestParam("taskData") String taskData) {
        String userCode = getUserCodeBySession(request);
//        if(!jobService.checkTaskInsInfoAndUser(taskInsCode, userCode)) {
//            return new RestModelTemplate<>().Success(false);
//        }
        return new RestModelTemplate<>().Success(jobService.editTaskInsByCode(taskInsCode,taskData));
    }

    @GetMapping("/finishTaskIns")
    public RestModelTemplate<Boolean> finishTaskIns(@RequestParam("taskInsCode") String taskInsCode) {
        String userCode = getUserCodeBySession(request);
//        if(!jobService.checkTaskInsInfoAndUser(taskInsCode, userCode)) {
//            return new RestModelTemplate<>().Success(false);
//        }
        if(jobService.finishTaskIns(taskInsCode)) {
            return new RestModelTemplate<>().Success(true);
        } else {
            return new RestModelTemplate<>().Fail(DEPEND_TASK_ERROR.getCode(), DEPEND_TASK_ERROR.getDesc());
        }
    }

    @GetMapping("/getTaskInsByStatus")
    public RestModelTemplate<List<TaskInsView>> getTaskInsByStatusAndJobCode(@RequestParam("taskStatus") String taskStatus) {
        // 根据taskStatus获取所有服务实例 比如获取所有服务状态未start 提供给页面，如果是start可以点击完成，否则就是没啥操作
        return new RestModelTemplate<>().Success(jobService.findTaskInsByStatus(taskStatus,getUserCodeBySession(request)));
    }


    @GetMapping("/addTaskInsExt")
    public RestModelTemplate<Boolean> attentionTaskInsCode(@RequestParam("taskInsCode") String taskInsCode) {
//        if(jobService.checkTaskInsExtInfo(taskInsCode)) {
//            return new RestModelTemplate<>().Success(false);
//        }
//        String userCode = getUserCodeBySession();
//        if(!jobService.checkTaskInsInfoAndUser(taskInsCode, userCode)) {
//            return new RestModelTemplate<>().Success(false);
//        }

        TaskInsExtView taskInsExt = new TaskInsExtView();
        taskInsExt.setTaskInsCode(taskInsCode);
        taskInsExt.setDate(new Date());
        taskInsExt.setTaskInsExtDesc("star task");
        taskInsExt.setType("star");
        taskInsExt.setTaskInsExtCode(UUID.randomUUID().toString());
        return new RestModelTemplate().Success(jobService.addTaskInsExtInfo(taskInsExt));
    }

    @GetMapping("/deleteTaskInsExt")
    public RestModelTemplate<Boolean> deleteTaskInsCode(@RequestParam("taskInsCode") String taskInsCode) {
        return new RestModelTemplate().Success(jobService.deleteTaskInsExtInfo(taskInsCode));
    }

    @GetMapping("/getJobList")
    public RestModelTemplate<JobView> getJobViews(@RequestParam("jobType") String jobType) {
        if(jobType.equals("1")) {
            return new RestModelTemplate<>().Success(jobService.findJobViewsByUserId(getUserCodeBySession(request)));
        } else if(jobType.equals("2")){
            return new RestModelTemplate<>().Success(jobService.findJobViewsByWaitMe(getUserCodeBySession(request)));
        } else if(jobType.equals("3")) {
            return new RestModelTemplate<>().Success(jobService.findJobViewsByStarByMe(getUserCodeBySession(request)));
        } else {
            return new RestModelTemplate<>().Success(null);
        }
    }

}
