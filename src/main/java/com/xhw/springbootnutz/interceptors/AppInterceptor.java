package com.xhw.springbootnutz.interceptors;

import com.xhw.springbootnutz.util.SpringContextUtils;
import org.nutz.dao.Dao;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * APP 请求拦截器，拦截所有 APP端的API
 *
 * @author Somer
 * @date 2018-09-06 16:36
 **/
public class AppInterceptor implements HandlerInterceptor {
    Dao dao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");// 获取请求 token
        String imei = request.getParameter("imei");// 获取 imei
        dao = (Dao) SpringContextUtils.getBean("dao");
        return true;

    }
}
