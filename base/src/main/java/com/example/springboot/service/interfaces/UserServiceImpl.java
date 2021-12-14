package com.example.springboot.service.interfaces;

import com.example.springboot.db.dao.UserDao;
import com.example.springboot.db.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

//    @Override
//    public List<User> selectUser() {
////        return userDao.selectMaps();
//    }

    @Override
    public User selectUserById(int userId) {
        return userDao.selectById(userId);
    }
}
