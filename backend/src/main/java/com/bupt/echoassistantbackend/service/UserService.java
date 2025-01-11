package com.bupt.echoassistantbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.echoassistantbackend.model.domain.User;
import com.bupt.echoassistantbackend.model.request.UserLoginRequest;
import com.bupt.echoassistantbackend.model.request.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

/**
 * user service
 *
 * @author Ni Xiang
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param registerRequest register request
     * @param request         request
     * @return {@link Long }
     * @author Ni Xiang
     */
    Long userRegister(UserRegisterRequest registerRequest, HttpServletRequest request);

    /**
     * user login
     *
     * @param loginRequest login request
     * @param request      request
     * @return {@link User }
     * @author Ni Xiang
     */
    User userLogin(UserLoginRequest loginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return {@link User }
     * @author nx-xn2002
     */
    User getLoginUser(HttpServletRequest request);
}
