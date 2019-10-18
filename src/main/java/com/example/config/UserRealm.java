package com.example.config;

import com.example.mapper.SysMenuMapper;
import com.example.mapper.SysUserMapper;
import com.example.model.SysMenu;
import com.example.model.SysUser;
import com.example.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private ShiroService shiroService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        if(sysUser == null) {
            throw new UnknownAccountException("账号不存在");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = shiroService.getRoles(sysUser.getUsername());
        Set<String> permissions = shiroService.getPermissions();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SysUser sysUser =sysUserMapper.selectByUserName((String) authenticationToken.getPrincipal());
        if (sysUser == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        if (sysUser.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()),getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName("md5");
        shaCredentialsMatcher.setHashIterations(1);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
