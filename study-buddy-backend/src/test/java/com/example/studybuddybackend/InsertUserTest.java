package com.example.studybuddybackend;

import com.example.studybuddybackend.model.domain.User;
import com.example.studybuddybackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Willow
 **/
@SpringBootTest
public class InsertUserTest {
//    @Resource
//    UserService userService;
//
//    private ExecutorService executorService = new ThreadPoolExecutor(16, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));
//
//    /**
//     * 批量插入用户   1000  耗时： 969ms
//     */
//    @Test
//    public void doInsertUsers() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 1000;
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < INSERT_NUM; i++) {
//            User user = new User();
//            user.setUsername("FakeUser");
//            user.setUserAccount("fakeAccount");
//            user.setAvatarUrl("https://img1.baidu.com/it/u=1894617199,1124318134&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
//            user.setUserProfile("都是假的");
//            user.setGender(0);
//            user.setUserPassword("12345678");
//            user.setPhone("12352025475");
//            user.setEmail("fake@qq.com");
//            user.setUserStatus(0);
//            user.setUserRole(0);
//            user.setWebId("2334");
//            user.setTags("[]");
//            userList.add(user);
//        }
//        userService.saveBatch(userList,100);
//        stopWatch.stop();
//        System.out.println( stopWatch.getLastTaskTimeMillis());
//    }
//
//    /**
//     * 并发批量插入用户   100000  耗时： 6326ms
//     */
//    @Test
//    public void doConcurrencyInsertUser() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        final int INSERT_NUM = 100000;
//        // 分十组
//        int j = 0;
//        //批量插入数据的大小
//        int batchSize = 5000;
//        List<CompletableFuture<Void>> futureList = new ArrayList<>();
//        // i 要根据数据量和插入批量来计算需要循环的次数。
//        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
//            List<User> userList = new ArrayList<>();
//            while (true){
//                j++;
//                User user = new User();
//                user.setUsername("FakeUser");
//                user.setUserAccount("fakeAccount");
//                user.setAvatarUrl("https://img1.baidu.com/it/u=1894617199,1124318134&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
//                user.setUserProfile("都是假的");
//                user.setGender(0);
//                user.setUserPassword("12345678");
//                user.setPhone("12352025475");
//                user.setEmail("fake@qq.com");
//                user.setUserStatus(0);
//                user.setUserRole(0);
//                user.setWebId("2334");
//                user.setTags("[]");
//                userList.add(user);
//                if (j % batchSize == 0 ){
//                    break;
//                }
//            }
//            //异步执行
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//                System.out.println("ThreadName：" + Thread.currentThread().getName());
//                userService.saveBatch(userList,batchSize);
//            },executorService);
//            futureList.add(future);
//        }
//        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
//
//        stopWatch.stop();
//        System.out.println(stopWatch.getLastTaskTimeMillis());
//    }
}
