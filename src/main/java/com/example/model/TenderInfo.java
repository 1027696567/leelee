package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tender_info
 * @author
 */
@Data
public class TenderInfo implements Serializable {
    /**
     * 投标id
     */
    private Long tenderId;

    /**
     * 投标人姓名
     */
    private String tenderUserName;

    /**
     * 投标时间
     */
    private Date tenderTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否上传投标书
     */
    private String uploadStatus;

    /**
     * 投标书地址
     */
    private String tenderBookUrl;

    private static final long serialVersionUID = 1L;

    private Long itemId;
}
