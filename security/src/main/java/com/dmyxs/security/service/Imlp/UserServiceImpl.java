package com.dmyxs.security.service.Imlp;

import com.dmyxs.security.dao.UserDao;
import com.dmyxs.security.entity.UserEntity;
import com.dmyxs.security.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserEntity selectUserById(int userId) {
        return userDao.selectById(userId);
    }
}
