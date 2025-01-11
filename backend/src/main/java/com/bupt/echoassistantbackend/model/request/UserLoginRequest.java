package com.bupt.echoassistantbackend.model.request;

import lombok.Data;

/**
 * 用户登录请求参数
 *
 * @author Ni Xiang
 */
@Data
public class UserLoginRequest {
    private String username;
    private String userPassword;
}
