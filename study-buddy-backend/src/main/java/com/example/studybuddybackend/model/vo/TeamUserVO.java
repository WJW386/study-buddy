package com.example.studybuddybackend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍和队伍创建者信息脱敏封装类
 *
 * @author Willow
 **/
@Data
public class TeamUserVO implements Serializable {

    private static final long serialVersionUID = 9204910813202741161L;

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

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者信息
     */
    private UserVO creatorVO;

    /**
     * 已加入的用户数量
     */
    private Integer hasJoinNum;

    /**
     * 是否加入队伍
     */
    private Boolean hasJoin = false;
}
