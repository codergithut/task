package com.factory.task.model.weixin.message;

import com.factory.task.config.WeiXinTokenScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by tianjian on 2020/4/19.
 */
@Service
public class SubscribeMessageUtil {

    @Value("${spring.weixin.miniprogram_state}")
    private String miniprogram_state;

    @Value("${spring.weixin.lang}")
    private String lang;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.weixin.publishUrl}")
    private String publishUrl;

    @Autowired
    private WeiXinTokenScheduleTask weiXinTokenScheduleTask;


    private SubscribeMessage buildSubscribeMessage(String templateId, Map data, String page, String touser, String token) {
        SubscribeMessage subscribeMessage = new SubscribeMessage();
        subscribeMessage.setTemplate_id(templateId);
        subscribeMessage.setData(data);
        if(page != null) {
            subscribeMessage.setPage(page);
        }
        subscribeMessage.setTouser(touser);
        subscribeMessage.setAccess_token(token);
        return subscribeMessage;
    }

    public SubMessageBack sendSubscribeMessage(String templateId, Map data, String touser) {
        return sendSubscribeMessage(templateId, data, null, touser);
    }

    public SubMessageBack sendSubscribeMessage(String templateId, Map data, String page, String touser) {
        String token = weiXinTokenScheduleTask.getToken();
        SubscribeMessage subscribeMessage = buildSubscribeMessage(templateId, data, page, touser, token);
        publishUrl = publishUrl.replace("accessToken", token);
        return restTemplate.postForEntity(publishUrl, subscribeMessage, SubMessageBack.class).getBody();
    }
}
