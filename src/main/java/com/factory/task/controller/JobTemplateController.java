package com.factory.task.controller;

import com.factory.task.data.task.JobTemplateData;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.JobTplView;
import com.factory.task.model.task.TaskHandleTemplateView;
import com.factory.task.service.JobTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/5/17.
 */
@RestController
@RequestMapping("/jobTplManage")
@CrossOrigin
public class JobTemplateController {

    @Autowired
    private JobTemplateService jobTemplateService;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/create")
    public RestModelTemplate<Boolean> createJobTpl(@RequestBody JobTplView jobTplView, HttpServletRequest request) {
        String jobTplName = jobTplView.getJobTplName();
        String jobTemplateId = jobTemplateService.saveJobTemplateData(jobTplName);
        if(jobTemplateId == null) {
            return new RestModelTemplate<>().Fail("1000001", "模版已经存在");
        }

        List<TaskHandleTemplateView> taskHandleTemplateViews = jobTplView.getTaskHandleTemplateViews();
        jobTemplateService.saveTaskTplDetail(jobTemplateId, taskHandleTemplateViews);
        return new RestModelTemplate<>().OK();
    }

    @GetMapping("/getAllJobTplInfo")
    public RestModelTemplate<List<JobTplView>> getAllJobDescInfo() {
        List<JobTemplateData> jobTemplateDatas = jobTemplateService.findAllJobTempData();
        if(CollectionUtils.isEmpty(jobTemplateDatas)) {
            return new RestModelTemplate<>().Fail("1000001", "查询数据为null");
        }
        List<JobTplView> jobTplViews = jobTemplateDatas.stream().map(e -> {
            JobTplView jobTplView = new JobTplView();
            jobTplView.setJobTplName(e.getJobTemplateName());
            jobTplView.setJobTplCode(e.getJobTemplateCode());
            return jobTplView;
        }).collect(Collectors.toList());
        return new RestModelTemplate<>().Success(jobTplViews);

    }


}
