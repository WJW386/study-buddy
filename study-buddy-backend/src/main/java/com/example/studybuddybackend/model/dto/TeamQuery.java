package com.example.studybuddybackend.model.dto;

import com.example.studybuddybackend.common.PageRequest;
import lombok.Data;

import java.util.Date;

/**
 * @author Willow
 **/
@Data
public class TeamQuery extends PageRequest {

    private static final long serialVersionUID = -4203443164290088705L;
    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;
}
