package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class UserInfo implements Serializable {
    private Long userId;
    private String username;
    private String  status;
    private String roleName;
    private String realName;
    private String email;
    private String mobile;

}
