package com.example.mapper;

import com.example.model.ContractDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractDetailMapper {
    int deleteByPrimaryKey(Long detailId);

    int insert(ContractDetail record);

    int insertSelective(ContractDetail record);

    ContractDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(ContractDetail record);

    int updateByPrimaryKey(ContractDetail record);

    List<ContractDetail> selectByContractNumber(String contractNumber);

    int deleteByContractNumber(String contractNumber);
}
