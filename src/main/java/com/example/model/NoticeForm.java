package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 返回用户角色和公司信息
 * @create 2019-11-15 16:00
 */
@Data
public class NoticeForm implements Serializable {
    private List<String> roles;
    private String companyName;
    private List<String> replyRoles;
    private String replyCompanyName;
}
