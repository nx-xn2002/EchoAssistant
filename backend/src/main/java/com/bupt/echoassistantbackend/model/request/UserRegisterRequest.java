package com.bupt.echoassistantbackend.model.request;

import lombok.Data;

/**
 * 用户注册请求参数
 *
 * @author Ni Xiang
 */
@Data
public class UserRegisterRequest {
    private String username;
    private String userPassword;
    private String checkPassword;
    private String verifyCode;
    private Integer userRole;
    private String phone;
}
