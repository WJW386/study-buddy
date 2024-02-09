package com.example.studybuddybackend;

import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class StudyBuddyBackendApplicationTests {
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSearchByTags() {
        List<String> list = Arrays.asList("Java", "Python");
        List<User> users = userService.searchUserByTags(list);
        Assert.notNull(users);
    }


}
