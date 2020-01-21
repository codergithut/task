package com.factory.task.service.impl;

import com.factory.task.data.user.ResourceInfoData;
import com.factory.task.data.user.RoleInfoData;
import com.factory.task.data.user.UriInfoData;
import com.factory.task.data.user.UserInfoData;
import com.factory.task.data.user.curd.ResourceInfoDataCurd;
import com.factory.task.data.user.curd.RoleInfoDataCurd;
import com.factory.task.data.user.curd.UriInfoDataCurd;
import com.factory.task.data.user.curd.UserInfoDataCurd;
import com.factory.task.data.user.curd.relation.ResourceAndUriRelationDataCurd;
import com.factory.task.data.user.curd.relation.RoleAndResourceRelationDataCurd;
import com.factory.task.data.user.curd.relation.UserAndRoleRelationDataCurd;
import com.factory.task.data.user.relation.ResourceAndUriRelationData;
import com.factory.task.data.user.relation.RoleAndResourceRelationData;
import com.factory.task.data.user.relation.UserAndRoleRelationData;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    private ResourceInfoDataCurd resourceInfoDataCurd;

    @Autowired
    private UriInfoDataCurd uriInfoDataCurd;

    @Autowired
    private UserAndRoleRelationDataCurd userAndRoleRelationDataCurd;

    @Autowired
    private RoleAndResourceRelationDataCurd roleAndResourceRelationDataCurd;

    @Autowired
    private ResourceAndUriRelationDataCurd resourceAndUriRelationDataCurd;

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
        if(CollectionUtils.isEmpty(roleInfo.getResourceCodes())) {
            return false;
        }
        RoleInfoData roleInfoData = new RoleInfoData();
        BeanUtils.copyProperties(roleInfo, roleInfoData);
        roleInfoData.setRoleCode(UUID.randomUUID().toString());

        List<RoleAndResourceRelationData> relationDatas = new ArrayList<>();

        roleInfo.getResourceCodes().forEach(e -> {
            RoleAndResourceRelationData resourceRelationData = new RoleAndResourceRelationData();
            resourceRelationData.setCode(UUID.randomUUID().toString());
            resourceRelationData.setRoleCode(roleInfoData.getRoleCode());
            resourceRelationData.setResourceId(e);
            relationDatas.add(resourceRelationData);
        });

        roleInfoDataCurd.save(roleInfoData);
        roleAndResourceRelationDataCurd.saveAll(relationDatas);
        return true;
    }

    @Override
    public List<ResourceInfo> getAllResourceInfo() {
        List<ResourceInfoData> resourceInfos = (List) resourceInfoDataCurd.findAll();
        if(CollectionUtils.isEmpty(resourceInfos)) {
            return null;
        }
        return resourceInfos.stream().map(e -> {
            ResourceInfo resourceInfo = new ResourceInfo();
            BeanUtils.copyProperties(e, resourceInfo);
            return resourceInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean createResource(ResourceInfo resourceInfo) {
        if(CollectionUtils.isEmpty(resourceInfo.getUriCodes())) {
            return false;
        }
        List<ResourceAndUriRelationData> relationDatas = new ArrayList<>();
        ResourceInfoData resourceInfoData = new ResourceInfoData();
        BeanUtils.copyProperties(resourceInfo, resourceInfoData);
        resourceInfoData.setResourceCode(UUID.randomUUID().toString());
        resourceInfo.getUriCodes().forEach(e -> {
            ResourceAndUriRelationData resourceAndUriRelationData = new ResourceAndUriRelationData();
            resourceAndUriRelationData.setCode(UUID.randomUUID().toString());
            resourceAndUriRelationData.setUriCode(e);
            resourceAndUriRelationData.setResourceCode(resourceInfoData.getResourceCode());
            relationDatas.add(resourceAndUriRelationData);
        });
        resourceAndUriRelationDataCurd.saveAll(relationDatas);
        resourceInfoDataCurd.save(resourceInfoData);
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

}