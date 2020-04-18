package com.factory.task.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by tianjian on 2020/4/18.
 */
public class VailMessageController {

    private String token = "3fb2e6f82b96";

    String ss = "oPw8c00fI8BdcnsVoKUXB0qqi5pTMYHd6O8e6vwXGSE";

    @GetMapping("/message")
    public String valilMessage(@RequestParam("signature") String signature,
                               @RequestParam("timestamp") String timestamp,
                               @RequestParam("nonce") String nonce,
                               @RequestParam("echostr") String echostr) {
        if(validMessageUtil(timestamp, nonce, signature)) {
            return echostr;
        }
        return "";
    }

    private boolean validMessageUtil(String timestamp, String nonce, String signature) {
        List<String> valMessage = new ArrayList<>();
        valMessage.add(token);
        valMessage.add(timestamp);
        valMessage.add(nonce);
        Collections.sort(valMessage);
        StringBuffer s = new StringBuffer();
        valMessage.stream().forEach(e -> {
            s.append(e);
        });
        String sign = DigestUtils.shaHex(s.toString());
        System.out.println("sign =======" + sign);
        System.out.println("signature =====" + signature);
        if(!sign.equals(signature)) {
            return false;
        }
        return true;
    }
}
