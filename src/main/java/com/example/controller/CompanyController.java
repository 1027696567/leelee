package com.example.controller;

import com.example.model.CompanyInfo;
import com.example.service.CompanyService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 招投标管理
 * @create 2019-10-18 19:58
 */
@CrossOrigin
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/company/list")
    public List<CompanyInfo> selectAllCompanyInfo(){
       return companyService.selectAllCompanyInfo();
    }

    @RequestMapping("/company/insert")
    public void addInfo(@RequestBody CompanyInfo companyInfo){
        companyService.addCompanyInfo(companyInfo);
    }
}
