package com.bupt.echoassistantbackend.common.utils;

/**
 * 校验工具类
 *
 * @author Ni Xiang
 */
public class CheckUtils {
    /**
     * 校验手机号
     * <p>
     * 位数 11 位且只包含 0 - 9 的数字
     *
     * @param phone phone
     * @return boolean
     * @author Ni Xiang
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        for (int i = 0; i < 11; i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验用户名
     * <p>
     * 1. 长度大于等于 4 且小于等于 10
     * 2. 只允许包含数字、字母、下划线
     *
     * @param username username
     * @return boolean
     * @author Ni Xiang
     */
    public static boolean checkUsername(String username) {
        if (username == null || username.length() < 4 || username.length() > 10) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                continue;
            }
            if (c >= 'A' && c <= 'Z') {
                continue;
            }
            if (c == '_') {
                continue;
            }
            return false;
        }
        return true;
    }
}
