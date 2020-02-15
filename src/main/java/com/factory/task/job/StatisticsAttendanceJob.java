package com.factory.task.job;

import com.alibaba.fastjson.JSON;
import com.factory.task.data.attence.AttendancePersonHistoryData;
import com.factory.task.data.attence.AttendanceRecordData;
import com.factory.task.data.attence.AttendanceTimeData;
import com.factory.task.data.user.UserInfoData;
import com.factory.task.model.attence.AttendanceDailyPlanView;
import com.factory.task.model.attence.AttendancePersonHistoryView;
import com.factory.task.model.attence.AttendanceTimeView;
import com.factory.task.service.AttendanceService;
import com.factory.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/2/15.
 */
@Service
public class StatisticsAttendanceJob {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    public void StatisticsAttendance(String departmentCode){
        AttendanceDailyPlanView attendanceDailyPlanView = attendanceService.getAttendancePlan(departmentCode);
        List<AttendanceTimeView> attendanceTimeViews = attendanceDailyPlanView.getAttendanceTimeViews();
        List<UserInfoData> userInfoDatas = userService.findUserInfoDataByDepartmentCode(departmentCode);
        Date today_start = fromDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        userInfoDatas.forEach(e -> {
            AttendancePersonHistoryData attendancePersonHistoryData = new AttendancePersonHistoryData();
            attendancePersonHistoryData.setHistoryDate(today_start);
            attendancePersonHistoryData.setAttendancePersonHistoryCode(UUID.randomUUID().toString());
            attendancePersonHistoryData.setUserCode(e.getUserCode());
            List<AttendanceException> attendanceExceptions = new ArrayList<>();
            attendanceTimeViews.forEach(ee -> {
                 List<AttendanceRecordData> attendanceRecordDatas = attendanceService
                        .findAttendanceRecordByDateAndUserCode(ee.getBeginTime(), ee.getEndTime(), e.getUserCode());
                if(CollectionUtils.isEmpty(attendanceRecordDatas)) {
                    AttendanceException attendanceException = new AttendanceException();
                    attendanceException.setException("EXCEPTION");
                    attendanceException.setAttendanceName(ee.getAttendanceName());
                    attendanceExceptions.add(attendanceException);
                }
                if(!CollectionUtils.isEmpty(attendanceExceptions)) {
                    attendancePersonHistoryData.setException("EXCEPTON");
                    attendancePersonHistoryData.setAttendanceRecordDesc(JSON.toJSONString(attendanceExceptions));
                }
            });
            attendanceService.saveAttendancePersonHistory(attendancePersonHistoryData);

        });
    }


    private Date fromDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

}
