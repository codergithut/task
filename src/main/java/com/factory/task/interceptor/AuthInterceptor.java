package com.factory.task.interceptor;

import com.factory.task.data.user.UserInfoData;
import com.factory.task.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                             HttpServletResponse response, Object handler) {
        String token = request.getHeader(TOKEN);
        String uri = request.getRequestURI();
        if(token == null) {
            return false;
        }
        if(StringUtils.isEmpty(authResource.getUserCodeByToken(token))) {
            return false;
        }
        request.getSession().setAttribute(USERINFO, authResource.getUserCodeByToken(token));
        //return authResource.isUserAuth(userInfo.getUserName(), uri);
        return true;
    }
}
