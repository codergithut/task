package com.factory.task.config;

import com.factory.task.model.weixin.WeiXinToken;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2020/4/19.
 */
@Service
public class WeiXinTokenScheduleTask {

    @Autowired
    private RestTemplate restTemplate;

    Cache<String, String> tokens = CacheBuilder.newBuilder()
            .expireAfterWrite(60*6*17, TimeUnit.SECONDS)
            .build();

    String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
            "&appid=wxf9682b2d07b42000&secret=11563aa67bfedfa04777176081e240c2";

    public String getToken() {
        if(tokens.getIfPresent("access_token") != null) {
            return tokens.getIfPresent("access_token");
        } else {
            ResponseEntity<WeiXinToken> s = restTemplate.getForEntity(getTokenUrl, WeiXinToken.class);
            String token = s.getBody().getAccess_token();
            tokens.put("access_token", token);
            System.out.println("===========" + token + "===========");
            return token;
        }
    }
}
