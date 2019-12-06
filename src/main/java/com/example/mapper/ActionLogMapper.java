package com.example.mapper;


import com.example.model.ActionLogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActionLogMapper {
    /**
     * 插入ActionLog数据
     */
    int insertActionLog(ActionLogInfo actionLogInfo);

    /**
     * 查询ActionLog数据
     */
    List<ActionLogInfo> queryActionLogList();
}
