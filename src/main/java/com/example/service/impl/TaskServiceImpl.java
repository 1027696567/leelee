package com.example.service.impl;

import com.example.mapper.TaskMapper;
import com.example.mapper.TaskReportMapper;
import com.example.model.TaskInfo;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 进度管理
 * @create 2019-11-18 22:16
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskReportMapper taskReportMapper;

    @Override
    public List<TaskInfo> selectAllTask() {
        System.out.println(taskMapper.selectAll(true));
        return taskMapper.selectAll(true);
    }

    @Override
    public String addTask(HttpServletResponse httpServletResponse,TaskInfo taskInfo) {
        TaskInfo taskInfo1 = taskMapper.selectByTaskNumber(taskInfo.getTaskNumber());
        System.out.println(taskInfo1);
        if (taskInfo1 != null) {
            httpServletResponse.setStatus(401);
            return "任务编号重复";
        } else {
            taskMapper.insertSelective(taskInfo);
            return "创建成功";
        }
    }

    @Override
    public List<TaskInfo> selectChildren(String taskNumber) {
        return taskMapper.selectChildren(taskNumber);
    }

    @Override
    public Integer updateTask(TaskInfo taskInfo) {
        return taskMapper.updateByPrimaryKeySelective(taskInfo);
    }
}
