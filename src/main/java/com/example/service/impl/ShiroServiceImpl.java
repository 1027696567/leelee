package com.example.service.impl;

import com.example.mapper.SysMenuMapper;
import com.example.mapper.SysRoleMapper;
import com.example.mapper.SysUserMapper;
import com.example.model.*;
import com.example.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;
/**
 * @description
 * @author chen
 * */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    /**
     * 用户拥有的角色Id
     **/
    private List<Long> roleId;

    /**
     * 登录接口
     * */
    @Override
    public Serializable userLogin(HttpServletResponse response,String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token1 = new UsernamePasswordToken(username,password);
        try {
            subject.login(token1);
        }catch (IncorrectCredentialsException e) {
            response.setStatus(202);
            return e.getMessage();
        }catch (UnknownAccountException e) {
            response.setStatus(202);
            return e.getMessage();
        }catch (LockedAccountException e) {
            response.setStatus(203);
            return e.getMessage();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Serializable token = subject.getSession().getId();
        return token;
    }

    /**
     * 获取用户角色
     */
    @Override
    public Set<String> getRoles(String username) {
        Long userId = sysUserMapper.selectIdByUserName(username);
        roleId = sysRoleMapper.selectRoleIdByUserId(userId);
        List<String> sysRole = new ArrayList<>();
        for (int id = 0; id < roleId.size(); id++) {
            sysRole.add(sysRoleMapper.selectByRoleId(roleId.get(id)).getRoleName());
        }
        Set<String> roles = new HashSet<>();
        for (String roleName : sysRole) {
            if (StringUtils.isEmpty(roleName)) {
                continue;
            }
            roles.add(roleName);
        }
        return roles;
    }

    /**
     * 获取用户权限
     **/
    @Override
    public Set<String> getPermissions() {
        if (!StringUtils.isEmpty(roleId)) {
            List<String> permsList = new ArrayList<>();
            Set<Long> menuIdSet = new HashSet<>();
            for (int i = 0; i < roleId.size(); i++) {
                menuIdSet.addAll(sysMenuMapper.selectMenuIdByRoleId(roleId.get(i)));
            }
            List<Long> menuId = new ArrayList<>(menuIdSet);
            for (int id = 0; id < menuId.size(); id++) {
                SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId.get(id));
                permsList.add(sysMenu.getPerms());
            }
            Set<String> permsSet = new HashSet<>();
            for (String perms : permsList) {
                if (StringUtils.isEmpty(perms)) {
                    continue;
                }
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
            return permsSet;
        } else {
            return null;
        }
    }

    /**
     * 获取权限菜单
     **/
    @Override
    public Map<String, List> getMenu() {
        //获取menu菜单
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = sysUserMapper.selectIdByUserName(user.getUsername());
        List<Long> roleId = sysRoleMapper.selectRoleIdByUserId(userId);
        Set<Long> menuId = new HashSet<>();
        Set<SysMenu> menuSet = new HashSet<>();
        for (int i = 0; i < roleId.size(); i++) {
            menuId.addAll(sysMenuMapper.selectMenuIdByRoleId(roleId.get(i)));
        }
        List<Long> menuIdList = new ArrayList<>(menuId);
        for (int id = 0; id < menuIdList.size(); id++) {
            menuSet.add(sysMenuMapper.selectByPrimaryKey(menuIdList.get(id)));
        }
        List<SysMenu> menu = new ArrayList<>(menuSet);

        //按要求转换菜单格式
        //定义一个RouterData
        RouterData routerData = new RouterData();
        List<NavData> navDatas = new ArrayList<>();
        List<RouterData.Children> childrens = new ArrayList<>();
        List<RouterData> routerDatas = new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        for (int i = 0; i < menu.size(); i++) {
            //判断是否为父菜单
            if (menu.get(i).getType() == 1 && menu.get(i).getParentId() == 0) {
                //定义一个navData
                NavData navData = new NavData();
                navData.setIcon(menu.get(i).getIcon());
                navData.setIndex(menu.get(i).getName());
                navData.setTitle(menu.get(i).getName());
                List<NavData.Subs> subs = new ArrayList<>();
                for (int s = 0; s < menu.size(); s++) {
                    //判断是否为该父菜单下的子菜单
                    if (menu.get(s).getType() == 1 && menu.get(s).getParentId() == 1 && menu.get(s).getOrderNum().equals(menu.get(i).getOrderNum())) {
                        NavData.Subs sub = new NavData.Subs();
                        sub.setTitle(menu.get(s).getName());
                        sub.setIndex("/" + menu.get(s).getUrl());
                        subs.add(sub);
                        RouterData.Children children = new RouterData.Children();
                        children.setPath(menu.get(s).getUrl());
                        children.setName(menu.get(s).getUrl());
                        children.setComponent(menu.get(s).getUrl());
                        childrens.add(children);
                    }
                }
                navData.setSubs(subs);
                navDatas.add(navData);
            }
        }
        routerData.setName("main");
        routerData.setPath("/");
        routerData.setComponent("dashboard");
        routerData.setChildren(childrens);
        routerDatas.add(routerData);
        map.put("navData", navDatas);
        map.put("routerData", routerDatas);
        return map;
    }

    /**
     * 角色管理界面
     * 获取所有用户以及角色
     * */
    @Override
    public List<UserInfo> getUserList() {
        List<UserInfo> userInfos = new ArrayList<>();
        List<SysUser> users = sysUserMapper.selectAllUser();
        List<SysUserRole> userRoles = sysRoleMapper.selectAllInfo();
        List<SysRole> roles = sysRoleMapper.selectAllRole();
        for (int i = 0; i < users.size(); i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(users.get(i).getUserId());
            userInfo.setUsername(users.get(i).getUsername());
            userInfo.setRealName(users.get(i).getRealName());
            userInfo.setEmail(users.get(i).getEmail());
            userInfo.setMobile(users.get(i).getMobile());
            if (users.get(i).getStatus() == 1) {
                userInfo.setStatus("启用");
            } else {
                userInfo.setStatus("禁用");
            }
            List<String> roleName = new ArrayList<>();
            for (int s = 0; s < userRoles.size(); s++) {
                if (userRoles.get(s).getUserId().equals(users.get(i).getUserId())) {
                    for (int x = 0; x < roles.size(); x++) {
                        if (roles.get(x).getRoleId() == userRoles.get(s).getRoleId().intValue()) {
                            roleName.add(roles.get(x).getRoleName());
                        }
                    }
                }
            }
            userInfo.setRoleName(String.join("，", roleName));
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    /**
     * 禁用用户账号
     * */
    @Override
    public void deleteUser(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setStatus((byte) 0);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 路由demo
     * */
    @Override
    public Map<String, List> routerDemo() {
        NavData navData = new NavData();
        navData.setIcon("/static/images/money.png");
        navData.setIndex("sales");
        NavData.Subs sub = new NavData.Subs();
        sub.setIndex("/menu1_item1");
        sub.setTitle("选项1");
        List<NavData.Subs> subs = new ArrayList<>();
        subs.add(sub);
        navData.setSubs(subs);
        navData.setTitle("菜单一");
        List<NavData> navDatas = new ArrayList<>();
        navDatas.add(navData);
        RouterData.Children children = new RouterData.Children();
        children.setComponent("menu1_item1");
        children.setName("menu1_item1");
        children.setPath("menu1_item1");
        List<RouterData.Children> childrens = new ArrayList<>();
        childrens.add(children);
        RouterData routerData = new RouterData();
        routerData.setChildren(childrens);
        routerData.setComponent("dashboard");
        routerData.setName("main");
        routerData.setPath("/");
        List<RouterData> routerDatas = new ArrayList<>();
        routerDatas.add(routerData);
        Map<String, List> map = new HashMap<>();
        map.put("navData", navDatas);
        map.put("routerData", routerDatas);
        return map;
    }
}
