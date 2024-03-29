package com.example.studybuddybackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studybuddybackend.common.ErrorCode;
import com.example.studybuddybackend.exception.BusinessException;
import com.example.studybuddybackend.mapper.UserMapper;
import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.service.UserService;
import com.example.studybuddybackend.utils.AlgorithmUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.studybuddybackend.constant.UserConstant.*;


/**
* @author Willow
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-30 11:35:27
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;



    @Override
    public long registerUser(String userAccount, String password, String rePassword, String webId) {
        // 判断是否为空
        if (StringUtils.isAnyBlank(userAccount, password, rePassword, webId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 账号长度不小于4位
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度小于4位");
        }
        // 密码长度不小于8位
        if (password.length() < 8 || rePassword .length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度小于8位");
        }
        if (webId.length() > 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "网站编号长度大于5位");
        }
        // 账户中不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户中包含特殊字符");
        }
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户重复");
        }
        // webId不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("web_id", webId);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户重复");
        }
        // 密码和校验密码相同
        if (!password.equals(rePassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码和校验密码不相同");
        }

        // 密码加密
        String encrypt = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        // 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encrypt);
        user.setWebId(webId);
        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "数据库存储用户失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String password, HttpServletRequest request) {
        // 1. 校验用户账户和密码是否合法
        // 判断是否为空
        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户或密码为空");
        }
        // 账号长度不小于4位
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度小于4位");
        }
        // 密码长度不小于8位
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度小于8位");
        }
        // 账户中不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户中包含特殊字符");
        }

        // 2. 校验密码和用户名是否输入正确
        String encrypt = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encrypt);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            log.info("Login failed. The user account cannot match the user password.");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名和密码不匹配");
        }

        // 用户脱敏
        User safeUser = getSafeUser(user);

        request.getSession().setAttribute(USER_LOGIN_STATE, safeUser);
        System.out.println(request.getSession().getAttribute(USER_LOGIN_STATE));

        System.out.println("------");

        return safeUser;
    }

    @Override
    public User getSafeUser(User user) {
        if (user == null) {
            return null;
        }
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setCreateTime(user.getCreateTime());
        safeUser.setUserRole(user.getUserRole());
        safeUser.setWebId(user.getWebId());
        safeUser.setTags(user.getTags());
        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    @Override
    public List<User> searchUserByTags(List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(queryWrapper);
        Gson gson = new Gson();
        return userList.stream().filter(user -> {
            String tags = user.getTags();
            if (StringUtils.isBlank(tags)) {
                return false;
            }
            Set<String> tagNameSet = gson.fromJson(tags, new TypeToken<Set<String>>() {
            }.getType());
            for (String tagName : tagNameList) {
                if (!tagNameSet.contains(tagName)) {
                    return false;
                }
            }
            return true;
        }).map(this::getSafeUser).collect(Collectors.toList());
    }

    @Override
    public int updateUser(User user, User loginUser) {
        // 如果用户没有传任何要更新的值，就直接报错，不用执行 update 语句
        if (user.getUsername() == null && user.getEmail() == null && user.getAvatarUrl() == null
            && user.getPhone() == null && user.getTags() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = user.getId();
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!isAdmin(loginUser) && loginUser.getId() != id) {
            throw new BusinessException(ErrorCode.NOT_AUTH);
        }
        User userOld = userMapper.selectById(id);
        if (userOld == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return userMapper.updateById(user);
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        System.out.println(userObj);
        User user = (User) userObj;
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_AUTH);
        }
        return user;
    }

    @Override
    public List<User> matchUsers(long num, User loginUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "tags");
        queryWrapper.isNotNull("tags");
        // 优化查询，只查找标签与用户id
        List<User> users = this.list(queryWrapper);
        String tags = loginUser.getTags();
        Gson gson = new Gson();
        List<String> tagList = gson.fromJson(tags, new TypeToken<List<String>>() {}.getType());
        List<Pair<User, Long>> list = new ArrayList<>();
        for (User user : users) {
            String userTags = user.getTags();
            if (StringUtils.isBlank(userTags) || loginUser.getId() == user.getId()) {
                continue;
            }
            List<String> userTagList = gson.fromJson(userTags, new TypeToken<List<String>>() {
            }.getType());
            long distance = AlgorithmUtils.minDistance(tagList, userTagList);
            list.add(new Pair<>(user, distance));
        }
        // 找出最匹配的num个用户
        List<Pair<User, Long>> topUserPair = list.stream().
                sorted((a, b) -> (int) (a.getValue() - b.getValue())).limit(num).
                collect(Collectors.toList());
        // 最匹配的num个用户的id
        List<Long> topIdList = topUserPair.stream().map(a -> a.getKey().getId()).collect(Collectors.toList());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id", topIdList);
        // 匹配用户的脱敏信息
        Map<Long, List<User>> userMap = this.list(userQueryWrapper).stream().
                map(this::getSafeUser).collect(Collectors.groupingBy(User::getId));
        ArrayList<User> result = new ArrayList<>();
        // 按匹配顺序输出
        for (Long id : topIdList) {
            result.add(userMap.get(id).get(0));
        }
        return result;
    }
}




