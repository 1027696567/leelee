package com.example.config;

import com.alibaba.druid.util.StringUtils;
import org.apache.catalina.SessionListener;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomSessionManager extends DefaultWebSessionManager {

    private static final Logger logger = LoggerFactory.getLogger(CustomSessionManager.class);

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    public CustomSessionManager() {
        super();
        //15分钟，10s=10000L,设置为900000L
        setGlobalSessionTimeout(900000L);
        //是否开启定时调度器进行检测过期session 默认为true
        setSessionValidationSchedulerEnabled(true);
        // 去掉shiro登录时url里的JSESSIONID
        setSessionIdUrlRewritingEnabled(false);

    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //如果请求头中有 Authorization 则其值为sessionId
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
