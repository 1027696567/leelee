package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * company_info
 * @author
 */
@Data
public class CompanyInfo implements Serializable {
    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 机构代码
     */
    private String companyNumber;

    /**
     * 机构名称
     */
    private String companyName;

    /**
     * 注册地
     */
    private String registeredPlace;

    /**
     * 评级资质
     */
    private String creditRating;

    /**
     * 法人代表
     */
    private String corporateRepresentative;

    /**
     * 固定资产
     */
    private String permanentAssets;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private Long telephone;

    /**
     * 备注
     */
    private String textarea;

    /**
     * 创建用户ID
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0：待审核，1：审核通过，2：审核未通过
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    private String statusName;

}
