package com.factory.task.data.task.curd;

import com.factory.task.data.task.TaskTplData;
import com.factory.task.data.task.TaskTplDescMetaData;
import com.factory.task.model.task.TaskTplDescMetaView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/13.
 */
public interface TaskTplDataCurd  extends CrudRepository<TaskTplData,String> {
    
    List<TaskTplData> findTaskTplDataByIsParent(boolean isParent);

    TaskTplData findTaskTplDataByTaskCode(String taskCode);

    List<TaskTplDescMetaData> findTaskDescMetaDataByTaskCode(String taskCode);
}
