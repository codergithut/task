package com.factory.task.service.impl;

import com.factory.task.data.user.RoleInfoData;
import com.factory.task.data.user.UriInfoData;
import com.factory.task.data.user.UserInfoData;
import com.factory.task.data.user.curd.RoleInfoDataCurd;
import com.factory.task.data.user.curd.UriInfoDataCurd;
import com.factory.task.data.user.curd.UserInfoDataCurd;
import com.factory.task.data.user.curd.relation.RoleAndUriRelationDataCurd;
import com.factory.task.data.user.curd.relation.UserAndRoleRelationDataCurd;
import com.factory.task.data.user.relation.RoleAndUriRelationData;
import com.factory.task.data.user.relation.UserAndRoleRelationData;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2020/1/15.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private RoleInfoDataCurd roleInfoDataCurd;

    @Autowired
    private UserInfoDataCurd userInfoDataCurd;

    @Autowired
    private UriInfoDataCurd uriInfoDataCurd;

    @Autowired
    private UserAndRoleRelationDataCurd userAndRoleRelationDataCurd;

    @Autowired
    private RoleAndUriRelationDataCurd roleAndUriRelationDataCurd;

    @Override
    public List<RoleInfo> getAllRoleInfo() {
        List<RoleInfoData> roleInfos = (List<RoleInfoData>) roleInfoDataCurd.findAll();
        if(CollectionUtils.isEmpty(roleInfos)) {
            return null;
        }
        return roleInfos.stream().map(e -> {
            RoleInfo roleInfo = new RoleInfo();
            BeanUtils.copyProperties(e, roleInfo);
            return roleInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean createUser(UserInfo userInfo) {

        if(CollectionUtils.isEmpty(userInfo.getRoleCodes())) {
            return false;
        }

        UserInfoData userInfoData = new UserInfoData();
        List<UserAndRoleRelationData> relationDatas = new ArrayList<>();
        BeanUtils.copyProperties(userInfo, userInfoData);
        userInfoData.setUserCode(UUID.randomUUID().toString());

        userInfo.getRoleCodes().forEach(e -> {
            UserAndRoleRelationData userAndRoleRelationData = new UserAndRoleRelationData();
            userAndRoleRelationData.setCode(UUID.randomUUID().toString());
            userAndRoleRelationData.setUserCode(userInfoData.getUserCode());
            userAndRoleRelationData.setRoleCode(e);
            relationDatas.add(userAndRoleRelationData);

        });

        userInfoDataCurd.save(userInfoData);
        userAndRoleRelationDataCurd.saveAll(relationDatas);
        return true;
    }

    @Override
    public Boolean createRole(RoleInfo roleInfo) {
        if(CollectionUtils.isEmpty(roleInfo.getUriCodes())) {
            return false;
        }
        RoleInfoData roleInfoData = new RoleInfoData();
        BeanUtils.copyProperties(roleInfo, roleInfoData);
        roleInfoData.setRoleCode(UUID.randomUUID().toString());

        List<RoleAndUriRelationData> relationDatas = new ArrayList<>();

        roleInfo.getUriCodes().forEach(e -> {
            RoleAndUriRelationData resourceRelationData = new RoleAndUriRelationData();
            resourceRelationData.setCode(UUID.randomUUID().toString());
            resourceRelationData.setRoleCode(roleInfoData.getRoleCode());
            resourceRelationData.setUriId(e);
            relationDatas.add(resourceRelationData);
        });

        roleInfoDataCurd.save(roleInfoData);
        roleAndUriRelationDataCurd.saveAll(relationDatas);
        return true;
    }

    @Override
    public List<UriInfo> getAllUriInfo() {
        List<UriInfoData> uriInfoDatas = (List)uriInfoDataCurd.findAll();
        if(CollectionUtils.isEmpty(uriInfoDatas)) {
            return null;
        }
        return uriInfoDatas.stream().map(e -> {
            UriInfo uriInfo = new UriInfo();
            BeanUtils.copyProperties(e, uriInfo);
            return uriInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean createUri(UriInfo uriInfo) {
        UriInfoData uriInfoData = new UriInfoData();
        BeanUtils.copyProperties(uriInfo, uriInfoData);
        uriInfoData.setUriCode(UUID.randomUUID().toString());
        uriInfoDataCurd.save(uriInfoData);
        return true;
    }

    @Override
    public List<String> findUserUriByUserName(String userName) {
        return null;
    }

    @Override
    public UserInfoData findUserByNameAndPassWord(String userName, String passWord) {
        UserInfoData userInfoData = userInfoDataCurd.findByUserNameAndPassWord(userName, passWord);
        return userInfoData;
    }

    @Override
    public List<Map<String,String>> findAll() {
        List<UserInfoData> userInfoDatas = (List) userInfoDataCurd.findAll();
        if(userInfoDatas.isEmpty()) {
            return null;
        }
        return userInfoDatas.stream().map(e ->  {
            Map<String,String> data = new HashMap<>();
            data.put("userCode", e.getUserCode());
            data.put("userName", e.getUserName());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserInfoData> findUserInfoDataByDepartmentCode(String departmentCode) {
        return userInfoDataCurd.findByDepartmentCode(departmentCode);
    }

    @Override
    public Boolean updateUserInfoDatas(List<UserInfoData> userInfoDatas) {
        return userInfoDataCurd.saveAll(userInfoDatas) != null;
    }

    @Override
    public UserInfoData findUserByUserCode(String userCode) {
        UserInfoData userInfoData = userInfoDataCurd.findByUserCode(userCode);
        return userInfoData;
    }

    @Override
    public List<String> findUserUriByUserCode(String userCode) {
        List<String> uris = new ArrayList<>();
        List<UserAndRoleRelationData> roles = userAndRoleRelationDataCurd.findByUserCode(userCode);
        if(CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }

        roles.forEach(e -> {
            List<RoleAndUriRelationData> roleAndUriRelationDatas = roleAndUriRelationDataCurd.findByRoleCode(e.getRoleCode());
            roleAndUriRelationDatas.forEach(ee -> {
                UriInfoData uriInfoData = uriInfoDataCurd.findById(ee.getUriId()).get();
                uris.add(uriInfoData.getUriPath());
            });
        });
        return uris;
    }

}