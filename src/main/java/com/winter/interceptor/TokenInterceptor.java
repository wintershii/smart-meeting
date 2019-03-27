package com.winter.interceptor;

import com.winter.service.impl.FileServiceImpl;
import com.winter.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                logger.info("成功通过拦截器");
                return true;
            }
        }
        request.getRequestDispatcher("/user/tokenExpired.do").forward(request,response);
        return false;
    }
}
