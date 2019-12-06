package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * cost_target_info
 * @author
 */
@Data
public class CostTargetInfo implements Serializable {
    /**
     * 目标成本id
     */
    private Long costTargetId;

    /**
     * 成本科目编号
     */
    private String costAccountNumber;

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

    private static final long serialVersionUID = 1L;




}
