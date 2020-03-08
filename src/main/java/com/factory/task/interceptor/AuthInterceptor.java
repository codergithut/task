package com.factory.task.interceptor;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.util.ConstantUtils;
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
        UserInfoData userInfo = (UserInfoData) request.getSession().getAttribute(ConstantUtils.USERINFO);
        String uri = request.getRequestURI();
        if(userInfo == null) {
            return false;
        }

        return authResource.isUserAuth(userInfo.getUserName(), uri);


    }
}
