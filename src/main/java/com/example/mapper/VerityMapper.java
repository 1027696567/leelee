package com.example.mapper;

import com.example.model.VerityInfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VerityMapper {

    int deleteByPrimaryKey(Long verityId);

    int insert(VerityInfo record);

    int insertSelective(VerityInfo record);

    VerityInfo selectByPrimaryKey(Long verityId);

    int updateByPrimaryKeySelective(VerityInfo record);

    int updateByPrimaryKey(VerityInfo record);

    VerityInfo selectByCompanyId(Long companyId);

    int deleteByCompanyId(Long companyId);
}
