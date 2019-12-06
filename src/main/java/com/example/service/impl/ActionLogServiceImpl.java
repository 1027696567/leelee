package com.example.service.impl;

import com.example.mapper.ActionLogMapper;
import com.example.model.ActionLogInfo;
import com.example.service.ActionLogService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description log
 * @create 2019-12-06 19:27
 */

@Service
public class ActionLogServiceImpl implements ActionLogService {
    @Autowired
    ActionLogMapper actionLogMapper;
    @Override
    public List<ActionLogInfo> queryActionLogList() {
        return actionLogMapper.queryActionLogList();
    }
}
