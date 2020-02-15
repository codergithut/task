package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.job.StatisticsAttendanceJob;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.attence.AttendanceDailyPlanView;
import com.factory.task.model.attence.AttendancePersonHistoryView;
import com.factory.task.model.attence.AttendanceTimeView;
import com.factory.task.model.task.TaskInsExtView;
import com.factory.task.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by tianjian on 2020/2/14.
 */
@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 上传考勤文件到系统
     * @return
     */
    @PostMapping("/upload")
    public RestModelTemplate<Boolean> uploadFile(@RequestBody TaskInsExtView taskInsExt) {
        return new RestModelTemplate().Success("OK");
    }

    /**
     * 根据时间区间获取某个人的打卡记录
     * @param beginDate
     * @param endDate
     * @return
     */
    @GetMapping("/listPerson")
    public RestModelTemplate<List<AttendancePersonHistoryView>> getAttendancePersonHistory(@RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate) {
        return new RestModelTemplate<>().Success(attendanceService.findAttendancePersonHistory(beginDate, endDate));
    }

    /**
     * 添加部门考勤计划
     * @param attendanceDailyPlanView
     * @return
     */
    @PostMapping("/attendancePlan")
    public RestModelTemplate<Boolean> addAttendancePlan(@RequestBody AttendanceDailyPlanView attendanceDailyPlanView) {
        return new RestModelTemplate<>().Success(attendanceService.addAttendancePlan(attendanceDailyPlanView));
    }

    /**
     * 获取考勤计划表
     * @param departmentCode
     * @return
     */
    @GetMapping("/getAttendancePlan")
    public RestModelTemplate<AttendanceDailyPlanView> getAttendancePlan(@RequestParam("departmentCode") String departmentCode) {
        return new RestModelTemplate<>().Success(attendanceService.getAttendancePlan(departmentCode));
    }

    /**
     * 编辑考勤计划表
     * @param attendanceDailyPlanView
     * @return
     */
    @PostMapping("/editAttendancePlan")
    public RestModelTemplate<Boolean> editAttendancePlan(@RequestBody AttendanceDailyPlanView attendanceDailyPlanView) {
        return new RestModelTemplate<>().Success(attendanceService.editAttendAancePlan(attendanceDailyPlanView));
    }

    @Autowired
    private StatisticsAttendanceJob statisticsAttendanceJob;
    @GetMapping("/test")
    public void ss() {
        statisticsAttendanceJob.StatisticsAttendance("eaac805f-bdef-42c4-9f9f-0216d4ca6535");
    }



}
