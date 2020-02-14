package com.factory.task.data.attence.curd;

import com.factory.task.data.attence.AttendancePersonHistoryData;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendancePersonHistoryDataCurd extends CrudRepository<AttendancePersonHistoryData,String> {
    List<AttendancePersonHistoryData> findByHistoryDateAfterAndHistoryDateBefore(Date beginDate, Date endDate);
}
