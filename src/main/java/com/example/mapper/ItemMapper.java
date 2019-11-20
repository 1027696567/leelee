package com.example.mapper;

import com.example.model.ItemInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    ItemInfo selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);

    ItemInfo selectByItemNumber(String itemNumber);

    List<ItemInfo> selectAllItemInfo();

    List<ItemInfo> selectFinish();
}
