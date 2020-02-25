package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chen
 * @program leelee
 * @description 成本报表结合表
 * @create 2019-12-06 00:15
 */
@Data
public class CostStatementMerge implements Serializable {
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

   /**
    * costAccountInfo
    * */


    /**
     * 成本科目id
     */
    private Long costAccountId;

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
     * 是否有子节点
     */
    private boolean hasChildren;

    /**
     * 父节点编码
     */
    private String parentNumber;

    private Integer level;

    /**
     * costTargetInfo
     * */

    /**
     * 总价
     */
    private Integer totalPrice;

    private Integer workAmount;

    private Integer restWorkAmount;

    private Integer unitPrice;

    private Integer totalWorkAmount;

    private String measurementUnit;

    private Integer oldUnitPrice;
}
