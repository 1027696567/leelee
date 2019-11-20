package com.example.service;

import com.example.model.ContractDetail;
import com.example.model.ContractInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ContractService {
    List<ContractInfo> selectMainContract();

    List<ContractInfo> selectByFatherNumber(String fatherNumber);

    String addContractInfo(ContractInfo contractInfo, HttpServletResponse httpServletResponse);

    Integer addContractDetail(List<ContractDetail> contractDetails);

    List<ContractInfo> selectAllContractInfo();

    ContractInfo selectByContractNumber(String contractNumber);

    List<ContractDetail> selectContractDetailByContractNumber(String contractNumber);

    Integer updateContractDetail(List<ContractDetail> contractDetails);

    Integer deleteContractDetail(ContractInfo contractInfo);
}
