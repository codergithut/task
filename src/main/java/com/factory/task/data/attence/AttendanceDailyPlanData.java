package com.factory.task.data.attence;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tianjian on 2020/2/14.
 */
@Entity
@Data
public class AttendanceDailyPlanData {

    /**
     * 考勤计划编号
     */
    @Id
    private String attendanceDailyPlanCode;

    /**
     * 考勤部门编号
     */
    private String departmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getAttendanceDailyPlanCode() {
        return attendanceDailyPlanCode;
    }

    public void setAttendanceDailyPlanCode(String attendanceDailyPlanCode) {
        this.attendanceDailyPlanCode = attendanceDailyPlanCode;
    }
}
