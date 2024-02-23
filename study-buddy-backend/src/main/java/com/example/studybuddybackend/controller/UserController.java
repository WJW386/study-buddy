package com.example.studybuddybackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studybuddybackend.common.BaseResponse;
import com.example.studybuddybackend.common.ErrorCode;
import com.example.studybuddybackend.common.ResultUtils;
import com.example.studybuddybackend.exception.BusinessException;
import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.model.domain.request.UserDeleteRequest;
import com.example.studybuddybackend.model.domain.request.UserLoginRequest;
import com.example.studybuddybackend.model.domain.request.UserRegisterRequest;
import com.example.studybuddybackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.studybuddybackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @author Willow
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:5173/")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest servletRequest) {
        Object o = servletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (o == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        User currentUser = (User) o;
        Long id = currentUser.getId();
        User user = userService.getById(id);
        User result = userService.getSafeUser(user);
        return ResultUtils.success(result);
    }


    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String rePassword = userRegisterRequest.getRePassword();
        String webId = userRegisterRequest.getWebId();

        if (StringUtils.isAnyBlank(userAccount, password, rePassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.registerUser(userAccount, password, rePassword, webId);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getUserPassword();
        System.out.println(password);
        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        System.out.println("Valid");
        User result = userService.userLogin(userAccount, password, request);
        return ResultUtils.success(result);
    }

    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return userService.userLogout(request);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NOT_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isAnyBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> result = userList.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(result);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsers(@RequestParam(required = false) List<String> tagList) {
        if (CollectionUtils.isEmpty(tagList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> users = userService.searchUserByTags(tagList);
        return ResultUtils.success(users);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody UserDeleteRequest userDeleteRequest, HttpServletRequest request) {
        long id = userDeleteRequest.getId();
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NOT_AUTH);
        }

        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }

    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommend(int pageSize, int pageNum) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> userList = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return ResultUtils.success(userList);
    }

}
