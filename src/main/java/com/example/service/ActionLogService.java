package com.example.service;

import com.example.model.ActionLogInfo;

import java.util.List;

public interface ActionLogService {
    /**
     * 查询Action Log列表
     * */
    List<ActionLogInfo> queryActionLogList();
}
