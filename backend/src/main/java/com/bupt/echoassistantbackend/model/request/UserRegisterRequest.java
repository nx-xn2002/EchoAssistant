package com.bupt.echoassistantbackend.model.request;

import lombok.Data;

/**
 * 用户注册请求参数
 *
 * @author Ni Xiang
 * @date 2025-01-12
 */
@Data
public class UserRegisterRequest {
    private String username;
    private String password;
    private String checkPassword;
    private String verifyCode;
}
