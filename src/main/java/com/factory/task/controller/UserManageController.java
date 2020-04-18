package com.factory.task.controller;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.error.UserIsNotExist;
import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.user.RoleInfo;
import com.factory.task.model.user.UriInfo;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.factory.task.model.RestModelTemplate.FailUserInfoCheck;
import static com.factory.task.util.ConstantUtils.TOKEN;
import static com.factory.task.util.RequestUtil.getUserCodeBySession;

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

    @Autowired
    private HttpServletRequest request;

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

    @GetMapping(path = "/getUris", name = "get uri")
    public RestModelTemplate<UriInfo> getAllUriInfo() {
        return new RestModelTemplate<>().Success(userService.getAllUriInfo());
    }

    @GetMapping(path = "/login", name = "login")
    public RestModelTemplate<Map<String,String>> loginService(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Map<String,String> userInfo = new HashMap<>();
        UserInfoData userInfoData = userService.findUserByNameAndPassWord(userName, passWord);
        if(userInfoData != null) {
            userInfo = authResource.createToken(userInfoData);
        }
        return new RestModelTemplate<>().Success(userInfo);
    }

    @PostMapping(path = "/loginOut", name ="login out")
    public RestModelTemplate<Boolean> loginOutService() {
        String token = getUserCodeBySession(request);
        String userName = authResource.authUserInfo(token);
        if(userName == null) {
            return FailUserInfoCheck();
        }
        return new RestModelTemplate<>().Success(authResource.clearUserLogInfo(userName));
    }

    @GetMapping("/getUserByToken")
    public RestModelTemplate<UserInfo> getUserInfoByToken() {
        String token = request.getHeader(TOKEN);
        if(StringUtils.isEmpty(token)) {
            return new RestModelTemplate<UserInfo>().Success(new UserInfo());
        }
        String userCode = authResource.getUserCodeByToken(token);
        UserInfoData userInfoData = userService.findUserByUserCode(userCode);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoData, userInfo);
        return new RestModelTemplate<>().Success(userInfo);
    }



}
