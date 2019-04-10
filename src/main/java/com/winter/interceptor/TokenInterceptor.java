package com.winter.interceptor;

import com.winter.common.Const;
import com.winter.service.impl.FileServiceImpl;
import com.winter.util.PropertiesUtil;
import com.winter.util.RedisUtil;
import com.winter.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token == null) {
            request.getRequestDispatcher("/user/tokenExpired.do").forward(request,response);
        }
        if (token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                logger.info("成功通过拦截器");
                return true;
            }
        }
        String userId = TokenUtil.getInfo(token, "id");
        String redisKey = PropertiesUtil.getProperty("redis_prefix")+userId;
        if (redisUtil.get(redisKey) != null && redisUtil.get(redisKey).equals(token)) {
            request.setAttribute("id",userId);
            request.setAttribute("phone",TokenUtil.getInfo(token,"phone"));
            request.getRequestDispatcher("/user/offerToken.do").forward(request,response);
        } else {
            request.getRequestDispatcher("/user/tokenExpired.do").forward(request,response);
        }
        return false;
    }
}
