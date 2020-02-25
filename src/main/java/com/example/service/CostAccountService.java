package com.example.service;

import com.example.model.CostAccountInfo;
import com.example.model.CostStatementMerge;
import com.example.model.CostTargetMerge;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CostAccountService {
    List<CostAccountInfo> selectAll();

    String insert(HttpServletResponse httpServletResponse, CostAccountInfo costAccountInfo);

    List<CostAccountInfo> selectByItemNumber(String itemNumber);

    Integer updateCostAccount(CostAccountInfo costAccountInfo);

    String updateStatus(HttpServletResponse httpServletResponse,CostAccountInfo costAccountInfo);

    List<CostAccountInfo> selectChildren(String parentNumber);

    Integer deleteInfo(CostAccountInfo costAccountInfo);

    List<CostTargetMerge> selectCostTargetMerge(String itemNumber);

    List<CostTargetMerge> selectCostTargetChildren(String itemNumber);

    String insertCostTarget(CostTargetMerge costTargetMerge);

    String updateCostTarget(CostTargetMerge costTargetMerge);

    List<CostStatementMerge> selectCostStatementInfo(String itemNumber);

    List<CostStatementMerge> selectCostStatementChildren(String costAccountNumber);

    String insertCostStatement(CostStatementMerge costStatementMerge);

    String updateCostStatementInfo(CostStatementMerge costStatementMerge);
}
