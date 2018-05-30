package com.wxsoft.drinkTea.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccessControlAllowOriginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        //if (path.contains("/fileupload/") || path.contains("/imgdir/") || path.contains("metronic/") || path.contains("css/") || path.contains("images/")) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //}
        // response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // response.setHeader("Access-Control-Allow-Methods", "GET");
        // response.setHeader("Allow", "GET");
        return true;
    }

}
