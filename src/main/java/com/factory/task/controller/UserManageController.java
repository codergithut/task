package com.factory.task.controller;

import com.factory.task.data.user.UriInfoData;
import com.factory.task.error.UserIsLogin;
import com.factory.task.error.UserIsNotExist;
import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.JobView;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
@RestController
@RequestMapping("/user")
public class UserManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthResource authResource;

    /**
     * 创建任务
     * @return
     */
    @PostMapping("/createUser")
    public RestModelTemplate<Boolean> createUser(@RequestBody UserInfo userInfo) {
        return new RestModelTemplate<Boolean>().Success(userService.createUser(userInfo));
    }

    @GetMapping("/getRoles")
    public RestModelTemplate<RoleInfo> getRoleInfo() {
        List<RoleInfo> roleInfoList = userService.getAllRoleInfo();
        return new RestModelTemplate<>().Success(roleInfoList);
    }

    @PostMapping("/createRole")
    public RestModelTemplate<Boolean> createRole(@RequestBody RoleInfo roleInfo) {
        return new RestModelTemplate<>().Success(userService.createRole(roleInfo));
    }

    @GetMapping("/getResources")
    public RestModelTemplate<ResourceInfo> getResourceInfo() {
        return new RestModelTemplate<>().Success(userService.getAllResourceInfo());
    }

    @PostMapping("/createResource")
    public RestModelTemplate<Boolean> createResource(@RequestBody ResourceInfo resourceInfo) {
        return new RestModelTemplate<Boolean>().Success(userService.createResource(resourceInfo));
    }

    @GetMapping("/getUris")
    public RestModelTemplate<UriInfo> getAllUriInfo() {
        return new RestModelTemplate<>().Success(userService.getAllUriInfo());
    }

    @PostMapping("/createUri")
    public RestModelTemplate<Boolean> createUri(@RequestBody UriInfo uriInfo) {
        return new RestModelTemplate<>().Success(userService.createUri(uriInfo));
    }

    @PostMapping("/login")
    public RestModelTemplate<String> loginService(@RequestParam("userName") String userName,
                                                @RequestParam("passWord") String passWord) {
        String token = null;
        try {
            token = authResource.createToken(userName, passWord);
        } catch (UserIsLogin userIsLogin) {
            userIsLogin.printStackTrace();
            return new RestModelTemplate<>().Fail("003", "用户已经登陆");
        } catch (UserIsNotExist userIsNotExist) {
            userIsNotExist.printStackTrace();
            return new RestModelTemplate<>().Fail("004", "用户不存在");
        }
        return new RestModelTemplate<>().Success(token);
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
