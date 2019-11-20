package com.example.controller;

import com.example.model.ContractDetail;
import com.example.model.ContractInfo;
import com.example.service.ContractService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 合同管理
 * @create 2019-11-17 21:19
 */
@RestController
@CrossOrigin
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/contract/addContractInfo")
    @RequiresAuthentication
    public String addContractInfo(@RequestBody ContractInfo contractInfo, HttpServletResponse httpServletResponse) {
        return contractService.addContractInfo(contractInfo,httpServletResponse);
    }

    @PostMapping("/contract/addContractDetail")
    @RequiresAuthentication
    public Integer addContractDetail(@RequestBody List<ContractDetail> contractDetails) {
        return contractService.addContractDetail(contractDetails);
    }

    @GetMapping("/contract/selectAllContractInfo")
    @RequiresAuthentication
    public List<ContractInfo> selectMainContract() {
        return contractService.selectMainContract();
    }

    @GetMapping("/contract/selectChildren")
    @RequiresAuthentication
    public List<ContractInfo> selectChildren(@RequestParam("fatherNumber")String fatherNumber) {
        return contractService.selectByFatherNumber(fatherNumber);
    }

    @GetMapping("/contract/selectContractByContractNumber")
    @RequiresAuthentication
    public ContractInfo selectContractByContractNumber(@RequestParam("contractNumber")String contractNumber) {
        return contractService.selectByContractNumber(contractNumber);
    }

    @GetMapping("/contract/selectContractDetailByContractNumber")
    @RequiresAuthentication
    public List<ContractDetail> selectContractDetailByContractNumber(@RequestParam("contractNumber")String contractNumber) {
        return contractService.selectContractDetailByContractNumber(contractNumber);
    }

    @PostMapping("/contract/updateContractDetail")
    @RequiresAuthentication
    public Integer updateContractDetail(@RequestBody List<ContractDetail> contractDetails) {
        return contractService.updateContractDetail(contractDetails);
    }

    @DeleteMapping("/contract/deleteContract")
    @RequiresPermissions("sys:user:shiro")
    public Integer deleteContract(@RequestBody ContractInfo contractInfo) {
        return contractService.deleteContractDetail(contractInfo);
    }
}
