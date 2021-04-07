package com.baizhi.interceptor;

import com.baizhi.common.CommonConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class myInterceptor implements HandlerInterceptor {
    /*
    返回为true ，代表放行请求，false 中断请求
    参数1：当前请求对象 参数2：当前请求对应响应对象  参数3：当前请求的控制器对应的方法对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==========1===========");
        System.out.println("method name=" + ((HandlerMethod) handler).getMethod().getName());
        String requestURI = request.getRequestURI();
        System.out.println("requestURI" + requestURI);
        Object adminInfo = request.getSession().getAttribute(CommonConstants.ADMININFO);
        if (adminInfo == null) {
//            String json = "当前没有权限访问，请登录管理员账号！";
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("msg", "当前没有权限访问，请登录管理员账号！");
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
        return true;
    }

}
