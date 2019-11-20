package com.example.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * task_info
 * @author
 */
@Data
public class TaskInfo implements Serializable {
    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 任务名称
     */
    private String taskName;

    private String itemNumber;

    private String itemName;

    /**
     * 任务工期
     */
    private String taskTime;

    /**
     * 计划开始日期
     */
    private Date planBeginTime;

    /**
     * 计划完成日期
     */
    private Date planEndTime;

    /**
     * 考核日期
     */
    private Date planCheckTime;

    /**
     * 实际开始时间
     */
    private Date realityBeginTime;

    /**
     * 实际完成时间
     */
    private Date realityEndTime;

    /**
     * 任务进度
     */
    private Integer taskRate;

    /**
     * 任务是否完成，0：未完成，1：完成
     */
    private Byte status;

    /**
     * 任务优先级,1为最高级，越往上级别越低
     */
    private String taskPriority;

    /**
     * 父任务编号
     */
    private String fatherNumber;

    /**
     * 是否为主任务
     */
    private Boolean hasChildren;

    /**
     * 备注
     */
    private String remarks;

    private static final long serialVersionUID = 1L;

}
