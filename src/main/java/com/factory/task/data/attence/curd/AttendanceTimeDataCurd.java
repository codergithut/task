package com.factory.task.data.attence.curd;

import com.factory.task.data.attence.AttendanceTimeData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendanceTimeDataCurd extends CrudRepository<AttendanceTimeData, String> {
    void deleteByattendanceDailyPlanCode(String attendanceDailyPlanCode);

    List<AttendanceTimeData> findByAttendanceDailyPlanCode(String attendanceDailyPlanCode);
}
