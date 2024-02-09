package com.example.studybuddybackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Willow
 **/
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 7355086853545096936L;

    private String userAccount;

    private String password;

    private String rePassword;

    private String webId;
}
