package com.factory.task.data.attence.curd;

import com.factory.task.data.attence.AttendanceRecordData;
import com.factory.task.data.attence.AttendanceTimeData;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendanceRecordDataCurd extends CrudRepository<AttendanceRecordData, String> {
    List<AttendanceRecordData> findByAttendanceTimeAfterAndAttendanceTimeBeforeAndUserCode(Date today_start, Date today_end, String userCode);
}
