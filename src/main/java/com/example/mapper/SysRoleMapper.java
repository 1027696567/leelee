package com.example.mapper;

import com.example.model.SysRole;
import com.example.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
/**
 * 根据Id查找角色
 * */
    SysRole selectByRoleId(Long roleId);
/**
 * 查询所有角色
 * */
    List<SysRole> selectAllRole();


//中间表
/**
 * sys_user_role根据用户Id查询角色Id
 * */
    List<Long> selectRoleIdByUserId(Long userId);
/**
 * 查询sys_user_role所有信息
 * */
    List<SysUserRole> selectAllInfo();
}
