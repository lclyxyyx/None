package xyz.thetbw.blog.web.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.thetbw.blog.data.constant.FieldKey;
import xyz.thetbw.blog.exception.UserAccessErrorException;
import xyz.thetbw.blog.exception.UserException;
import xyz.thetbw.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 用户根据cookie自动登陆
 */
public class UserInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        System.out.println("---------------------------");
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            System.out.println("method name:" + method.getName());
//            System.out.println("bean type:" + handlerMethod.getBeanType().getName());
//        }else {
//            System.out.println("handel type:"+handler.getClass().getName());
//        }
//        System.out.println();
        try {
            userService.findUser();
        }catch (UserException e){
            LOG.warn(e.getMessage());
            if (e instanceof UserAccessErrorException){
                userService.deleteAccessFromCookie(response);
            }
        }
        return true;
    }
}
