package com.factory.task.model.attence;

import java.util.Date;

/**
 * Created by tianjian on 2020/2/14.
 */
public class AttendancePersonHistoryView {

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 考勤异常检查编号
     */
    private String attendanceTimeCode;

    /**
     * 考情异常
     */
    private String exception;

    /**
     * 异常时间
     */
    private Date historyDate;

    /**
     * 考情记录json字符串
     */
    private String attendanceRecordDesc;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public String getAttendanceTimeCode() {
        return attendanceTimeCode;
    }

    public void setAttendanceTimeCode(String attendanceTimeCode) {
        this.attendanceTimeCode = attendanceTimeCode;
    }

    public String getAttendanceRecordDesc() {
        return attendanceRecordDesc;
    }

    public void setAttendanceRecordDesc(String attendanceRecordDesc) {
        this.attendanceRecordDesc = attendanceRecordDesc;
    }
}
