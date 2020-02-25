package com.example.controller;

import com.example.model.SysUser;
import com.example.model.UserInfo;
import com.example.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ShiroController {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroController.class) ;

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/userLogin")
    public Serializable userLogin (HttpServletResponse response,@RequestParam(value = "userName") String userName,@RequestParam(value = "passWord") String passWord){
        return shiroService.userLogin(response,userName,passWord);
    }

    @GetMapping("/menu/list")
    @RequiresAuthentication
 //   @RequiresPermissions("sys:user:shiro")
    public Map<String, List> list(){
        return shiroService.getMenu();
    }

    @GetMapping("/menu/userList")
    @RequiresAuthentication
    public List<UserInfo> getUserList() {
        return shiroService.getUserList();
    }

    @RequestMapping("/menu/list1")
    @RequiresAuthentication
    public Map RouterDemo(){
        return shiroService.routerDemo();
    }

    @PutMapping("/user/delete")
    @RequiresAuthentication
    public void changeStatus(@RequestParam(value = "userId")Long userId) {
        shiroService.deleteUser(userId);
    }

    @GetMapping("/userLogOut")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "success" ;
    }

    @PostMapping("/register")
    public Integer Register(@RequestBody SysUser sysUser) {
        return shiroService.register(sysUser);
    }

    @PutMapping("/updateRole")
    public Integer updateRole(@RequestBody UserInfo userInfo) {
        return shiroService.updateRole(userInfo);
    }
}
