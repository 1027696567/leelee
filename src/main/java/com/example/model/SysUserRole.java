package com.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * sys_user_role
 * @author
 */
@Data
public class SysUserRole implements Serializable {
    private Long Id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;

}
