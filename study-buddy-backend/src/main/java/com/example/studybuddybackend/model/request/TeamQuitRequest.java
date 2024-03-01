package com.example.studybuddybackend.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Willow
 **/
@Data
public class TeamQuitRequest implements Serializable {

    private static final long serialVersionUID = 1755243522487239665L;

    /**
     * 队伍id
     */
    private Long teamId;
}
