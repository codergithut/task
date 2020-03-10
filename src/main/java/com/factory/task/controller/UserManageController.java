package com.factory.task.controller;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import com.factory.task.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/1/15.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthResource authResource;

    @GetMapping("/getUsers")
    public RestModelTemplate<List<UserInfo>> getUser() {
        return new RestModelTemplate<List<UserInfo>>().Success(userService.findAll());
    }

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/createUser")
    public RestModelTemplate<Boolean> createUser(@RequestBody UserInfo userInfo) {
        userInfo.setUserCode(UUID.randomUUID().toString());
        return new RestModelTemplate<Boolean>().Success(userService.createUser(userInfo));
    }

    @GetMapping("/getRoles")
    public RestModelTemplate<RoleInfo> getRoleInfo() {
        List<RoleInfo> roleInfoList = userService.getAllRoleInfo();
        return new RestModelTemplate<>().Success(roleInfoList);
    }

    @PostMapping("/createRole")
    public RestModelTemplate<Boolean> createRole(@RequestBody RoleInfo roleInfo) {
        roleInfo.setRoleCode(UUID.randomUUID().toString());
        return new RestModelTemplate<>().Success(userService.createRole(roleInfo));
    }

    @GetMapping("/getResources")
    public RestModelTemplate<ResourceInfo> getResourceInfo() {
        return new RestModelTemplate<>().Success(userService.getAllResourceInfo());
    }

    @PostMapping("/createResource")
    public RestModelTemplate<Boolean> createResource(@RequestBody ResourceInfo resourceInfo) {
        resourceInfo.setResourceCode(UUID.randomUUID().toString());
        return new RestModelTemplate<Boolean>().Success(userService.createResource(resourceInfo));
    }

    @GetMapping("/getUris")
    public RestModelTemplate<UriInfo> getAllUriInfo() {
        return new RestModelTemplate<>().Success(userService.getAllUriInfo());
    }

    @PostMapping("/createUri")
    public RestModelTemplate<Boolean> createUri(@RequestBody UriInfo uriInfo) {
        uriInfo.setUriCode(UUID.randomUUID().toString());
        return new RestModelTemplate<>().Success(userService.createUri(uriInfo));
    }

    @GetMapping("/login")
    public RestModelTemplate<UserInfoData> loginService(@RequestParam("userName") String userName,
                                                @RequestParam("passWord") String passWord, HttpServletRequest request) {
        UserInfoData userInfoData = authResource.getUserInfoByLoginInfo(userName, passWord);
        request.getSession().setAttribute(ConstantUtils.USERINFO, userInfoData);
        return new RestModelTemplate<>().Success(userInfoData);
    }

    @PostMapping("/loginOut")
    public RestModelTemplate<Boolean> loginOutService(@RequestParam("token") String token) {
        String userName = authResource.authUserInfo(token);
        if(userName == null) {
            return new RestModelTemplate<>().Fail("004", "用户不存在");
        }
        return new RestModelTemplate<>().Success(authResource.clearUserLogInfo(userName));
    }

}
