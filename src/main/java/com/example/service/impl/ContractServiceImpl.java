package com.example.service.impl;

import com.example.mapper.ContractDetailMapper;
import com.example.mapper.ContractMapper;
import com.example.model.ContractDetail;
import com.example.model.ContractInfo;
import com.example.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 合同操作
 * @create 2019-11-17 21:16
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Override
    public List<ContractInfo> selectMainContract() {
        List<ContractInfo> contractInfos = contractMapper.selectMainContract();
        for (int i = 0; i < contractInfos.size(); i++) {
            if (contractInfos.get(i).getContractTypes().equals("1")) {
                contractInfos.get(i).setContractTypesName("直接合同");
            } else if (contractInfos.get(i).getContractTypes().equals("2")) {
                contractInfos.get(i).setContractTypesName("三方合同");
            } else {
                contractInfos.get(i).setContractTypesName("补充合同");
            }
        }
        return contractInfos;
    }

    @Override
    public List<ContractInfo> selectByFatherNumber(String fatherNumber) {
        List<ContractInfo> contractInfos = contractMapper.selectByFatherNumber(fatherNumber);
        for (int i = 0; i < contractInfos.size(); i++) {
            if (contractInfos.get(i).getContractTypes().equals("1")) {
                contractInfos.get(i).setContractTypesName("直接合同");
            } else if (contractInfos.get(i).getContractTypes().equals("2")) {
                contractInfos.get(i).setContractTypesName("三方合同");
            } else {
                contractInfos.get(i).setContractTypesName("补充合同");
            }
        }
        return contractInfos;
    }

    @Override
    public String addContractInfo(ContractInfo contractInfo, HttpServletResponse httpServletResponse) {
        ContractInfo contractInfo1 = contractMapper.selectByContractNumber(contractInfo.getContractNumber());
        if (contractInfo1 != null) {
            httpServletResponse.setStatus(401);
            return "合同编号重复！";
        } else if (!contractInfo.getFatherNumber().equals("")){
            ContractInfo contractInfo2 = contractMapper.selectByContractNumber(contractInfo.getFatherNumber());
            contractInfo2.setHasChildren(true);
            contractMapper.updateByPrimaryKeySelective(contractInfo2);
            contractInfo.setHasChildren(false);
            contractMapper.insertSelective(contractInfo);
            return "合同签订成功！";
        } else {
            contractInfo.setHasChildren(false);
            contractMapper.insertSelective(contractInfo);
            return "合同签订成功！";
        }
    }

    @Override
    public Integer addContractDetail(List<ContractDetail> contractDetails) {
        for (int i = 0; i < contractDetails.size(); i++) {
            contractDetailMapper.insertSelective(contractDetails.get(i));
        }
        return 1;
    }

    @Override
    public List<ContractInfo> selectAllContractInfo() {
        return contractMapper.selectAllContractInfo();
    }

    @Override
    public ContractInfo selectByContractNumber(String contractNumber) {
        return contractMapper.selectByContractNumber(contractNumber);
    }

    @Override
    public List<ContractDetail> selectContractDetailByContractNumber(String contractNumber) {
        return contractDetailMapper.selectByContractNumber(contractNumber);
    }

    @Override
    public Integer updateContractDetail(List<ContractDetail> contractDetails) {
        for (int i = 0; i < contractDetails.size(); i++) {
            if (contractDetails.get(i).getDetailId() == null) {
                contractDetailMapper.insertSelective(contractDetails.get(i));
            } else {
                contractDetailMapper.updateByPrimaryKeySelective(contractDetails.get(i));
            }
        }
        return 1;
    }

    @Override
    public Integer deleteContractDetail(ContractInfo contractInfo) {
        if (contractInfo.getContractTypes().equals("3")) {
            List<ContractInfo> contractInfos = contractMapper.selectByFatherNumber(contractInfo.getFatherNumber());
            if (contractInfos.size() > 1) {
                contractDetailMapper.deleteByContractNumber(contractInfo.getContractNumber());
                return contractMapper.deleteByPrimaryKey(contractInfo.getContractId());
            } else {
                ContractInfo contractInfo1 = contractMapper.selectByContractNumber(contractInfo.getFatherNumber());
                contractInfo1.setHasChildren(false);
                contractMapper.updateByPrimaryKeySelective(contractInfo1);
                contractDetailMapper.deleteByContractNumber(contractInfo.getContractNumber());
                return contractMapper.deleteByPrimaryKey(contractInfo.getContractId());
            }
        } else {
            List<ContractInfo> contractInfos = contractMapper.selectByFatherNumber(contractInfo.getContractNumber());
            for (int i = 0; i < contractInfos.size(); i++) {
                contractDetailMapper.deleteByContractNumber(contractInfos.get(i).getContractNumber());
                contractMapper.deleteByPrimaryKey(contractInfos.get(i).getContractId());
            }
            contractDetailMapper.deleteByContractNumber(contractInfo.getContractNumber());
            contractMapper.deleteByPrimaryKey(contractInfo.getContractId());
            return 1;
        }
    }
}
