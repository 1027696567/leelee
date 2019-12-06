package com.example.controller;

import com.example.model.TaskInfo;
import com.example.model.TaskReportInfo;
import com.example.service.TaskService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 进度管理
 * @create 2019-11-18 22:14
 */
@CrossOrigin
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/selectAll")
    @RequiresAuthentication
    public List<TaskInfo> selectAll() {
        return taskService.selectAllTask();
    }

    @PostMapping("/task/addTask")
    @RequiresPermissions("sys:user:shiro")
    public String addTask(@RequestBody TaskInfo taskInfo, HttpServletResponse httpServletResponse) {
        return taskService.addTask(httpServletResponse,taskInfo);
    }

    @GetMapping("/task/selectChildren")
    @RequiresAuthentication
    public List<TaskInfo> selectChildren(@RequestParam("taskNumber")String taskNumber) {
        return taskService.selectChildren(taskNumber);
    }

    @PutMapping("/task/updateTask")
    @RequiresAuthentication
    public Integer updateTask(@RequestBody TaskInfo taskInfo) {
        return taskService.updateTask(taskInfo);
    }

    @PostMapping("/task/addTaskReport")
    @RequiresPermissions("sys:user:shiro")
    public Integer insertTaskReport(@RequestBody TaskReportInfo taskReportInfo) {
        return taskService.insertTaskReportInfo(taskReportInfo);
    }

    @DeleteMapping("/task/deleteTaskInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer deleteTaskInfo(@RequestParam("taskId")Long taskId) {
        return taskService.deleteTaskInfo(taskId);
    }
}
