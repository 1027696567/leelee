package com.example.service.impl;

import com.example.mapper.CompanyMapper;
import com.example.mapper.VerityMapper;
import com.example.model.CompanyInfo;
import com.example.model.SysUser;
import com.example.model.VerityInfo;
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
    @Autowired
    private VerityMapper verityMapper;

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
    public Integer addCompanyInfo(CompanyInfo companyInfo) {
        CompanyInfo companyInfo1 = companyMapper.selectByNumber(companyInfo.getCompanyNumber());
        if (companyInfo1 == null) {
            companyInfo.setStatus((byte) 0);
            Date date = new Date();
            companyInfo.setCreateTime(date);
            companyMapper.insertSelective(companyInfo);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer editCompanyInfo(CompanyInfo companyInfo) {
        return companyMapper.updateByPrimaryKeySelective(companyInfo);
    }
    /**
     * 添加供应商审核信息
     * */
    @Override
    public Integer addVerityInfo(VerityInfo verityInfo) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(verityInfo.getCompanyId());
        companyInfo.setStatus((byte) 1);
        companyMapper.updateByPrimaryKeySelective(companyInfo);
        return verityMapper.insertSelective(verityInfo);
    }
    /**
     * 查询供应商审核信息
     */
    @Override
    public VerityInfo selectVerityInfo(Long companyId) {
        return verityMapper.selectByCompanyId(companyId);
    }

    /**
     * 更新供应商审核信息
     * */
    @Override
    public Integer updateVerityInfo(VerityInfo verityInfo) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(verityInfo.getCompanyId());
        companyInfo.setStatus((byte) 1);
        companyMapper.updateByPrimaryKeySelective(companyInfo);
        return verityMapper.updateByPrimaryKeySelective(verityInfo);
    }

    /**
     * 只修改供应商审核状态，不删除审核信息
     * */
    @Override
    public Integer updateCompanyInfo(Long companyId) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(companyId);
        companyInfo.setStatus((byte) 2);
        return companyMapper.updateByPrimaryKeySelective(companyInfo);
    }

    /**
     * 删除供应商和对应审核信息
     * */
    @Override
    public Integer deleteCompanyInfo(Long companyId) {
        verityMapper.deleteByCompanyId(companyId);
        return companyMapper.deleteByPrimaryKey(companyId);
    }
}
