package com.example.studybuddybackend.model.dto;

import com.example.studybuddybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author Willow
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {

    private static final long serialVersionUID = -4203443164290088705L;
    /**
     * id
     */
    private Long id;

    /**
     * id 列表
     */
    private List<Long> idList;

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
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 搜索关键词（同时对队伍名称和描述搜索）
     */
    private String searchText;

}
