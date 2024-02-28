package com.example.studybuddybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studybuddybackend.model.domain.UserTeam;
import com.example.studybuddybackend.service.UserTeamService;
import com.example.studybuddybackend.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author Yurio
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-02-27 14:14:01
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




