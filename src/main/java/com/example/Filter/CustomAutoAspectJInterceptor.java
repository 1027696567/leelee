package com.example.Filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 自定义拦截器-方法拦截器，基于注解的AspectJ方式
 * @ClassName: CustomAutoAspectJInterceptor
 * @Description: 配置在applicationContext.xml中
 * @author OnlyMate
 * @Date 2018年8月29日 下午4:03:49
 *
 */
@Component
@Aspect
public class CustomAutoAspectJInterceptor {
    private Logger logger = LoggerFactory.getLogger(CustomAutoAspectJInterceptor.class);

    @Around("execution (* com.example.controller.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long startTime = System.currentTimeMillis();//开始时间

        Object[] args = point.getArgs();//获取参数数组
        Object ret = point.proceed(args);//执行原方法并获取返回结果

        long endTime = System.currentTimeMillis();//结束时间

        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append("###请求URL: " + request.getRequestURL().toString());
        sb.append("   ###IP: " + request.getRemoteAddr());
        sb.append("   ###Params: " + Arrays.toString(args));
        sb.append("   ###CLASS_METHOD: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        sb.append("   ###耗时: " + (endTime - startTime) + "毫秒");

        logger.info(sb.toString());

        // TODO: 2019-11-18  
        return ret;
    }
}
