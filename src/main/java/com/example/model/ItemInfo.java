package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * item_info
 * @author
 */
@Data
public class ItemInfo implements Serializable {
    /**
     * 项目ID
     */
    private Long itemId;

    /**
     * 项目编号
     */
    private String itemNumber;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 项目类别
     */
    private String itemClasses;

    /**
     * 招标人
     */
    private String username;

    /**
     * 招标地址
     */
    private String address;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 招标时间
     */
    private Date beginTime;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 招标状态
     */
    private String status;

    /**
     * 是否公开参考价
     */
    private String referencePrice;

    /**
     * 最少参与人数
     */
    private String leastNumber;

    /**
     * 中标方式
     */
    private String biddingWay;

    private static final long serialVersionUID = 1L;

    private String statusName;

    /**
     * 额外字段
     * */
    private Long itemClassesId;

    private String itemClassesName;

    private Long biddingWayId;

    private String biddingWayName;

}
