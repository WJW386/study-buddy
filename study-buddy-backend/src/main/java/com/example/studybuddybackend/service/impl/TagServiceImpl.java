package com.example.studybuddybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studybuddybackend.model.Tag;
import com.example.studybuddybackend.service.TagService;
import com.example.studybuddybackend.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author Yurio
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-02-04 22:46:17
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




