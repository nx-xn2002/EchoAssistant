package com.bupt.echoassistantbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.echoassistantbackend.common.ErrorCode;
import com.bupt.echoassistantbackend.common.utils.CheckUtils;
import com.bupt.echoassistantbackend.common.utils.PasswordUtils;
import com.bupt.echoassistantbackend.exception.BusinessException;
import com.bupt.echoassistantbackend.mapper.UserMapper;
import com.bupt.echoassistantbackend.model.domain.User;
import com.bupt.echoassistantbackend.model.request.UserLoginRequest;
import com.bupt.echoassistantbackend.model.request.UserRegisterRequest;
import com.bupt.echoassistantbackend.service.UserService;
import com.google.code.kaptcha.Constants;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.bupt.echoassistantbackend.content.UserContent.*;

/**
 * user service impl
 *
 * @author Ni Xiang
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Long userRegister(UserRegisterRequest registerRequest, HttpServletRequest request) {
        String username = registerRequest.getUsername();
        String userPassword = registerRequest.getUserPassword();
        String checkPassword = registerRequest.getCheckPassword();
        String verifyCode = registerRequest.getVerifyCode();
        Integer userRole = registerRequest.getUserRole();
        String phone = registerRequest.getPhone();

        String encoded = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        //1.校验
        if (StringUtils.isAnyBlank(username, userPassword, checkPassword, encoded, verifyCode, phone)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        if (StringUtils.isBlank(encoded)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "验证码已过期");
        }
        if (!PasswordUtils.equals(encoded, verifyCode)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "验证码错误");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度小于8");
        }
        if (userRole == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户类型错误");
        }
        if (!userRole.equals(STUDENT) && !userRole.equals(TEACHER)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户类型错误");
        }
        //校验手机号格式
        if (!CheckUtils.checkPhone(phone)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号格式错误");
        }
        //校验用户名格式
        if (!CheckUtils.checkUsername(username)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法的用户名格式");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不一致");
        }
        //校验用户名重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }
        //校验手机号重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号已被注册");
        }

        //2.对密码进行加密
        String newPassword = PasswordUtils.encode(userPassword);

        //3.插入数据
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(newPassword);
        user.setUserName(username);
        user.setUserRole(userRole);
        user.setAvatarUrl("https://avatars.githubusercontent.com/u/109718247?v=4");
        user.setPhone(phone);
        boolean result = save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        log.info("user register succeeded[({}):{}]", user.getId(), username);
        return user.getId();
    }

    @Override
    public User userLogin(UserLoginRequest loginRequest, HttpServletRequest request) {
        String username = loginRequest.getUsername();
        String userPassword = loginRequest.getUserPassword();
        //校验
        if (StringUtils.isAnyBlank(username, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        if (!CheckUtils.checkUsername(username)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }
        //查找
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("user_password", PasswordUtils.encode(userPassword));
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }
        //脱敏并返回
        return getSafetyUser(user);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return currentUser;
    }

    /**
     * 用户信息脱敏
     *
     * @param user user
     * @return {@link User }
     * @author Ni Xiang
     */
    private static User getSafetyUser(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setAvatarUrl(user.getAvatarUrl());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setUserRole(user.getUserRole());
        return newUser;
    }
}




