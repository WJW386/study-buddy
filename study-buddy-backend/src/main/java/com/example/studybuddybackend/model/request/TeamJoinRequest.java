package com.example.studybuddybackend.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Willow
 **/
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = -481907793805933655L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 队伍id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;
}
