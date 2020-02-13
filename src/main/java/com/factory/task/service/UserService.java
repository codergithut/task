package com.factory.task.service;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UserService {

    List<RoleInfo> getAllRoleInfo();

    Boolean createUser(UserInfo userInfo);

    Boolean createRole(RoleInfo roleInfo);

    List<ResourceInfo> getAllResourceInfo();

    Boolean createResource(ResourceInfo resourceInfo);

    List<UriInfo> getAllUriInfo();

    Boolean createUri(UriInfo uriInfo);

    List<String> findUserUriByUserName(String userName);

    UserInfoData findUserByNameAndPassWord(String userName, String passWord);

    List<UserInfo> findAll();

    List<UserInfoData> findUserInfoDataByDepartmentCode(String departmentCode);

    Boolean updateUserInfoDatas(List<UserInfoData> userInfoDatas);
}
