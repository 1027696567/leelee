package com.example.service.impl;

import com.example.mapper.CompanyMapper;
import com.example.model.CompanyInfo;
import com.example.service.CompanyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 供应商信息操作
 * @create 2019-10-18 19:50
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<CompanyInfo> selectAllCompanyInfo() {
        List<CompanyInfo> companyInfos = companyMapper.selectAll();
        for (int i = 0; i < companyInfos.size(); i++){
            if (companyInfos.get(i).getStatus().equals((byte)0)){
                companyInfos.get(i).setStatusName("待审核");
            } else if (companyInfos.get(i).getStatus().equals((byte)1)){
                companyInfos.get(i).setStatusName("审核通过");
            } else {
                companyInfos.get(i).setStatusName("审核未通过");
            }
        }
        return companyInfos;
    }
    @Override
    public void addCompanyInfo(CompanyInfo companyInfo) {
        Subject subject = SecurityUtils.getSubject();
        companyInfo.setStatus((byte) 0);
        companyInfo.setCreateUserName((String) subject.getPrincipal());
        Date date = new Date();
        companyInfo.setCreateTime(date);
        companyMapper.insertSelective(companyInfo);
    }
}
