package com.factory.task.model.attence;

import java.util.Date;

/**
 * Created by tianjian on 2020/2/14.
 */
public class AttendanceTimeView {

    /**
     * 考情计划编号
     */
    private String attendanceDailyPlanCode;

    /**
     * 考情计划编号
     */
    private String attendanceTimeCode;

    /**
     * 考勤名称
     */
    private String attendanceName;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 早退 迟到 正常
     */
    private String type;


    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttendanceDailyPlanCode() {
        return attendanceDailyPlanCode;
    }

    public void setAttendanceDailyPlanCode(String attendanceDailyPlanCode) {
        this.attendanceDailyPlanCode = attendanceDailyPlanCode;
    }

    public String getAttendanceTimeCode() {
        return attendanceTimeCode;
    }

    public void setAttendanceTimeCode(String attendanceTimeCode) {
        this.attendanceTimeCode = attendanceTimeCode;
    }

    public String getAttendanceName() {
        return attendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName;
    }
}
