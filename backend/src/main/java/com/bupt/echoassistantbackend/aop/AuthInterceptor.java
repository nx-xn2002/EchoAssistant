package com.bupt.echoassistantbackend.aop;

import com.bupt.echoassistantbackend.annotation.AuthCheck;
import com.bupt.echoassistantbackend.common.ErrorCode;
import com.bupt.echoassistantbackend.content.UserContent;
import com.bupt.echoassistantbackend.exception.BusinessException;
import com.bupt.echoassistantbackend.model.domain.User;
import com.bupt.echoassistantbackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 权限校验 AOP
 *
 * @author nx-xn2002
 * @date 2024-10-02
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return {@link Object }
     * @author nx-xn2002
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        // 不需要权限，放行
        if (mustRole == null || mustRole.isEmpty()) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        String userRole = UserContent.getRoleStrByValue(loginUser.getUserRole());
        if (userRole == null) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (!mustRole.equals(userRole)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

