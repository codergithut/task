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
            .expireAfterWrite(60*60*24, TimeUnit.SECONDS)
            .build();

    Cache<String, String> userTokens = CacheBuilder.newBuilder()
            .expireAfterWrite(60*60*24, TimeUnit.SECONDS)
            .build();

    Cache<String, List<String>> auths = CacheBuilder.newBuilder()
            .build();


    public Map<String,String> createToken(UserInfoData user) {
        Map<String,String> userMapInfo = new HashMap<>();
        String token = UUID.randomUUID().toString();
        if(user != null) {
            tokens.put(token, user.getUserCode());
            userMapInfo.put("token", token);
            userMapInfo.put("userCode", user.getUserCode());
            return userMapInfo;
        }
        return null;
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

    public UserInfoData getUserInfoByLoginInfo(String userName, String passWord) {
        return userService.findUserByNameAndPassWord(userName, passWord);
    }

    public boolean clearUserLogInfo(String token) {
        if(token != null) {
            tokens.invalidate(token);
        }
        return true;
    }


    public boolean checkUriAndUser(String uri, String token) {
        String userCode = tokens.getIfPresent(token);
        if(auths.getIfPresent(userCode) != null) {
            return auths.getIfPresent(userCode).contains(uri);
        } else {
            List<String> userCodes = userService.findUserUriByUserCode(userCode);
            if(CollectionUtils.isEmpty(userCodes)) {
                return false;
            }
            auths.put(userCode, userCodes);
            return userCodes.contains(uri);
        }

    }

    /**
     * 刷新当前用户数据
     * @param userCode
     * @return
     */
    public boolean freshenUriAndUser(String userCode) {
        synchronized (auths) {
            auths.invalidate(userCode);
            List<String> userCodes = userService.findUserUriByUserCode(userCode);
            auths.put(userCode,userCodes);
            return true;
        }
    }
}
