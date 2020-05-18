package com.factory.task.model.attence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by tianjian on 2020/2/14.
 */
@ApiModel
public class AttendanceDailyPlanView {

    /**
     * 考勤计划编号
     */
    @ApiModelProperty("考勤计划编号")
    private String attendanceDailyPlanCode;

    /**
     * 考勤部门编号
     */
    @ApiModelProperty("考勤部门编号")
    private String departmentCode;

    /**
     * 时间区间
     */
    @ApiModelProperty("时间区间")
    List<AttendanceTimeView> attendanceTimeViews;

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

    public List<AttendanceTimeView> getAttendanceTimeViews() {
        return attendanceTimeViews;
    }

    public void setAttendanceTimeViews(List<AttendanceTimeView> attendanceTimeViews) {
        this.attendanceTimeViews = attendanceTimeViews;
    }
}
