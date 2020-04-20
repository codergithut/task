package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.config.WeiXinTokenScheduleTask;
import com.factory.task.model.weixin.message.SubMessageBack;
import com.factory.task.model.weixin.message.SubscribeMessage;
import com.factory.task.model.weixin.message.SubscribeMessageUtil;
import com.factory.task.util.weixin.AesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2020/4/19.
 */
@RestController
@RequestMapping("/message")
@CrossOrigin
public class WeiXinMessageController {

    @Autowired
    private SubscribeMessageUtil subscribeMessageUtil;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String valilMessage() throws AesException {
        Map<String,Map<String,Object>> keyValue = new HashMap<>();
        Map<String,Object> thing1 = new HashMap<>();
        thing1.put("value", "BRK产线油泵漏油");

        Map<String,Object> name3 = new HashMap<>();
        name3.put("value", "李四");

        Map<String,Object> name2 = new HashMap<>();
        name2.put("value", "张三");

        Map<String, Object> time4 = new HashMap<>();
        time4.put("value", "2019-10-15");


        keyValue.put("name2", name2);
        keyValue.put("name3", name3);
        keyValue.put("time4", time4);
        keyValue.put("thing1", thing1);
        SubMessageBack ss = subscribeMessageUtil.sendSubscribeMessage(
                "_QRm8hkaNCftgMisIrjV1xASMGiTSRKp6hy6k8UptQs",
                keyValue, "oYZkO5JBKPWvjzWcFiwsM5DJOP8E");
        System.out.println("------------------------");
        System.out.println(ss.getErrcode());
        System.out.println(ss.getErrmsg());
        return "";
    }

}
