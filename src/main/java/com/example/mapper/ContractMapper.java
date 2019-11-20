package com.example.mapper;

import com.example.model.ContractInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int deleteByPrimaryKey(Long contractId);

    int insert(ContractInfo record);

    int insertSelective(ContractInfo record);

    ContractInfo selectByPrimaryKey(Long contractId);

    int updateByPrimaryKeySelective(ContractInfo record);

    int updateByPrimaryKey(ContractInfo record);

    List<ContractInfo> selectAllContractInfo();

    ContractInfo selectByContractNumber(String contractNumber);

    List<ContractInfo> selectMainContract();

    List<ContractInfo> selectByFatherNumber(String fatherNumber);
}
