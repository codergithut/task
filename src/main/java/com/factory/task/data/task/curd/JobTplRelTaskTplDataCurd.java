package com.factory.task.data.task.curd;

import com.factory.task.data.task.JobTplRelTaskTplData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/5/17.
 */
public interface JobTplRelTaskTplDataCurd extends CrudRepository<JobTplRelTaskTplData, String> {
    JobTplRelTaskTplData findByJobTemplateCodeAndOrder(String jobTemplateCode, int initOrder);

    JobTplRelTaskTplData findByTaskCodeAndJobTemplateCode(String taskTplCode, String jobTemplateCode);
}
