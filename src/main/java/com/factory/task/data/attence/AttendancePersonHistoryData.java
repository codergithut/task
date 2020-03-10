package com.factory.task.data.attence;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/2/14.
 */
@Entity
public class AttendancePersonHistoryData {

    @Id
    private String attendancePersonHistoryCode;

    /**
     * 用户编号
     */
    private String userCode;

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

    public String getAttendanceRecordDesc() {
        return attendanceRecordDesc;
    }

    public void setAttendanceRecordDesc(String attendanceRecordDesc) {
        this.attendanceRecordDesc = attendanceRecordDesc;
    }

    public String getAttendancePersonHistoryCode() {
        return attendancePersonHistoryCode;
    }

    public void setAttendancePersonHistoryCode(String attendancePersonHistoryCode) {
        this.attendancePersonHistoryCode = attendancePersonHistoryCode;
    }
}
