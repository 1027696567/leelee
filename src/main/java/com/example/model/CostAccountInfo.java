package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * cost_account_info
 * @author
 */
@Data
public class CostAccountInfo implements Serializable {
    /**
     * 成本科目id
     */
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

    private Integer level;

    private static final long serialVersionUID = 1L;

}
