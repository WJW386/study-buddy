package com.example.studybuddybackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Willow
 */
@SpringBootApplication
@MapperScan("com.example.studybuddybackend.mapper")
public class StudyBuddyBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyBuddyBackEndApplication.class, args);
    }

}
