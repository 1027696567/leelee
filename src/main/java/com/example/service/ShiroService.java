package com.example.service;

import com.example.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

public interface ShiroService {

    /**
     * 登录接口
     * */
    public Serializable userLogin(HttpServletResponse response,String username, String password);
    /**
     * 获取用户角色
     * @param username username
     * @return Set<String>
     */
    public Set<String> getRoles(String username);

    /**
     * 获取用户权限
     * */
    public Set<String> getPermissions();

    /**
     * 获取权限菜单
     * */
    public Map<String, List> getMenu();

    /**
     * 获取所有用户以及角色
     * */
    public List<UserInfo> getUserList();

    /**
     * 禁用用户账号
     * */
    public void deleteUser(Long userId);

    /**
     * 路由demo
     * */
    public Map<String, List> routerDemo();


}
