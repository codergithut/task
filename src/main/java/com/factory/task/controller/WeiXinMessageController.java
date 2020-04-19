package com.factory.task.controller;

import com.alibaba.fastjson.JSON;
import com.factory.task.config.WeiXinTokenScheduleTask;
import com.factory.task.model.weixin.WeiXinToken;
import com.factory.task.model.weixin.message.SubMessageBack;
import com.factory.task.model.weixin.message.SubscribeMessage;
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
    private WeiXinTokenScheduleTask weiXinTokenScheduleTask;

    @Autowired
    private RestTemplate restTemplate;

    private String publishUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=accessToken";
    @GetMapping("/test")
    public String valilMessage() throws AesException {
        //String token = weiXinTokenScheduleTask.getToken();
        String token = "32_3gd_0TyqTOPZ28928CzYpE0id2ALmiCz70u8nKsR6t5_DoBsNnPlSOyBIgbpnF2b2EUByKUNCawcAhxl8f7xbbeOpH3lTYxAVLb49y09PQiNlcxuSUfb_TS5vhZh8gz4w-b33QTQM6yeycwhHCFhAEATOR";
        publishUrl = publishUrl.replace("accessToken", token);
        SubscribeMessage subscribeMessage = new SubscribeMessage();
        subscribeMessage.setAccess_token(token);
        subscribeMessage.setTouser("oYZkO5JBKPWvjzWcFiwsM5DJOP8E");
        subscribeMessage.setMiniprogram_state("trial");
        subscribeMessage.setLang("zh_CN");
        subscribeMessage.setTemplate_id("_QRm8hkaNCftgMisIrjV1xASMGiTSRKp6hy6k8UptQs");
        Map<String,Map<String,Object>> keyValue = new HashMap<>();
        Map<String,Object> thing1 = new HashMap<>();
        thing1.put("value", "我是主题");

        Map<String,Object> name3 = new HashMap<>();
        name3.put("value", "tianjian");

        Map<String,Object> name2 = new HashMap<>();
        name2.put("value", "tianjian2");

        Map<String, Object> time4 = new HashMap<>();
        time4.put("value", System.currentTimeMillis());


        keyValue.put("name2", name2);
        keyValue.put("name3", name3);
        keyValue.put("time4", time4);
        keyValue.put("thing1", thing1);

        subscribeMessage.setData(JSON.toJSONString(keyValue));
        subscribeMessage.setTemplate_id("_QRm8hkaNCftgMisIrjV1xASMGiTSRKp6hy6k8UptQs");
        ResponseEntity<SubMessageBack> s = restTemplate.postForEntity(publishUrl, subscribeMessage, SubMessageBack.class);
        SubMessageBack ss = s.getBody();
        System.out.println("------------------------");
        System.out.println(ss.getErrcode());
        System.out.println(ss.getErrmsg());
        return "";
    }

}
