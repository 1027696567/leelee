package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen
 * @program leelee
 * @description 提供投标人和供应商信息
 * @create 2019-11-15 01:08
 */
@Data
public class TenderTable implements Serializable {
    private Long tenderId;
    private String tenderUserName;
    private Date tenderTime;
    private String companyNumber;
    private String companyName;
    private String remarks;

}
