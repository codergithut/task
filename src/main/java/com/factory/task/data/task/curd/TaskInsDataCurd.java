package com.factory.task.data.task.curd;

import com.factory.task.data.task.TaskInsData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/13.
 */
public interface TaskInsDataCurd extends CrudRepository<TaskInsData,String> {

    TaskInsData findTaskInsDataByTaskInsCode(String taskInsCode);

    TaskInsData findTaskInsDataByTaskTplCodeAndJobCode(String taskTplCode, String jobCode);

    List<TaskInsData> findTaskInsDataByTaskStatusAndUserCode(String taskStatus,String userCode);
}
