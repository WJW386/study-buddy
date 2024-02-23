package com.example.studybuddybackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Willow
 **/
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -1206344158209237791L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

}

