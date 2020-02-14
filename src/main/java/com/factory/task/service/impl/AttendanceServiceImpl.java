package com.factory.task.service.impl;

import com.factory.task.data.attence.AttendanceDailyPlanData;
import com.factory.task.data.attence.AttendancePersonHistoryData;
import com.factory.task.data.attence.AttendanceTimeData;
import com.factory.task.data.attence.curd.AttendanceDailyPlanDataCurd;
import com.factory.task.data.attence.curd.AttendancePersonHistoryDataCurd;
import com.factory.task.data.attence.curd.AttendanceRecordDataCurd;
import com.factory.task.data.attence.curd.AttendanceTimeDataCurd;
import com.factory.task.model.attence.AttendanceDailyPlanView;
import com.factory.task.model.attence.AttendancePersonHistoryView;
import com.factory.task.model.attence.AttendanceTimeView;
import com.factory.task.service.AttendanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/2/14.
 */
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDailyPlanDataCurd attendanceDailyPlanDataCurd;

    @Autowired
    private AttendancePersonHistoryDataCurd attendancePersonHistoryDataCurd;

    @Autowired
    private AttendanceRecordDataCurd attendanceRecordDataCurd;

    @Autowired
    private AttendanceTimeDataCurd attendanceTimeDataCurd;

    @Override
    public List<AttendancePersonHistoryView> findAttendancePersonHistory(Date beginDate, Date endDate) {
        List<AttendancePersonHistoryData> attendancePersonHistoryDatas = attendancePersonHistoryDataCurd
                .findByHistoryDateAfterAndHistoryDateBefore(beginDate, endDate);
        if(CollectionUtils.isEmpty(attendancePersonHistoryDatas)) {
            return new ArrayList<>();
        }
        return attendancePersonHistoryDatas.stream().map(e -> {
            AttendancePersonHistoryView attendancePersonHistoryView = new AttendancePersonHistoryView();
            BeanUtils.copyProperties(e, attendancePersonHistoryView);
            return attendancePersonHistoryView;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean addAttendancePlan(AttendanceDailyPlanView attendanceDailyPlanView) {
        AttendanceDailyPlanData attendanceDailyPlanData = new AttendanceDailyPlanData();
        BeanUtils.copyProperties(attendanceDailyPlanView, attendanceDailyPlanData);
        attendanceDailyPlanData.setAttendanceDailyPlanCode(UUID.randomUUID().toString());
        List<AttendanceTimeView> attendanceTimeViews = attendanceDailyPlanView.getAttendanceTimeViews();
        attendanceTimeViews.forEach(e -> {
            AttendanceTimeData attendanceTimeData = new AttendanceTimeData();
            BeanUtils.copyProperties(e, attendanceTimeData);
            attendanceTimeDataCurd.save(attendanceTimeData);
        });
        return attendanceDailyPlanDataCurd.save(attendanceDailyPlanData) != null;
    }

    @Override
    public AttendanceDailyPlanView getAttendancePlan(String departmentCode) {
        AttendanceDailyPlanData attendanceDailyPlanData = attendanceDailyPlanDataCurd.findByDepartmentCode(departmentCode);
        if(attendanceDailyPlanData == null) {
            return new AttendanceDailyPlanView();
        }
        List<AttendanceTimeData> attendanceTimeDatas = attendanceTimeDataCurd
                .findByAttendanceDailyPlanCode(attendanceDailyPlanData.getAttendanceDailyPlanCode());
        AttendanceDailyPlanView attendanceDailyPlanView = new AttendanceDailyPlanView();
        attendanceDailyPlanView.setAttendanceDailyPlanCode(attendanceDailyPlanData.getAttendanceDailyPlanCode());
        attendanceDailyPlanView.setDepartmentCode(attendanceDailyPlanData.getDepartmentCode());
        if(!CollectionUtils.isEmpty(attendanceTimeDatas)) {
            List<AttendanceTimeView> attendanceTimeViews = attendanceTimeDatas.stream().map(e -> {
                AttendanceTimeView attendanceTimeView = new AttendanceTimeView();
                BeanUtils.copyProperties(e, attendanceTimeView);
                return attendanceTimeView;
            }).collect(Collectors.toList());
            attendanceDailyPlanView.setAttendanceTimeViews(attendanceTimeViews);
        }
        return attendanceDailyPlanView;
    }

    @Override
    public Boolean editAttendAancePlan(AttendanceDailyPlanView attendanceDailyPlanView) {
        AttendanceDailyPlanData attendanceDailyPlanData = new AttendanceDailyPlanData();
        BeanUtils.copyProperties(attendanceDailyPlanView, attendanceDailyPlanData);
        List<AttendanceTimeView> attendanceTimeViews = attendanceDailyPlanView.getAttendanceTimeViews();
        attendanceTimeDataCurd.deleteByattendanceDailyPlanCode(attendanceDailyPlanView.getAttendanceDailyPlanCode());
        attendanceTimeViews.forEach(e -> {
            AttendanceTimeData attendanceTimeData = new AttendanceTimeData();
            BeanUtils.copyProperties(e, attendanceTimeData);
            attendanceTimeDataCurd.save(attendanceTimeData);
        });
        return attendanceDailyPlanDataCurd.save(attendanceDailyPlanData) != null;
    }
}
