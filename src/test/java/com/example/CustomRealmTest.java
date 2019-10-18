package com.example;

import com.example.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {

    CustomRealm customRealm = new CustomRealm();
    @Test
    public void authentication() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        customRealm.setCredentialsMatcher(matcher);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermissions("user:delete","user:add");
    }

    public static String sha256(String password, String salt) {
        return new SimpleHash("md5", password, salt, 1).toString();
    }

    // 获取一个测试账号 admin
    public static void main(String[] args) {
        // 3743a4c09a17e6f2829febd09ca54e627810001cf255ddcae9dabd288a949c4a
        System.out.println(sha256("admin","123")) ;
    }
}
