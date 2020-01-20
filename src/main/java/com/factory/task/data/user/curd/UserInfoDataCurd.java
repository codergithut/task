package com.factory.task.data.user.curd;

import com.factory.task.data.user.UserInfoData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UserInfoDataCurd extends CrudRepository<UserInfoData,String> {

    UserInfoData findByUserNameAndPassWord(String userName, String passWord);

}
