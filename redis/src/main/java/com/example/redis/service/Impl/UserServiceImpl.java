package com.example.redis.service.Impl;

import com.example.redis.db.dao.UserDao;
import com.example.redis.db.entity.User;
import com.example.redis.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = {"user"})
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Cacheable(key = "#userId")
    public User selectUserById(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    @CachePut(key = "#user.id")
    public User insertUser(User user) {
        userDao.insert(user);
        return userDao.findUserById(user.getId());
    }

    @Override
    @CacheEvict(key ="#userId")
    public int deleteUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setStatus(1);
        return userDao.updateById(user);
    }

    @Override
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        userDao.updateById(user);
        return userDao.findUserById(user.getId());
    }
}
