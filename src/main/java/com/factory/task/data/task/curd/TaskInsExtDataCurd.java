package com.factory.task.data.task.curd;

import com.factory.task.data.task.TaskInsExtData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/13.
 */
public interface TaskInsExtDataCurd extends CrudRepository<TaskInsExtData,String> {

    List<TaskInsExtData> findByTaskInsCode(String taskInsCode);
}
