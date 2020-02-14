package com.factory.task.data.attence.curd;

import com.factory.task.data.attence.AttendanceRecordData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendanceRecordDataCurd extends CrudRepository<AttendanceRecordData, String> {
}
