package com.example;

import com.example.service.ShiroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeeleeApplicationTests {
    @Autowired
    private ShiroService shiroService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void getRoles() {
        System.out.println(shiroService.getRoles("admin"));
        System.out.println(shiroService.getPermissions());
        System.out.println(shiroService.getMenu());
    }
    @Test
    public void getUserRole() {
        System.out.println(shiroService.getUserList());
    }


}
