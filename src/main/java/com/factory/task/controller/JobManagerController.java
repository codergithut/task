package com.factory.task.controller;

import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.JobView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.model.task.TaskInsView;
import com.factory.task.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/1/11.
 */
@RestController
@RequestMapping("/jobManager")
@CrossOrigin
public class JobManagerController {

    @Autowired
    private JobService jobService;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/createJob")
    public RestModelTemplate<Boolean> createJob(@RequestBody JobView jobView) {
        jobView.setJobCode(UUID.randomUUID().toString());
        jobView.setIsFinished("begin");
        return new RestModelTemplate<Boolean>().Success(jobService.createJob(jobView));
    }

    /**
     * 创建任务 生成所有模版任务并初始化值
     * @return
     */
    @GetMapping("/startJob")
    public RestModelTemplate startFactoryJobtpl(@RequestParam("jobCode") String jobCode) {
        return new RestModelTemplate<>().Success(jobService.startJob(jobCode));
    }

    //lsksd11111
    @GetMapping("/finishTaskIns")
    public RestModelTemplate<Boolean> finishTaskIns(@RequestParam("taskInsCode") String taskInsCode) {
        return new RestModelTemplate<>().Success(jobService.finishTaskIns(taskInsCode));
    }


    //docker run -it -p 8081:8080 --name task tianjian3209/task:jenkins
    @GetMapping("/getTaskInsByStatus")
    public RestModelTemplate<List<TaskInsView>> getTaskInsByStatusAndJobCode(@RequestParam("taskStatus") String taskStatus) {
        // 根据taskStatus获取所有服务实例 比如获取所有服务状态未start 提供给页面，如果是start可以点击完成，否则就是没啥操作
        return new RestModelTemplate<>().Success(jobService.findTaskInsByStatus(taskStatus));
    }


    @PostMapping("/addTaskInsExt")
    public RestModelTemplate<Boolean> attentionTaskInsCode(@RequestBody TaskInsExtView taskInsExt) {
        taskInsExt.setTaskInsExtCode(UUID.randomUUID().toString());
        return new RestModelTemplate().Success(jobService.addTaskInsExtInfo(taskInsExt));
    }

    @GetMapping("/getJobList")
    public RestModelTemplate<JobView> getJobViews(@RequestParam("jobType") String jobType) {
        if(jobType.equals("1")) {
            return new RestModelTemplate<>().Success(jobService.findJobViewsByUserId(null));
        } else {
            return new RestModelTemplate<>().Success(jobService.findJobViewsByWaitMe(null));
        }
    }

}
