package com.example.studybuddybackend.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时存入推荐用户，进行缓存预热
 *
 * @author Willow
 **/
@Component
@Slf4j
public class PreCacheJob {
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 重点用户的id
     */
    private List<Long> mainUsers = Arrays.asList(2L);

    @Scheduled(cron = "0 4 20 * * *")
    public void doCacheRecommendUsers() {
        String lockKey = "study:preCacheJob:doCache:lock";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                System.out.println("Get lock: " + Thread.currentThread().getId());
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> userPage = userService.page(new Page<>(1, 15), queryWrapper);
                String recommendKey = String.format("study:user:recommend:%s", mainUsers);
                ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                try {
                    valueOperations.set(recommendKey, userPage, 30, TimeUnit.SECONDS);
                } catch (Exception e) {
                    log.error("Redis set key error", e);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println("Unlock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }

    }
}
