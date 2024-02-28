package com.example.studybuddybackend.service;

import com.example.studybuddybackend.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.studybuddybackend.model.domain.User;

/**
* @author Yurio
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-02-27 14:08:19
*/
public interface TeamService extends IService<Team> {

    /**
     * 添加队伍到数据库
     *
     * @param team 队伍
     * @param loginUser 登录用户
     * @return 队伍id
     */
    long addTeam(Team team, User loginUser);
}
