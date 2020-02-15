package com.factory.task.service;

import com.factory.task.data.attence.AttendancePersonHistoryData;
import com.factory.task.data.attence.AttendanceRecordData;
import com.factory.task.model.attence.AttendanceDailyPlanView;
import com.factory.task.model.attence.AttendancePersonHistoryView;

import java.util.Date;
import java.util.List;

/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendanceService {

    List<AttendancePersonHistoryView> findAttendancePersonHistory(Date beginDate, Date endDate);

    Boolean saveAttendancePersonHistory(AttendancePersonHistoryData attendancePersonHistoryData);

    Boolean addAttendancePlan(AttendanceDailyPlanView attendanceDailyPlanView);

    AttendanceDailyPlanView getAttendancePlan(String departmentCode);

    Boolean editAttendAancePlan(AttendanceDailyPlanView attendanceDailyPlanView);

    List<AttendanceRecordData> findAttendanceRecordByDateAndUserCode(Date today_start, Date today_end, String userCode);
}
