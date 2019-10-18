package com.example.mapper;

import com.example.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
/**
 * 根据用户名获取用户
 * */
    SysUser selectByUserName(String username);
/**
 * 根据用户名获取Id
 * */
    Long selectIdByUserName(String username);
/**
 * 获取所有用户
 * */
    List<SysUser> selectAllUser();
}
