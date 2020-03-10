package com.factory.task.data.task.curd;

import com.factory.task.data.task.TaskTplDescMetaData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/2/11.
 */
public interface TaskTplDescMetaDataCurd extends CrudRepository<TaskTplDescMetaData,String> {
    List<TaskTplDescMetaData> findTaskDescMetaDataByTaskCode(String taskCode);
}
