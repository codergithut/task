package com.factory.task.data.user.curd;

import com.factory.task.data.user.UserInfoData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UserInfoDataCurd extends CrudRepository<UserInfoData,String> {

    UserInfoData findByUserNameAndPassWord(String userName, String passWord);

    List<UserInfoData> findByDepartmentCode(String departmentCode);

    UserInfoData findByUserCode(String userCode);
}
