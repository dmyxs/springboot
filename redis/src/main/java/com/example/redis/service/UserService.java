package com.example.redis.service;


import com.example.redis.db.entity.User;

public interface UserService {
    User selectUserById(Integer userId);
    User insertUser(User user);
    int deleteUser(Integer userId);
    User updateUser(User user);
}
