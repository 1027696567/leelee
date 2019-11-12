package com.example.service;

import com.example.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

public interface ShiroService {

    /**
     * 登录接口
     * */
    Serializable userLogin(HttpServletResponse response,String username, String password);
    /**
     * 获取用户角色
     * @param username username
     * @return Set<String>
     */
    Set<String> getRoles(String username);

    /**
     * 获取用户权限
     * */
    Set<String> getPermissions();

    /**
     * 获取权限菜单
     * */
    Map<String, List> getMenu();

    /**
     * 获取所有用户以及角色
     * */
    List<UserInfo> getUserList();

    /**
     * 禁用用户账号
     * */
    void deleteUser(Long userId);

    /**
     * 路由demo
     * */
    Map<String, List> routerDemo();


}
