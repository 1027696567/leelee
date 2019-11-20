package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * task_report_info
 * @author
 */
@Data
public class TaskReportInfo implements Serializable {
    /**
     * 任务进度报告id
     */
    private Long taskReportId;

    /**
     * 汇报人名称
     */
    private String taskReportUsername;

    /**
     * 汇报日期
     */
    private Date taskReportTime;

    /**
     * 责任人
     */
    private String dutyUsername;

    /**
     * 本次完成工作量
     */
    private Integer taskAmount;

    /**
     * 完成情况说明
     */
    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getTaskReportId() {
        return taskReportId;
    }

    public void setTaskReportId(Long taskReportId) {
        this.taskReportId = taskReportId;
    }

    public String getTaskReportUsername() {
        return taskReportUsername;
    }

    public void setTaskReportUsername(String taskReportUsername) {
        this.taskReportUsername = taskReportUsername;
    }

    public Date getTaskReportTime() {
        return taskReportTime;
    }

    public void setTaskReportTime(Date taskReportTime) {
        this.taskReportTime = taskReportTime;
    }

    public String getDutyUsername() {
        return dutyUsername;
    }

    public void setDutyUsername(String dutyUsername) {
        this.dutyUsername = dutyUsername;
    }

    public Integer getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(Integer taskAmount) {
        this.taskAmount = taskAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
