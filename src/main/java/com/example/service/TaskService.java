package com.example.service;

import com.example.model.TaskInfo;
import com.example.model.TaskReportInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TaskService {
    List<TaskInfo> selectAllTask();

    String addTask(HttpServletResponse httpServletResponse,TaskInfo taskInfo);

    List<TaskInfo> selectChildren(String taskNumber);

    Integer updateTask(TaskInfo taskInfo);

    Integer insertTaskReportInfo(TaskReportInfo taskReportInfo);

    Integer deleteTaskInfo(Long taskId);
}
