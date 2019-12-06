package com.example.controller;

import com.example.model.CostAccountInfo;
import com.example.model.CostTargetMerge;
import com.example.service.CostAccountService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 成本管理
 * @create 2019-12-01 20:56
 */
@RestController
@CrossOrigin
public class CostAccountController {
    @Autowired
    private CostAccountService costAccountService;

    @GetMapping("/cost/selectAll")
    @RequiresAuthentication
    public List<CostAccountInfo> selectAll() {
        return costAccountService.selectAll();
    }

    @PostMapping("/cost/insertInfo")
    @RequiresPermissions("sys:user:shiro")
    public String insertInfo(@RequestBody CostAccountInfo costAccountInfo, HttpServletResponse httpServletResponse) {
        return costAccountService.insert(httpServletResponse,costAccountInfo);
    }

    @GetMapping("/cost/selectCostAccountInfo")
    @RequiresAuthentication
    public List<CostAccountInfo> selectByItemNumber(@RequestParam("itemNumber")String itemNumber) {
        return costAccountService.selectByItemNumber(itemNumber);
    }

    @PutMapping("/cost/updateCostAccount")
    @RequiresAuthentication
    public Integer updateCostAccount(@RequestBody CostAccountInfo costAccountInfo) {
        return costAccountService.updateCostAccount(costAccountInfo);
    }

    @GetMapping("/cost/selectChildren")
    @RequiresAuthentication
    public List<CostAccountInfo> selectChildren(@RequestParam("parentNumber")String parentNumber) {
        return costAccountService.selectChildren(parentNumber);
    }

    @PutMapping("/cost/updateStatus")
    @RequiresAuthentication
    public String updateStatus(@RequestBody CostAccountInfo costAccountInfo,HttpServletResponse httpServletResponse) {
        return costAccountService.updateStatus(httpServletResponse,costAccountInfo);
    }

    @DeleteMapping("/cost/deleteInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer deleteInfo(@RequestBody CostAccountInfo costAccountInfo) {
        return costAccountService.deleteInfo(costAccountInfo);
    }

    @GetMapping("/cost/getCostTargetMerge")
    @RequiresAuthentication
    public List<CostTargetMerge> selectCostTargetMerge(@RequestParam("itemNumber")String itemNumber) {
        return costAccountService.selectCostTargetMerge(itemNumber);
    }

    @GetMapping("/cost/getCostTargetChildren")
    @RequiresAuthentication
    public List<CostTargetMerge> selectCostTargetChildren(@RequestParam("costAccountNumber")String costAccountNumber) {
        return costAccountService.selectCostTargetChildren(costAccountNumber);
    }

    @PostMapping("/cost/insertCostTarget")
    @RequiresAuthentication
    public String insertCostTarget(@RequestBody CostTargetMerge costTargetMerge) {
        return costAccountService.insertCostTarget(costTargetMerge);
    }

    @PutMapping("/cost/updateCostTarget")
    @RequiresAuthentication
    public String updateCostTarget(@RequestBody CostTargetMerge costTargetMerge) {
        return costAccountService.updateCostTarget(costTargetMerge);
    }
}
