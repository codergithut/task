package com.factory.task.interceptor;

import com.alibaba.fastjson.JSON;
import com.factory.task.model.RestModelTemplate;
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

        if(request.getRemotePort() == 8080 || request.getLocalPort() == 8080) {
            return true;
        }	    
        String token = request.getHeader(TOKEN);
        String uri = request.getRequestURI().split("\\?")[0];

        if(StringUtils.isEmpty(authResource.getUserCodeByToken(token))
                || StringUtils.isEmpty(token)) {
            failResponseData(response);
            return false;
        }
//        if(!authResource.checkUriAndUser(uri, token)) {
//            failResponseData(response);
//            return false;
//        }
        request.getSession().setAttribute(USERINFO, authResource.getUserCodeByToken(token));
        //return authResource.isUserAuth(userInfo.getUserName(), uri);
        return true;
    }

    private void failResponseData(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(403);
        PrintWriter writer = response.getWriter();
        RestModelTemplate fail = new RestModelTemplate<>().Fail("100007", "token服务器已经失效");
        writer.write(JSON.toJSONString(fail));
    }
}
