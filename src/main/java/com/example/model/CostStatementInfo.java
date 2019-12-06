package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * cost_statement_info
 * @author
 */
@Data
public class CostStatementInfo implements Serializable {
    /**
     * 成本报表id
     */
    private Long costStatementId;

    /**
     * 科目类别编号
     */
    private String costAccountNumber;

    /**
     * 已发生成本
     */
    private Integer costIncurred;

    /**
     * 待发生成本
     */
    private Integer costWaitIncurred;

    /**
     * 动态成本
     */
    private Integer costDynamics;

    /**
     * 差价
     */
    private Integer differPrice;

    private static final long serialVersionUID = 1L;

}
