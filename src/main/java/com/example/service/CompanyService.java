package com.example.service;

import com.example.model.CompanyInfo;

import java.util.List;

public interface CompanyService {
    /**
     * 查询所有供应商信息
     * */
    public List<CompanyInfo> selectAllCompanyInfo();
    /**
     * 添加供应商
     * */
    public void addCompanyInfo(CompanyInfo companyInfo);
}
