package com.factory.task.service.impl;

import com.factory.task.data.task.JobTemplateData;
import com.factory.task.data.task.JobTplRelTaskTplData;
import com.factory.task.data.task.curd.JobTemplateDataCurd;
import com.factory.task.data.task.curd.JobTplRelTaskTplDataCurd;
import com.factory.task.model.task.TaskHandleTemplateView;
import com.factory.task.service.JobTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/5/17.
 */
@Service
public class JobTemplateServiceImpl implements JobTemplateService {

    @Autowired
    private JobTemplateDataCurd jobTemplateDataCurd;

    @Autowired
    private JobTplRelTaskTplDataCurd jobTplRelTaskTplDataCurd;

    @Override
    public String saveJobTemplateData(String jobTplName) {
        List<JobTemplateData> jobTemplateDatas = jobTemplateDataCurd.findByJobTemplateName(jobTplName);
        if(!CollectionUtils.isEmpty(jobTemplateDatas)) {
            return null;
        }
        String jobTemplateId = UUID.randomUUID().toString();
        JobTemplateData jobTemplateData = new JobTemplateData();
        jobTemplateData.setJobTemplateCode(jobTemplateId);
        jobTemplateData.setJobTemplateName(jobTplName);
        jobTemplateDataCurd.save(jobTemplateData);
        return jobTemplateId;
    }

    @Override
    public Boolean saveTaskTplDetail(String jobTemplateId, List<TaskHandleTemplateView> taskHandleTemplateViews) {
        int order = 0;
        int size = taskHandleTemplateViews.size();
        for(TaskHandleTemplateView task : taskHandleTemplateViews) {
            String taskCode = task.getTaskCode();
            String handleUserCode = task.getDependUserCode();
            JobTplRelTaskTplData jobTplRelTaskTplData = new JobTplRelTaskTplData();
            jobTplRelTaskTplData.setJobTemplateCode(jobTemplateId);
            jobTplRelTaskTplData.setOrder(++order);
            jobTplRelTaskTplData.setUserCode(handleUserCode);
            jobTplRelTaskTplData.setTaskCode(taskCode);
            if(order == size) {
                jobTplRelTaskTplData.setHasNext("false");
            } else {
                jobTplRelTaskTplData.setHasNext("true");
            }
            jobTplRelTaskTplDataCurd.save(jobTplRelTaskTplData);
        }
        return true;
    }

    @Override
    public List<JobTemplateData> findAllJobTempData() {
        return (List<JobTemplateData>) jobTemplateDataCurd.findAll();
    }
}
