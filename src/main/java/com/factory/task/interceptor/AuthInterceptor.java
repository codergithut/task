package com.factory.task.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.factory.task.util.ConstantUtils.TOKEN;
import static com.factory.task.util.ConstantUtils.USERINFO;

/**
 * Created by tianjian on 2020/1/16.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthResource authResource;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader(TOKEN);
        System.out.println("filter token:" + token);
        System.out.println("filter userCode:" + authResource.getUserCodeByToken(token));
        String uri = request.getRequestURI();
        if(token == null) {
            failResponseData(response);
            return false;
        }
        if(StringUtils.isEmpty(authResource.getUserCodeByToken(token))) {
            failResponseData(response);
            return false;
        }
        request.getSession().setAttribute(USERINFO, authResource.getUserCodeByToken(token));
        //return authResource.isUserAuth(userInfo.getUserName(), uri);
        return true;
    }

    private void failResponseData(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(403);
        PrintWriter writer = response.getWriter();
        Map<String, String> map = new HashMap<>();
        map.put("error", "token is null or lose effectiveness");
        writer.write(JSON.toJSONString(map));
    }
}
