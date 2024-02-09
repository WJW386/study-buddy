package com.example.studybuddybackend.service;

import com.example.studybuddybackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Willow
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-30 11:35:27
*/
public interface UserService extends IService<User> {

    /**
     * 登录校验
     * @param userAccount 用户账号
     * @param password 密码
     * @param rePassword 确认密码
     * @param webId 网站注册id
     * @return 输入是否合法
     */
    long registerUser(String userAccount, String password, String rePassword, String webId);

    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param password 密码
     * @param request 从前端接收请求中的登录信息
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String password, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user 用户对象
     * @return 脱敏对象
     */
    User getSafeUser(User user);

    /**
     * 用户注销
     * @return 是否注销成功
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     *
     * @param tagNameList 标签名列表
     * @return 带有所有在列表中标签的用户
     */
    List<User> searchUserByTags(List<String> tagNameList);
}
