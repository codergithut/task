package com.factory.task.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianjian on 2020/1/16.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthResource authResource;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = request.getParameter("token");
        String uri = request.getRequestURI();
        if(token == null) {
            return false;
        }

        String userName = authResource.authUserInfo(token);
        if("Fail".equals(userName)) {
            return false;
        }

        return authResource.isUserAuth(userName, uri);


    }
}
