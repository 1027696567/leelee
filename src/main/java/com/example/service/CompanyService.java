package com.example.service;

import com.example.model.CompanyInfo;
import com.example.model.VerityInfo;

import java.util.List;

public interface CompanyService {
    /**
     * 查询所有供应商信息
     * */
    List<CompanyInfo> selectAllCompanyInfo();
    /**
     * 添加供应商
     * */
    Integer addCompanyInfo(CompanyInfo companyInfo);

    Integer editCompanyInfo(CompanyInfo companyInfo);

    Integer addVerityInfo(VerityInfo verityInfo);

    VerityInfo selectVerityInfo(Long verityId);

    Integer updateVerityInfo(VerityInfo verityInfo);

    Integer updateCompanyInfo(Long companyId);

    Integer deleteCompanyInfo(Long companyId);
}
