package com.example.mapper;

import com.example.model.CostStatementInfo;
import com.example.model.CostStatementMerge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostStatementMapper {
    int deleteByPrimaryKey(Long costStatementId);

    int insert(CostStatementInfo record);

    int insertSelective(CostStatementInfo record);

    CostStatementInfo selectByPrimaryKey(Long costStatementId);

    int updateByPrimaryKeySelective(CostStatementInfo record);

    int updateByPrimaryKey(CostStatementInfo record);

    List<CostStatementMerge> selectByItemNumber(String itemNumber,boolean status);

    List<CostStatementMerge> selectChildren(String costAccountNumber,boolean status);

    CostStatementInfo selectSelfByCostAccountNumber(String costAccountNumber);
}
