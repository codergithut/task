package com.factory.task.data.attence;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/2/14.
 */
@Entity
public class AttendanceRecordData {

    @Id
    private String attendanceRecordCode;

    private String userCode;

    private Date attendanceTime;

    private String attendanceType;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getAttendanceRecordCode() {
        return attendanceRecordCode;
    }

    public void setAttendanceRecordCode(String attendanceRecordCode) {
        this.attendanceRecordCode = attendanceRecordCode;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
