package com.example.mapper;

import com.example.model.TaskInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);

    List<TaskInfo> selectAll(boolean hasChildren);

    List<TaskInfo> selectChildren(String taskNumber);

    TaskInfo selectByTaskNumber(String taskNumber);
}
