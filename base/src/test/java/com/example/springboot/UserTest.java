package com.example.springboot;

import com.example.springboot.db.dao.UserDao;
import com.example.springboot.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserTest {

    @Resource
    private UserDao userDao;

    @Test
    void selectUser() {
        User user = userDao.findUserById(1);
        System.out.println("user: " + user);
    }

}
