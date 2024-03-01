package com.example.studybuddybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.studybuddybackend.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.model.dto.TeamQuery;
import com.example.studybuddybackend.model.request.TeamJoinRequest;
import com.example.studybuddybackend.model.request.TeamQuitRequest;
import com.example.studybuddybackend.model.request.TeamUpdateRequest;
import com.example.studybuddybackend.model.vo.TeamUserVO;

import java.util.List;

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

    /**
     * 查询符合TeamQuery条件的队伍
     *
     * @param teamQuery 前端发送的查询队伍条件
     * @param isAdmin 是否为管理员
     * @return 队伍和创建者信息的封装list
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更改对应队伍信息
     *
     * @param teamUpdateRequest 前端发送的更改队伍信息
     * @param loginUser 当前登录用户
     * @return 是否更新队伍成功
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    /**
     * 用户加入队伍
     * @param teamJoinRequest 前端请求中的加入队伍信息
     * @param loginUser 当前登录用户
     * @return 是否加入队伍成功
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    /**
     * 用户退出队伍
     * @param teamQuitRequest 前端请求中的退出队伍信息
     * @param loginUser 当前登录用户
     * @return 是否退出队伍成功
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 队长删除队伍
     * @param id 队伍id
     * @param loginUser 当前登录用户
     * @return 是否删除队伍成功
     */
    boolean deleteTeam(long id, User loginUser);
}
