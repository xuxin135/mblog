package com.xuxin.interceptor;

import com.xuxin.annotation.PassToken;
import com.xuxin.util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        //没有绑定@RequestMapping的请求跳过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //所有绑定@RequestMapping的请求都需要验证token
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }


       try {
            TokenUtil.verify(token);
        } catch (Exception e) {
            throw new Exception("token无效");
        }
        return true;
    }
}
