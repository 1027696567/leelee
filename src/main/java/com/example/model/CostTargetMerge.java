package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chen
 * @program leelee
 * @description 目标成本与科目合并表
 * @create 2019-12-04 21:39
 */
@Data
public class CostTargetMerge implements Serializable {

    private Long costAccountId;
    /**
     * 成本科目编号
     */
    private String costAccountNumber;

    /**
     * 成本科目名称
     */
    private String costAccountName;

    /**
     * 项目编号
     */
    private String itemNumber;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 是否启用
     */
    private boolean status;

    /**
     * 科目类别
     */
    private String classifications;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private String createUsername;

    /**
     * 是否有子节点
     */
    private boolean hasChildren;

    /**
     * 父节点编码
     */
    private String parentNumber;


    /**
     * 目标成本id
     */
    private Long costTargetId;

    /**
     * 工作量
     */
    private Integer workAmount;

    /**
     * 单位
     */
    private String measurementUnit;

    /**
     * 单价
     */
    private Integer unitPrice;

    /**
     * 总价
     */
    private Integer totalPrice;

    private Integer level;

    private static final long serialVersionUID = 1L;
}
