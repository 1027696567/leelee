package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * contract_detail
 * @author
 */
@Data
public class ContractDetail implements Serializable {
    /**
     * 合同详细信息id
     */
    private Long detailId;

    /**
     * 详细信息
     */
    private String detailName;

    /**
     * 内容
     */
    private String detailContent;

    /**
     * 描述
     */
    private String detailDescribe;

    /**
     * 合同编号
     */
    private String contractNumber;

    private static final long serialVersionUID = 1L;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getDetailDescribe() {
        return detailDescribe;
    }

    public void setDetailDescribe(String detailDescribe) {
        this.detailDescribe = detailDescribe;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
