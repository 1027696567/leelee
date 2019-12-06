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

    private String taskNumber;
}
