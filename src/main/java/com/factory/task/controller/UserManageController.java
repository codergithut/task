package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.error.UserIsLogin;
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

import java.util.ArrayList;
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

    public static void main(String[] args) {
        String uricode = "d991592c-f85e-48f6-942e-e03b37f238f8";
        UriInfo uri = new UriInfo();
        uri.setUriCode(uricode);
        uri.setUriName("测试uri");
        uri.setUriPath("/test");
        System.out.println(JSON.toJSONString(uri));


        List<String> uricodes = new ArrayList<>();
        uricodes.add(uricode);
        String resourceCode = "042a5c20-b6d4-4992-a821-0e83a1644e75";
        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setResourceCode(resourceCode);
        resourceInfo.setResourceName("resource Name");
        resourceInfo.setUriCodes(uricodes);
        System.out.println(JSON.toJSONString(resourceInfo));

        String roleId = "bd8c138f-2a28-4df5-8395-f0c0c17f0503";
        List<String> resources = new ArrayList<>();
        resources.add(resourceCode);
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleCode(roleId);
        roleInfo.setRoleDesc("role desc");
        roleInfo.setRoleName("role name");
        roleInfo.setResourceCodes(resources);
        System.out.println(JSON.toJSONString(roleInfo));



        List<String> roles = new ArrayList<>();
        roles.add(roleId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserCode(UUID.randomUUID().toString());
        userInfo.setPassWord("HH");
        userInfo.setSex("nan");
        userInfo.setTelPhoneNum("12344566");
        userInfo.setRoleCodes(roles);
        System.out.println(JSON.toJSONString(userInfo));
    }



}
