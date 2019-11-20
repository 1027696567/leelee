package com.example.mapper;

import com.example.model.TenderInfo;
import com.example.model.TenderTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TenderMapper {
    int deleteByPrimaryKey(Long tenderId);

    int insert(TenderInfo record);

    int insertSelective(TenderInfo record);

    TenderInfo selectByPrimaryKey(Long tenderId);

    int updateByPrimaryKeySelective(TenderInfo record);

    int updateByPrimaryKey(TenderInfo record);

    TenderInfo selectByUserAndItem(String username, Long itemId);

    List<TenderTable> selectTenderTable(Long itemId);
}
