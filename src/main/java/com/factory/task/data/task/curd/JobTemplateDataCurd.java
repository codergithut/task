package com.factory.task.data.task.curd;

import com.factory.task.data.task.JobTemplateData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/5/17.
 */
public interface JobTemplateDataCurd extends CrudRepository<JobTemplateData, String> {
    List<JobTemplateData> findByJobTemplateName(String jobName);
}
