package com.example.mapper;

import com.example.model.CostAccountInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostAccountMapper {
    int deleteByPrimaryKey(Long costAccountId);

    int insert(CostAccountInfo record);

    int insertSelective(CostAccountInfo record);

    CostAccountInfo selectByPrimaryKey(Long costAccountId);

    int updateByPrimaryKeySelective(CostAccountInfo record);

    int updateByPrimaryKey(CostAccountInfo record);

    List<CostAccountInfo> selectAll();
    /**
     * 主页面，根据项目编号查父节点，同时parent_number=null
     * */
    List<CostAccountInfo> selectByItemNumber(String itemNumber);

    List<CostAccountInfo> selectChildren(String parentNumber);

    List<CostAccountInfo> selectByCostAccountNumber(String costAccountNumber);

    List<CostAccountInfo> selectChildrenByStatus(String parentNumber,boolean status);

    CostAccountInfo selectByParentNumber(String costAccountNumber);
    /**
     * 查询同样父节点的项目
     * */
    List<CostAccountInfo> selectByParentNumbers(String parentNumber);

    CostAccountInfo selectSelfByCostNumber(String costAccountNumber);
}
