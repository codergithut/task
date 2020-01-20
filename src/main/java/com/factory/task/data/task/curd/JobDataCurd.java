package com.factory.task.data.task.curd;

import com.factory.task.data.task.JobData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/1/13.
 */
public interface JobDataCurd extends CrudRepository<JobData,String> {
    JobData findByJobCode(String jobCode);
}
