package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.model.RestModelTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by tianjian on 2020/4/12.
 */
@RestController
@RequestMapping("/weixin")
@CrossOrigin
public class WeiXinController {

    @Autowired
    private RestTemplate restTemplate;
    String secretKey = "11563aa67bfedfa04777176081e240c2";
    String appid = "wxf9682b2d07b42000";
    @GetMapping("/getUserInfo")
    public RestModelTemplate getWeiXinUserInfo(@RequestParam("code") String code) {
        String req = "https://api.weixin.qq.com/sns/jscode2session?appid=wxf9682b2d07b42000&secret=11563aa67bfedfa04777176081e240c2&js_code=$code&grant_type=authorization_code";
        String realReq = req.replace("$code", code);
        ResponseEntity s = restTemplate.getForEntity(realReq, null);
        System.out.println(JSON.toJSON(s.getBody()).toString());
        return new RestModelTemplate<List<Map<String,String>>>().Success(null);
    }
}
