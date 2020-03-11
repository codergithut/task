package com.factory.task.controller;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.error.UserIsNotExist;
import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.user.ResourceInfo;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public RestModelTemplate<List<Map<String,String>>> getUser() {
        return new RestModelTemplate<List<Map<String,String>>>().Success(userService.findAll());
    }

    @GetMapping("/getUserByCode")
    public RestModelTemplate<Map<String, String>> getUserInfoByCode(@RequestParam("userCode") String userCode) {
        return new RestModelTemplate<>().Success(userService.findUserByUserCode(userCode));
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
    public RestModelTemplate<Map<String,String>> loginService(@RequestParam("userName") String userName,
                                                              @RequestParam("passWord") String passWord) {
        Map<String,String> userInfo = new HashMap<>();
        try {
            userInfo = authResource.createToken(userName, passWord);
        } catch (UserIsNotExist userIsNotExist) {
            userIsNotExist.printStackTrace();
            return new RestModelTemplate<>().Fail("1001", "user is not exist");
        }
        return new RestModelTemplate<>().Success(userInfo);
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
