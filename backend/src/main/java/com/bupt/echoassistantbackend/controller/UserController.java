package com.bupt.echoassistantbackend.controller;

import com.bupt.echoassistantbackend.common.BaseResponse;
import com.bupt.echoassistantbackend.common.ErrorCode;
import com.bupt.echoassistantbackend.common.utils.ResultUtils;
import com.bupt.echoassistantbackend.exception.BusinessException;
import com.bupt.echoassistantbackend.model.domain.User;
import com.bupt.echoassistantbackend.model.request.UserLoginRequest;
import com.bupt.echoassistantbackend.model.request.UserRegisterRequest;
import com.bupt.echoassistantbackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 *
 * @author Ni Xiang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param registerRequest user register request
     * @return {@link BaseResponse }<{@link Long }>
     * @author Ni Xiang
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest registerRequest,
                                           HttpServletRequest request) {
        if (registerRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long result = userService.userRegister(registerRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param loginRequest login request
     * @param request      request
     * @return {@link BaseResponse }<{@link User }>
     * @author Ni Xiang
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {
        if (loginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(loginRequest, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户信息修改
     *
     * @param newUser new user
     * @param request request
     * @return {@link BaseResponse }<{@link Boolean }>
     * @author nx-xn2002
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> userUpdate(@RequestBody User newUser,HttpServletRequest request){
        return ResultUtils.success(true);
    }
}
