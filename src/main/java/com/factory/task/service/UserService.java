package com.factory.task.service;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UserService {

    List<RoleInfo> getAllRoleInfo();

    Boolean createUser(UserInfo userInfo);

    Boolean createRole(RoleInfo roleInfo);

    List<UriInfo> getAllUriInfo();

    Boolean createUri(UriInfo uriInfo);

    List<String> findUserUriByUserName(String userName);

    UserInfoData findUserByNameAndPassWord(String userName, String passWord);

    List<Map<String,String>> findAll();

    List<UserInfoData> findUserInfoDataByDepartmentCode(String departmentCode);

    Boolean updateUserInfoDatas(List<UserInfoData> userInfoDatas);

    Map<String,String> findUserByUserCode(String userCode);

    List<String> findUserUriByUserCode(String userCode);
}
