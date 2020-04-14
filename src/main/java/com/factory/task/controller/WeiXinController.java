package com.factory.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.factory.task.data.user.UserInfoData;
import com.factory.task.data.weixin.WeiXinUserLinkSysUser;
import com.factory.task.data.weixin.curd.WeiXinUserLinkSysUserCurd;
import com.factory.task.interceptor.AuthResource;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.weixin.WeiXinLogin;
import com.factory.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2020/4/12.
 */
@RestController
@RequestMapping("/weixin")
@CrossOrigin
public class WeiXinController {

    @Autowired
    private WeiXinUserLinkSysUserCurd weiXinUserLinkSysUserCurd;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthResource authResource;

    //EncodingAESKey Y9Z2BQWXoUn0LNPW87FgPDgkSdKo6rA7gCpUd6U6H0L

    //token tianjian

    @Autowired
    private RestTemplate restTemplate;
    String secretKey = "11563aa67bfedfa04777176081e240c2";
    String appid = "wxf9682b2d07b42000";


    @GetMapping("/userInfo")
    public RestModelTemplate<Map<String,String>> getWeiXinUserInfo(@RequestParam("code") String code) {
        String req = "https://api.weixin.qq.com/sns/jscode2session?appid=wxf9682b2d07b42000&secret=11563aa67bfedfa04777176081e240c2&js_code=$code&grant_type=authorization_code";
        String realReq = req.replace("$code", code);
        ResponseEntity<String> s = restTemplate.getForEntity(realReq, String.class);
        System.out.print("======" + s);
        WeiXinLogin weiXinLogin = JSONObject.parseObject(s.getBody(), WeiXinLogin.class);
        WeiXinUserLinkSysUser weiXinUserInfo = weiXinUserLinkSysUserCurd.findByUnionId(weiXinLogin.getOpenid());
        UserInfoData userInfoData = userService.findUserByUserCode(weiXinUserInfo.getUserCode());
        Map<String,String> userInfo = new HashMap<>();
        if(weiXinLogin != null) {
            userInfo = authResource.createToken(userInfoData);
        }
        return new RestModelTemplate().Success(userInfo);
    }
}
