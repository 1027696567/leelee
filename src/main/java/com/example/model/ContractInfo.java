package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * contract_info
 * @author
 */
@Data
public class ContractInfo implements Serializable {
    /**
     * 合同id
     */
    private Long contractId;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 项目编号
     */
    private String itemNumber;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 甲方
     */
    private String firstParty;

    /**
     * 乙方
     */
    private String secondParty;

    /**
     * 丙方
     */
    private String thirdParty;

    /**
     * 主合同编码
     */
    private String fatherNumber;

    /**
     * 合同性质
     */
    private String contractTypes;

    /**
     * 签约时间
     */
    private Date contractTime;

    /**
     * 责任部门
     */
    private String dutyName;

    /**
     * 责任人
     */
    private String dutyUsername;

    /**
     * 制单人
     */
    private String createUsername;

    /**
     * 制单时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    private Boolean hasChildren;

    private String contractTypesName;
}
