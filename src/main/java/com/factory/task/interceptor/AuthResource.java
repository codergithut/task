package com.factory.task.interceptor;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.error.UserIsLogin;
import com.factory.task.error.UserIsNotExist;
import com.factory.task.model.user.UserInfo;
import com.factory.task.service.UserService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2020/1/16.
 */
@Service
public class AuthResource {

    @Autowired
    private UserService userService;


    Cache<String, String> tokens = CacheBuilder.newBuilder()
            .expireAfterWrite(2000, TimeUnit.SECONDS)
            .build();

    Cache<String, String> userTokens = CacheBuilder.newBuilder()
            .expireAfterWrite(2000, TimeUnit.SECONDS)
            .build();

    Cache<String, List<String>> auths = CacheBuilder.newBuilder()
            .expireAfterWrite(2000, TimeUnit.SECONDS)
            .build();


    public Map<String,String> createToken(String userName, String passWord) throws UserIsNotExist {
        Map<String,String> userMapInfo = new HashMap<>();
        UserInfoData user = userService.findUserByNameAndPassWord(userName, passWord);
        String token = UUID.randomUUID().toString();
        //String token = "1a212de7-ef2d-4a88-b9ee-137c7a3f258b";
        if(user != null) {
            tokens.put(token, user.getUserCode());
            userMapInfo.put("token", token);
            userMapInfo.put("userCode", user.getUserCode());
            System.out.println("token:" + token);
            System.out.println("userCode:" + user.getUserCode());
            return userMapInfo;
        }
        throw new UserIsNotExist();
    }

    public String getUserCodeByToken(String token) {
        return tokens.getIfPresent(token);
    }

    public String authUserInfo(String token) {
        if(tokens.getIfPresent(token) != null) {
            return tokens.getIfPresent(token);
        }
        return "Fail";
    }

    public boolean clearAuthResource() {
        tokens.cleanUp();
        auths.cleanUp();
        return true;
    }

    public UserInfoData getUserInfoByLoginInfo(String userName, String passWord) {
        return userService.findUserByNameAndPassWord(userName, passWord);
    }

    /**
     * 判断用户是否有权限
     * @param userName
     * @param uri
     * @return
     */
    public boolean isUserAuth(String userName, String uri) {
        if(auths.getIfPresent(userName) != null) {
            return auths.getIfPresent(userName).contains(uri);
        } else {
            List<String> userCodes = userService.findUserUriByUserName(userName);
            if(CollectionUtils.isEmpty(userCodes)) {
                return false;
            }
            auths.put(userName, userCodes);
            return userCodes.contains(uri);
        }
    }

    /**
     * 刷新已有用户缓存数据
     */
    public void refreshUserAuth() {
        for(String userCode : auths.asMap().keySet()) {
            List<String> userCodes = userService.findUserUriByUserName(userCode);
            if(CollectionUtils.isEmpty(userCodes)) {
                continue;
            }
            auths.put(userCode, userCodes);
        }
    }

    public boolean clearUserLogInfo(String token) {
        if(token != null) {
            tokens.invalidate(token);
        }
        return true;
    }


}
