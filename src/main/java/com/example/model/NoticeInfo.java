package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * notice_info
 * @author
 */
@Data
public class NoticeInfo implements Serializable {
    /**
     * 通知Id
     */
    private Long noticeId;

    /**
     * 发布人姓名
     */
    private String publishUsername;

    /**
     * 回复时间
     */
    private Date publishTime;

    /**
     * 发布内容
     */
    private String publishContent;

    /**
     * 回复人姓名
     */
    private String replyUsername;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复状态
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    private String statusName;
}
