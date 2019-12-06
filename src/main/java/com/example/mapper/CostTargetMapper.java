package com.example.mapper;

import com.example.model.CostTargetInfo;
import com.example.model.CostTargetMerge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostTargetMapper {
    int deleteByPrimaryKey(Long costTargetId);

    int insert(CostTargetInfo record);

    int insertSelective(CostTargetInfo record);

    CostTargetInfo selectByPrimaryKey(Long costTargetId);

    int updateByPrimaryKeySelective(CostTargetInfo record);

    int updateByPrimaryKey(CostTargetInfo record);

    List<CostTargetMerge> selectByItemNumber(String itemNumber,boolean status);

    List<CostTargetMerge> selectChildren(String costAccountNumber,boolean status);

    CostTargetInfo selectByCostAccountNumber(String costAccountNumber);

}
