package com.dmyxs.security;

import com.dmyxs.security.dao.UserDao;
import com.dmyxs.security.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SecurityApplicationTests {

    @Resource
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user");
        userEntity.setRole("normal");
        userEntity.setPassword(passwordEncoder.encode("123456"));

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setRole("admin");
        admin.setPassword(passwordEncoder.encode("123456"));

        userDao.insert(userEntity);
        userDao.insert(admin);
    }

    @Test
    void selectUserByName() {
        UserEntity userEntity = userDao.selectUserByName("admin");
        System.out.println("user: " + userEntity);
    }
}
