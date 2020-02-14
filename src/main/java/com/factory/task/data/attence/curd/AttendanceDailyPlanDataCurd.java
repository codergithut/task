package com.factory.task.data.attence.curd;
import com.factory.task.data.attence.*;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by tianjian on 2020/2/14.
 */
public interface AttendanceDailyPlanDataCurd extends CrudRepository<AttendanceDailyPlanData,String> {
    AttendanceDailyPlanData findByDepartmentCode(String departmentCode);
}
