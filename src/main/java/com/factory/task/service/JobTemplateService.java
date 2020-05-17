package com.factory.task.service;

import com.factory.task.data.task.JobTemplateData;
import com.factory.task.model.task.TaskHandleTemplateView;

import java.util.List;

/**
 * Created by tianjian on 2020/5/17.
 */
public interface JobTemplateService {
    
    String saveJobTemplateData(String jobTplName);

    Boolean saveTaskTplDetail(String jobTemplateId, List<TaskHandleTemplateView> taskHandleTemplateViews);

    List<JobTemplateData> findAllJobTempData();
}
