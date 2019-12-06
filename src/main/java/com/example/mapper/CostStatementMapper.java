package com.example.mapper;

import com.example.model.CostStatementInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CostStatementMapper {
    int deleteByPrimaryKey(Long costStatementId);

    int insert(CostStatementInfo record);

    int insertSelective(CostStatementInfo record);

    CostStatementInfo selectByPrimaryKey(Long costStatementId);

    int updateByPrimaryKeySelective(CostStatementInfo record);

    int updateByPrimaryKey(CostStatementInfo record);
}
