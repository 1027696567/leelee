package com.example.controller;

import com.example.model.ActionLogInfo;
import com.example.service.ActionLogService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description log 控制层
 * @create 2019-12-06 19:28
 */

@RestController
@CrossOrigin
public class ActionLogController {
    @Autowired
    ActionLogService actionLogService;

    @GetMapping("/log/getLogList")
    @RequiresAuthentication
    public List<ActionLogInfo> queryActionLogList(){
        return actionLogService.queryActionLogList();
    }
}
