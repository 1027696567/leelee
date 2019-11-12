package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * verity_info
 * @author
 */
@Data
public class VerityInfo implements Serializable {
    private Long verityId;

    /**
     * 分类,1:战略供应商,2:瓶颈供应商,3:杠杆供应商,4:一般供应商
     */
    private String classify;

    /**
     * 分级,1:Ⅰ级,2:Ⅱ级,3:Ⅲ级,4:Ⅳ级
     */
    private String grade;

    /**
     * 备注
     */
    private String textarea;

    /**
     * 对应公司Id
     */
    private Long companyId;

    private static final long serialVersionUID = 1L;
}
