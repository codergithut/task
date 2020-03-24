package com.factory.task.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianjian on 2020/3/24.
 */
public class RequestUtil {
    public static String getUserCodeBySession(HttpServletRequest request) {
        String userCode = (String)request.getSession().getAttribute(ConstantUtils.USERINFO);
        return userCode;
    }
}
