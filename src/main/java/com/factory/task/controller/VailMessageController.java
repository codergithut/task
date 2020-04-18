package com.factory.task.controller;

import com.factory.task.util.weixin.AesException;
import com.factory.task.util.weixin.SHA1;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by tianjian on 2020/4/18.
 */
@RestController
@CrossOrigin
public class VailMessageController {

    private String token = "3fb2e6f82b96";

    String ss = "GZ3elhWYSuejvnTVXxe31M5Hip8mOh9bqCqH3Z3Aons";

    @GetMapping("/message")
    public String valilMessage(@RequestParam("signature") String signature,
                               @RequestParam("timestamp") String timestamp,
                               @RequestParam("nonce") String nonce,
                               @RequestParam("echostr") String echostr) throws AesException {
        if(validMessageUtil(timestamp, nonce, signature)) {
            return echostr;
        }
        return "";
    }

    private boolean validMessageUtil(String timestamp, String nonce, String signature) throws AesException {

        String sign = SHA1.getSHA1(token, timestamp, nonce);
        System.out.println("sign =======" + sign);
        System.out.println("signature =====" + signature);
        if(!sign.equals(signature)) {
            return false;
        }
        return true;
    }
}
