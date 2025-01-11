package com.bupt.echoassistantbackend.content;

/**
 * user content
 *
 * @author Ni Xiang
 */
public interface UserContent {
    /**
     * 用户角色 0 - 管理员, 1 - 学生, 2 - 老师
     */
    int ADMIN = 0;
    int STUDENT = 1;
    int TEACHER = 2;
    /**
     * 学生角色
     */
    String STUDENT_ROLE = "student";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 老师角色
     */
    String TEACHER_ROLE = "teacher";
    /**
     * 用户登录态
     */
    String USER_LOGIN = "USER LOGIN";

    /**
     * 将用户角色转为字符串
     *
     * @param role role
     * @return {@link String }
     * @author nx-xn2002
     */
    static String getRoleStrByValue(Integer role) {
        return switch (role) {
            case 0 -> ADMIN_ROLE;
            case 1 -> STUDENT_ROLE;
            case 2 -> TEACHER_ROLE;
            default -> null;
        };
    }
}
