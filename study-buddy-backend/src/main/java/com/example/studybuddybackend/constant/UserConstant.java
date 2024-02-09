package com.example.studybuddybackend.constant;

/**
 * @author Willow
 **/
public interface UserConstant {
    /**
     * 盐值，混淆密码
     */
    String SALT = "Spring";

    /**
     * 普通用户
     */
    int DEFAULT_ROLE = 0;

    /**
     * 管理员
     */
    int ADMIN_ROLE = 1;

    /**
     * 用户登录状态键
     */
    String USER_LOGIN_STATE = "userLoginState";
}
