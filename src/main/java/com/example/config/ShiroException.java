package com.example.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ShiroException {

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException (HttpServletResponse response){
        response.setStatus(401);
        return "抱歉您没有权限访问该内容!";
    }

     @ExceptionHandler(UnauthenticatedException.class)
    public String authenticatedException(HttpServletResponse response){
         response.setStatus(402);
         return "登录超时";
     }
/*    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        return e.getMessage()+"系统异常!";
    }
*/
}
