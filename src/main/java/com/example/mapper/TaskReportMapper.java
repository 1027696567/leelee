package com.example.mapper;

import com.example.model.TaskReportInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskReportMapper {
    int deleteByPrimaryKey(Long taskReportId);

    int insert(TaskReportInfo record);

    int insertSelective(TaskReportInfo record);

    TaskReportInfo selectByPrimaryKey(Long taskReportId);

    int updateByPrimaryKeySelective(TaskReportInfo record);

    int updateByPrimaryKey(TaskReportInfo record);
}
